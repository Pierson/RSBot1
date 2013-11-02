package pSoftener;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;

import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;

import tasks.BankOpen;
import tasks.BankWithdraw;
import tasks.Interact;
import tasks.Soften;
import tasks.WalkToBank;
import tasks.WalkToFountain;
import util.Node;

@SuppressWarnings("deprecation")
@Manifest(name = "Clay Softener", description = "Efficiently softens clay at the Grand Exchange. Amazing profit!", authors = {"Piers"}, version = 1.0)
public class Softener extends PollingScript implements PaintListener, MessageListener {
    
    private final ArrayList<Node> nodes = new ArrayList<>();
    public static String status = "Starting Up...";
    private String runTime;
    
    private int amount = 0;
    private long second = 0;

    
    @Override
    public void start() {
	Collections.addAll(nodes, new BankOpen(ctx),  new BankWithdraw(ctx), new WalkToFountain(ctx), new WalkToBank(ctx), new Soften(ctx), new Interact(ctx));
    }

    @Override
    public void repaint(Graphics g1) {
	Graphics2D g = (Graphics2D) g1;
	long first = System.currentTimeMillis();
	int fps = (int) (1000 / (first - second));
	second = first;
	runTime = Timer.format(getRuntime());
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	Color gp = new Color(0f, 0f, 0f, .5f); //0red, 0blue, 0 green, 50% opacity
	g.setColor(gp);
	g.fillRoundRect(1, 1, 155, 115, 2, 2);
	
	// Foreground Text \\
	g.setColor(Color.ORANGE);
	g.setFont(new Font("Tahoma", Font.PLAIN, 13));
	g.drawString("Runtime: " +runTime, 5, 45);
	g.drawString("Clay Softened: " +amount, 5, 65);
	g.drawString("" +status, 5, 85);
	g.drawString("FPS: " +fps, 5, 105);
	g.setFont(new Font("Tahoma", Font.BOLD, 14));
	g.drawString("Clay Softener v1.0", 25, 15);
	g.drawLine(15, 25, 140, 25);
	
	// Mouse + Background \\
	g.setColor(Color.BLACK);
	g.setStroke(new BasicStroke(2));
	g.drawRoundRect(1, 1, 155, 115, 2, 2);
	g.drawLine(ctx.mouse.getLocation().x, ctx.mouse.getLocation().y - 7, ctx.mouse.getLocation().x, ctx.mouse.getLocation().y + 7);
	g.drawLine(ctx.mouse.getLocation().x - 7, ctx.mouse.getLocation().y, ctx.mouse.getLocation().x + 7, ctx.mouse.getLocation().y);
    }

    @Override
    public int poll() {
	for (Node node : nodes) {
	    if(node.validate()) {
		node.execute();
		return Random.nextInt(250, 375);
	    }
	}
	return 0;
    }
    
    @Override
    public void messaged(MessageEvent arg0) {
        final String claySoftened = arg0.getMessage().toLowerCase();
        if (claySoftened.contains("you mix the clay")) {
            amount++;
        }
    }
}