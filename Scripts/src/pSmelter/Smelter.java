package pSmelter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.SwingUtilities;

import ui.Gui;
import utils.Node;
import utils.ProgressBar;
import jobs.*;

import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;

import data.*;


@SuppressWarnings("deprecation")
@Manifest(name = "pSmelter", description = "Your AIO Smelting Experience. Soon to enable Smithing bars!", authors = {"Piers"}, version = 1.3)
public class Smelter extends PollingScript implements PaintListener, MessageListener {
    
   // Data \\
    private int startExp;
    private int smithExp;
    private int realLvl;
    private int smelted = 0;
    private long second = 0;
    
    public static String state = "Setting up...";
    public static String selected = "Selecting...";
    public static String selectedLocation = "Selecting...";
    public static boolean smith = false;
    private String runTime;
    
    public static Bar bar = Bar.NONE;
    public static Location location = Location.NONE;
    
    private Tile tile;
    private final ArrayList<Node> nodes = new ArrayList<>();
    
    private ProgressBar progressbar;
    
    
    // Script \\  
    public Smelter() {
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		new Gui();
	    }
	});
    }
    
    @Override
    public void start() {
	Collections.addAll(nodes, new Traverse(ctx), new Banking(ctx), new Smelt(ctx), new InteractFurnace(ctx), new Reverse(ctx));
	startExp = ctx.skills.getExperience(Skills.SMITHING);
	progressbar = new ProgressBar(ctx);
    }

    @Override
    public void repaint(Graphics g1) {
	Graphics2D g = (Graphics2D) g1;
	long first = System.currentTimeMillis();
	realLvl = ctx.skills.getLevel(Skills.SMITHING);
	smithExp = ctx.skills.getExperience(Skills.SMITHING) - startExp;
	int fps = (int) (1000 / (first - second));
	second = first;
	runTime = Timer.format(getRuntime());
	tile = ctx.movement.getDestination();
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	tile.getMatrix(ctx).draw(g);
	Color gp = new Color(0f, 0f, 0f, .5f); //0red, 0blue, 0 green, 50% opacity
	g.setColor(gp);
	g.fillRoundRect(1, 1, 155, 205, 2, 2);
	
	// Foreground Text \\
	g.setColor(Color.ORANGE);
	g.drawString("Added Edgeville + Fally support. Let me know if functionality issues. Remember to ZOOM in a lot!", 200, 50);
	g.setFont(new Font("Tahoma", Font.PLAIN, 13));
	g.drawString("Runtime: " +runTime, 5, 45);
	g.drawString("Current Level: " +realLvl, 5, 65);
	g.drawString("Gained Exp: " +smithExp, 5, 85);
	g.drawString("Bars Made: " +smelted, 5, 105);
	g.drawString("" +state, 5, 125);
	g.drawString("FPS: " +fps, 5, 145);
	g.drawString("Type: " +selected, 5, 165);
	g.drawString("Location: " +selectedLocation, 5, 185);
	g.setFont(new Font("Tahoma", Font.BOLD, 14));
	g.drawString("pSmelter v1.3", 25, 15);
	g.drawLine(15, 25, 140, 25);
	progressbar.drawProgressBar(g, 8, 195, 140, 10, Color.BLACK, Color.YELLOW, Color.RED, 10, 10, progressbar.getPercentToNextLevel(Skills.SMITHING));
	
	// Mouse + Background \\
	g.setColor(Color.BLACK);
	g.setStroke(new BasicStroke(2));
	g.drawRoundRect(1, 1, 155, 205, 2, 2);
	g.drawLine(ctx.mouse.getLocation().x, ctx.mouse.getLocation().y - 7, ctx.mouse.getLocation().x, ctx.mouse.getLocation().y + 7);
	g.drawLine(ctx.mouse.getLocation().x - 7, ctx.mouse.getLocation().y, ctx.mouse.getLocation().x + 7, ctx.mouse.getLocation().y);
    }

    @Override
    public int poll() {
	if(Gui.started) {
	    for (Node node : nodes) {
		if(node.validate()) {
		    node.execute();
		    return Random.nextInt(250, 375);
		}
	    }
	}
	return 0;
    }

    @Override
    public void messaged(MessageEvent arg0) {
        final String barsMade = arg0.getMessage().toLowerCase();
        if (barsMade.contains("you retrieve a")) {
            smelted++;
        }
        if(barsMade.contains("the magic of the varrock armour")) {
            smelted++;
        }
    }
}