package prayer;

import graphics.ProgressBar;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingUtilities;

import nodes.Bank;
import nodes.Bury;

import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;

import bones.Bone;

import uinterface.Gui;
import utils.Node;



@SuppressWarnings("deprecation")
@Manifest(name = "Bone Burier", description = "Buries bones and banks!", authors = {"Piers"}, version = 1.0)
public class PBurier extends PollingScript implements PaintListener, MessageListener {
    
    private final ArrayList<Node> nodes = new ArrayList<>();
    public static String status = "Starting Up...";
    public static Bone bone = Bone.NONE;
    public static String selected = "Selecting...";
    private String runTime;
    
    private int buried = 0;
    private int startExp;
    private int prayExp;
    private int realLvl;
    private int startLvl;
    private long second = 0;
    
    private ProgressBar progressbar;
    
    public PBurier() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui();
            }
        });
    }
    
    @Override
    public void start() {
	Collections.addAll(nodes, new Bury(ctx), new Bank(ctx));
	startExp = ctx.skills.getExperience(Skills.PRAYER);
	startLvl = ctx.skills.getLevel(Skills.PRAYER);
	progressbar = new ProgressBar(ctx);
    }

    @Override
    public void repaint(Graphics g1) {
	Graphics2D g = (Graphics2D) g1;
	long first = System.currentTimeMillis();
	realLvl = ctx.skills.getLevel(Skills.PRAYER);
	int gain = realLvl - startLvl; 
	prayExp = ctx.skills.getExperience(Skills.PRAYER) - startExp;
	int fps = (int) (1000 / (first - second));
	second = first;
	runTime = Timer.format(getRuntime());
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
	Color gp = new Color(0f, 0f, 0f, .5f); //0red, 0blue, 0 green, 50% opacity
	g.setColor(gp);
	g.fillRoundRect(1, 1, 155, 185, 2, 2);
	
	// Foreground Text \\
	g.setColor(Color.ORANGE);
	g.setFont(new Font("Tahoma", Font.PLAIN, 13));
	g.drawString("Runtime: " +runTime, 5, 45);
	g.drawString("Current Level: " +realLvl +" ("+gain+")", 5, 65);
	g.drawString("Gained Exp: " +prayExp, 5, 85);
	g.drawString("Bones Buried: " +buried, 5, 105);
	g.drawString("" +status, 5, 125);
	g.drawString("FPS: " +fps, 5, 145);
	g.drawString("Type: " +selected +" BONES", 5, 165);
	g.setFont(new Font("Tahoma", Font.BOLD, 14));
	g.drawString("Bone Burier v1.0", 25, 15);
	g.drawLine(15, 25, 140, 25);
	progressbar.drawProgressBar(g, 8, 170, 140, 10, Color.BLACK, Color.YELLOW, Color.RED, 10, 10, progressbar.getPercentToNextLevel(Skills.PRAYER));
	
	// Mouse + Background \\
	g.setColor(Color.BLACK);
	g.setStroke(new BasicStroke(2));
	g.drawRoundRect(1, 1, 155, 185, 2, 2);
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
        final String bonesBuried = arg0.getMessage().toLowerCase();
        if (bonesBuried.contains("you bury")) {
            buried++;
        }
    }
}