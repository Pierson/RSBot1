package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Random;

import pSoftener.Softener;

import data.Path;

import util.Node;

public class WalkToBank extends Node{
    
    public Path tile = Path.NORTH_WEST;
    private final int softClay = 1761;

    public WalkToBank(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	return ctx.backpack.select().id(softClay).count() == 28;
    }

    @Override
    public void execute() {
	path();
    }
    
    
    
    public void path() {
	int path = Random.nextInt(0, 6);
	switch(path) {
	
	case 1:
	    Softener.status = "Walking to NW Bank";
	    ctx.movement.findPath(Path.NORTH_WEST.getPath()).traverse();
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
