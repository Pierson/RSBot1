package jobs;


import org.powerbot.script.methods.MethodContext;
import pSmelter.Smelter;

import utils.Antipattern;
import utils.Node;

public class Traverse extends Node {

    public Traverse(MethodContext arg0) {
	super(arg0);
	antipattern = new Antipattern(ctx);
    }
    
    Antipattern antipattern;
    
    @Override
    public boolean validate() {
	return (ctx.backpack.select().id(Smelter.bar.getFirstIngredient()).count() == Smelter.bar.getWithdrawAmt() && ctx.backpack.select().id(Smelter.bar.getSecondIngredient()).count() == Smelter.bar.getSecondWithdrawAmt()) && ctx.bank.isOnScreen();
    }

    @Override
    public void execute() {
	Smelter.state = "Traversing to Furnace";
	ctx.movement.findPath(Smelter.location.getTile()).traverse();	
	while(ctx.players.local().isInMotion()) {
	    antipattern.antiban();
	    sleep(300, 800);
	}
    }
}
