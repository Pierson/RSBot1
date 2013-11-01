package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Interactive;

import pSoftener.Softener;

import util.Node;

public class Interact extends Node{
    
    private final int fount = 47150;
    private final int clay = 434;

    public Interact(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	final GameObject fountain  = ctx.objects.select().id(fount).first().poll();
	final Component skillInterface = ctx.widgets.get(1370, 26); //pop up interface
	return fountain.isOnScreen() 
		&& !ctx.backpack.select().id(clay).isEmpty()
		&& !skillInterface.isOnScreen();
    }

    @Override
    public void execute() {
	final GameObject fountain  = ctx.objects.select().id(fount).first().poll();
	
	if(!fountain.isOnScreen()) {
	    Softener.status = "Adjusting Camera";
	    ctx.camera.turnTo(fountain);
	    ctx.movement.stepTowards(fountain);
	}
	if(fountain.isOnScreen()) {
	    if (!ctx.objects.select().id(fountain).first().each(Interactive.doInteract("Smelt")).isEmpty()) {
		sleep(500, 1500);
	    }
	}
    }
}
