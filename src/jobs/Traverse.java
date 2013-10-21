package jobs;


import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Tile;

import pSmelter.Smelter;

import utils.Antipattern;
import utils.Node;

public class Traverse extends Node {

    public Traverse(MethodContext arg0) {
	super(arg0);
	antipattern = new Antipattern(ctx);
    }
    
    Antipattern antipattern;
    
    private final Tile tile = new Tile(3275, 3190, 0);
    
    @Override
    public boolean validate() {
	return (ctx.backpack.select().id(Smelter.bar.getFirstIngredient()).count() == Smelter.bar.getWithdrawAmt() && ctx.backpack.select().id(Smelter.bar.getSecondIngredient()).count() == Smelter.bar.getSecondWithdrawAmt()) && ctx.bank.isOnScreen();
    }

    @Override
    public void execute() {
	Smelter.state = "Traversing to Furnace";
	ctx.movement.findPath(tile).traverse();	
	while(ctx.players.local().isInMotion()) {
	    antipattern.antiban();
	    sleep(300, 800);
	}
    }
}
