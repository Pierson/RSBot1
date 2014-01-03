package pSummoner;

import data.Familiar;
import data.Teleport;
import gui.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;

import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.Hud;
import org.powerbot.script.methods.Skills;
import org.powerbot.script.util.Random;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;

import utils.graphics.ProgressBar;

import core.Job;
import core.JobContainer;

    
@SuppressWarnings("deprecation")
@Manifest(name = "pSummoner", description = "AIO Summoning. Full pouch support, more features to come soon!", version = 1.0, topic = 0)
public class Summoner extends PollingScript implements PaintListener {
    
   // Data \\
    private int startExp;
    private int sumExp;
    private int startLvl;
    private int realLvl;
    
    public static Familiar familiar = Familiar.NONE;
    public Teleport option = Teleport.TAVERLY;
    public JobContainer container = new JobContainer();
    
    public static String state = "Setting up...";
    private String runTime;
    public String selected;
    public String teleport;
    
    private Date time;
    private long startTime;
    
    private ProgressBar pb;
    
	private final TilePath path = new TilePath(ctx, new Tile[] {
		new Tile(2877, 3418, 0),
		new Tile(2882, 3417, 0),
		new Tile(2887, 3417, 0),
		new Tile(2892, 3415, 0),
		new Tile(2897, 3415, 0),
		new Tile(2902, 3415, 0),
		new Tile(2907, 3416, 0),
		new Tile(2912, 3422, 0),
		new Tile(2915, 3424, 0),
		new Tile(2919, 3425, 0),
		new Tile(2923, 3433, 0),
		new Tile(2923, 3436, 0),
		new Tile(2923, 3440, 0),
		new Tile(2923, 3444, 0),
		new Tile(2930, 3448, 0),
});
    
   
    // Script \\
    @Override
    public void start() {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		new Gui(ctx, Summoner.this);
	    }
	});
	startExp = ctx.skills.getExperience(Skills.SUMMONING);
	startLvl = ctx.skills.getLevel(Skills.SUMMONING);
	pb = new ProgressBar(ctx);
	startTime = System.currentTimeMillis();
    }
    
    public int perHour(int value) {
	return (int) ((value) * 3600000D / (System.currentTimeMillis() - startTime));
    }
    
    public int gain(int a, int b) {
	return a - b;
    }
    
    final BufferedImage image = downloadImage("http://i.imgur.com/WQk6g7O.png");
	private void drawMouse(Graphics g) {
	        ((Graphics2D) g).setRenderingHints(new RenderingHints(
	                RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON));
	        Point p = ctx.mouse.getLocation();
	        Graphics2D spinG = (Graphics2D) g.create();
	        Graphics2D spinG2 = (Graphics2D) g.create();
	        spinG.setColor(Color.CYAN.brighter()); 
	        spinG.rotate(System.currentTimeMillis() % 2000d / 2000d * (360d) * 2
	                * Math.PI / 180.0, p.x, p.y);
	        final int outerSize = 20;
	        spinG.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
	                BasicStroke.JOIN_ROUND));
	        spinG.drawArc(p.x - (outerSize / 2), p.y - (outerSize / 2), outerSize,
	                outerSize, 100, 75);
	        spinG.drawArc(p.x - (outerSize / 2), p.y - (outerSize / 2), outerSize,
	                outerSize, -100, 75);
	        g.setColor(Color.CYAN.darker());
	        g.fillOval(p.x, p.y, 2, 2);
	        spinG2.setColor(Color.CYAN.brighter());
	        spinG2.rotate(System.currentTimeMillis() % 2000d / 2000d * 360d
	                * Math.PI / 180.0, p.x, p.y);
	        spinG2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
	                BasicStroke.JOIN_ROUND));
	        spinG2.drawLine(p.x - 5, p.y, p.x + 5, p.y);
	        spinG2.drawLine(p.x, p.y - 5, p.x, p.y + 5);
	    }

    @Override
    public void repaint(Graphics g1) {
	Graphics2D g = (Graphics2D) g1;
	
	realLvl = ctx.skills.getLevel(Skills.SUMMONING);
	sumExp = ctx.skills.getExperience(Skills.SUMMONING) - startExp;
	runTime = Timer.format(getRuntime());
	time = Calendar.getInstance().getTime();
	
	g.drawImage(image, 1, 390, null);
	g.setFont(new Font("Tahoma", Font.PLAIN, 13));
	g.setColor(Color.CYAN);
	g.drawString("" +realLvl+"("+gain(realLvl, startLvl)+")", 352, 465);
	g.drawString("" +state, 352, 535);
	g.drawString("" +sumExp, 90, 465);
	g.drawString("" +perHour(sumExp), 90, 500);
	g.drawString("" +selected, 352, 500);
	g.drawString("" +runTime, 90, 535);
	g.setFont(new Font("Arial", Font.BOLD, 11));
	g.drawString("" +time, 480, 420);
	g.drawString(+pb.getPercentToNextLevel(Skills.SUMMONING) +"%", 531, 578);
	pb.drawProgressBar(g, 70, 569, 450, 10, Color.BLACK, Color.CYAN, Color.MAGENTA, 10, 10, pb.getPercentToNextLevel(Skills.SUMMONING));
	
	g.setStroke(new BasicStroke(2));
	drawMouse(g);
    }
    
    @Override
    public int poll() {
	if(ctx.hud.isOpen(Hud.Window.BACKPACK)) {
	    ctx.hud.open(Hud.Window.BACKPACK);
	}
	if(!ctx.hud.isVisible(Hud.Window.BACKPACK)) {
	    ctx.hud.view(Hud.Window.BACKPACK);
	}
	if(!ctx.movement.isRunning()) {
	    ctx.movement.setRunning(true);
	}
	Job j = container.get();
	if(j != null) {
	    j.execute();
	}
	return Random.nextInt(100, 300);
    }
}