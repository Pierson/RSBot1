package jobs;


import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;

import pSmelter.Smelter;

import utils.Antipattern;
import utils.Node;

public class Reverse extends Node {
    
    public Reverse(MethodContext arg0) {
	super(arg0);
	antipattern = new Antipattern(ctx);
    }
    
    Antipattern antipattern;
    
    private final Tile tile = new Tile(3270, 3166, 0);
    private final int FURNACE = 76293;
    
    @Override
    public boolean validate() {
	final GameObject furnace = ctx.objects.select().id(FURNACE).first().poll();
	return furnace.isOnScreen() && !ctx.backpack.select().id(Smelter.bar.getBar()).isEmpty() && (ctx.backpack.count() == Smelter.bar.getMaxBar() || ctx.backpack.select().id(Smelter.bar.getFirstIngredient()).isEmpty());
    }

    @Override
    public void execute() {
	Smelter.state = "Going to Bank";
	ctx.movement.findPath(tile).traverse();
	while(ctx.players.local().isInMotion()) {
	    antipattern.antiban();
	    sleep(200, 1000);
	}	
    }
}
