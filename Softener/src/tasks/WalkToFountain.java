package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;

import pSoftener.Softener;

import util.Node;

public class WalkToFountain extends Node {
    
    private final int clay = 434;
    private final Tile tile = new Tile(3163, 3489, 0);
    private final int fount = 47150;

    public WalkToFountain(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	final GameObject fountain  = ctx.objects.select().id(fount).first().poll();
	return ctx.backpack.select().id(clay).count() == 28 && !fountain.isOnScreen() && !ctx.bank.isOpen();
    }

    @Override
    public void execute() {
	Softener.status = "Walking to Fountain";
	ctx.movement.findPath(tile).traverse();
	while(ctx.players.local().isInMotion()) {
	    sleep(100, 500);
	}
    }
}
