package jobs;


import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import pSmelter.Smelter;

import utilities.Antipattern;
import utilities.Node;

public class Reverse extends Node {
    
    public Reverse(MethodContext arg0) {
	super(arg0);
	antipattern = new Antipattern(ctx);
    }
    
    Antipattern antipattern;
    
    @Override
    public boolean validate() {
	final GameObject furnace = ctx.objects.select().id(Smelter.location.getFurnace()).first().poll();
	return furnace.isOnScreen() && !ctx.backpack.select().id(Smelter.bar.getBar()).isEmpty() && (ctx.backpack.count() == Smelter.bar.getMaxBar() || ctx.backpack.select().id(Smelter.bar.getFirstIngredient()).isEmpty());
    }

    @Override
    public void execute() {
	Smelter.state = "Going to Bank";
	ctx.movement.findPath(Smelter.location.getBankTile()).traverse();
	while(ctx.players.local().isInMotion()) {
	    antipattern.antiban();
	    sleep(200, 1000);
	}	
    }
}
