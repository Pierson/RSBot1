package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import util.Node;

public class WalkToFountain extends Node {
    
    private final int clay = 434;
    private final Tile tile = new Tile(3163, 3489, 0);

    public WalkToFountain(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	return ctx.backpack.select().id(clay).count() == 28;
    }

    @Override
    public void execute() {
	ctx.movement.findPath(tile).traverse();
	if(ctx.players.local().isInMotion()) {
	    sleep(200, 1200);
	}
    }
}
