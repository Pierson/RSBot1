package jobs;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Interactive;

import pSmelter.Smelter;

import utils.Node;

public class InteractAnvil extends Node {

    public InteractAnvil(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	final GameObject anvil = ctx.objects.select().id(123).first().poll();
	return Smelter.smith && !ctx.backpack.select().id(Smelter.bar.getBar()).isEmpty() && anvil.isOnScreen();
    }

    @Override
    public void execute() {
	final GameObject anvil = ctx.objects.select().id(123).first().poll();
	
	if(!anvil.isOnScreen()) {
	    ctx.camera.turnTo(anvil);
	}
	if (!ctx.objects.select().id(anvil).first().each(Interactive.doInteract("Smelt")).isEmpty()) {
	    sleep(200, 1000);
	}
    }
}
