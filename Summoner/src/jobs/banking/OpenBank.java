package jobs.banking;

import org.powerbot.script.methods.MethodContext;

import pSummoner.Summoner;


import core.Job;


public class OpenBank extends Job {

    public OpenBank(MethodContext arg0) {
	super(arg0);
    }
    
    private boolean containsItem() {
	return ctx.backpack.select().id(Summoner.familiar.getIngredient1()).count() == Summoner.familiar.getWithdrawAmt1()
		&& ctx.backpack.select().id(Summoner.familiar.getIngredient2()).count() == Summoner.familiar.getWithdrawAmt2();
    }

    @Override
    public boolean validate() {
	return ctx.bank.isOnScreen() 
		&& !ctx.bank.isOpen()
		&& !containsItem();
    }

    @Override
    public void execute() {
	Summoner.state = "Finding Bank";
	if(!ctx.bank.isOnScreen()) {
	    ctx.camera.turnTo(ctx.bank.getNearest());
	    ctx.movement.stepTowards(ctx.bank.getNearest());
	} else {
	    Summoner.state = "Opening Bank";
	    if(ctx.bank.open()) {	    
	    }
	}
    }
}