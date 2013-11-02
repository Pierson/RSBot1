package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.GameObject;

import pSoftener.Softener;
import path.Path;


import util.Node;

public class WalkToBank extends Node{
    
    public Path tile = Path.NORTH_WEST;
    private final int softClay = 1761;
    private final int fount = 47150;

    public WalkToBank(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	final GameObject fountain  = ctx.objects.select().id(fount).first().poll();
	return ctx.backpack.select().id(softClay).count() == 28 && fountain.isOnScreen();
    }

    @Override
    public void execute() {
	Softener.status = "Walking to Bank";
	path();
	while(ctx.players.local().isInMotion()) {
	    sleep(100, 500);
	}
    }
    
    
    
    public void path() {
	int path = Random.nextInt(1, 4);
	switch(path) {
	
	case 1:
	    Softener.status = "Walking to NW Bank";
	    ctx.movement.findPath(Path.NORTH_EAST.getPath()).traverse();
	    if(ctx.players.local().isInMotion()) {
		sleep(200, 1200);
	    }
	    break;
	    
	case 2:
	    Softener.status = "Walking to NE Bank";
	    ctx.movement.findPath(Path.NORTH_EAST.getPath()).traverse();
	    if(ctx.players.local().isInMotion()) {
		sleep(200, 1200);
	    }
	    break;
	
	case 3:
	    Softener.status = "Walking to SW Bank";
	    ctx.movement.findPath(Path.SOUTH_WEST.getPath()).traverse();
	    if(ctx.players.local().isInMotion()) {
		sleep(200, 1200);
	    }
	    break;
	    
	case 4:
	    Softener.status = "Walking to SE Bank";
	    ctx.movement.findPath(Path.SOUTH_EAST.getPath()).traverse();
	    if(ctx.players.local().isInMotion()) {
		sleep(200, 1200);
	    }
	    break;

	}
    }
}
