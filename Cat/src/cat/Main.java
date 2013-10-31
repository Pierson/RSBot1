package cat;

import gui.Gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingUtilities;

import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.Interactive;
import org.powerbot.script.wrappers.Tile;

import enums.Pet;



@Manifest(name = "pClockWork", description = "Spam drops clockwork cats", authors = {"Piers"}, version = 1.0)
public class Main extends PollingScript implements PaintListener {
    
    private final ArrayList<Node> nodes = new ArrayList<>();
    public static Pet pet = Pet.NONE;
    public static String selected = "Selecting...";
    private String status = "Starting Up";
    Tile tile;
    
    public Main() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Gui();
            }
        });
    }
    
    @Override
    public void start() {
	Collections.addAll(nodes, new FailSafe(ctx), new Drop(ctx), new Grab(ctx));
	tile = ctx.players.local().getLocation();
    }

    @Override
    public void repaint(Graphics g1) {
	Graphics2D g = (Graphics2D) g1;
	g.setStroke(new BasicStroke(2));
	g.drawString("Status: "+status, 5, 30);
	g.drawString("Selected: "+selected, 5, 50);
	g.drawLine(ctx.mouse.getLocation().x, ctx.mouse.getLocation().y - 7, ctx.mouse.getLocation().x, ctx.mouse.getLocation().y + 7);
	g.drawLine(ctx.mouse.getLocation().x - 7, ctx.mouse.getLocation().y, ctx.mouse.getLocation().x + 7, ctx.mouse.getLocation().y);
	tile.getMatrix(ctx).draw(g);
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
    
    public class Drop extends Node {

	public Drop(MethodContext arg0) {
	    super(arg0);
	}

	@Override
	public boolean validate() {
	    return !ctx.backpack.select().id(pet.getId()).isEmpty();
	}

	@Override
	public void execute() {
	    status = "Dropping";
	    ctx.mouse.hop(62, 354);
	    ctx.combatBar.getActionAt(0).getComponent().click();  
	}
    }
    
    public class Grab extends Node {

	public Grab(MethodContext arg0) {
	    super(arg0);
	}

	@Override
	public boolean validate() {
	    return !ctx.npcs.select().id(pet.getGroundId()).isEmpty();
	}

	@Override
	public void execute() {
	    status = "Picking up";
	    ctx.mouse.hop(262, 224);
	    if(!ctx.npcs.select().id(pet.getGroundId()).first().each(Interactive.doInteract("Pick-up")).isEmpty()) {
		sleep(200,500);
	    }
	}
    }
    
    public class FailSafe extends Node {

	public FailSafe(MethodContext arg0) {
	    super(arg0);
	}

	@Override
	public boolean validate() {
	    return !ctx.players.local().getLocation().equals(tile);
	}

	@Override
	public void execute() {
	   status = "Returning to Main Tile";
	   ctx.movement.stepTowards(tile);	    
	}
    }
}

	
	