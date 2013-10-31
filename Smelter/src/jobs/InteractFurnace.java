package jobs;


import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Interactive;

import pSmelter.Smelter;
import utilities.Node;

public class InteractFurnace extends Node {

    public InteractFurnace(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	final GameObject furnace = ctx.objects.select().id(Smelter.location.getFurnace()).first().poll();
	final Component SKILL_INTERFACE = ctx.widgets.get(1370, 26); //pop up interface
	return furnace.isOnScreen() && !SKILL_INTERFACE.isOnScreen() && !SKILL_INTERFACE.isVisible() && !ctx.backpack.select().id(Smelter.bar.getFirstIngredient()).isEmpty();
    }

    @Override
    public void execute() {
	final GameObject closedDoor = ctx.objects.select().id(11707).first().poll();
	final GameObject furnace = ctx.objects.select().id(Smelter.location.getFurnace()).first().poll();
	
	Smelter.state = "Interacting with Furnace";
	if(!ctx.movement.isReachable(ctx.players.local(), furnace)) {
	    Smelter.state = "Opening Door";
	    if(closedDoor.interact("Open")) {
	    }
	}
	if(!furnace.isOnScreen()) {
	    Smelter.state = "Stepping Towards Furnace";
	    ctx.camera.turnTo(furnace);
	    ctx.movement.stepTowards(furnace);
	}
	if (!ctx.objects.select().id(furnace).first().each(Interactive.doInteract("Smelt")).isEmpty()) {
	    sleep(200, 1000);
	}
    }
}
			    
