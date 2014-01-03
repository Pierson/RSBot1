package jobs.banking;

import org.powerbot.script.methods.MethodContext;
import pSummoner.Summoner;

import core.Job;

public class Withdraw extends Job {

    public Withdraw(MethodContext arg0) {
	super(arg0);
    }
    
    private boolean isCorrect1() {
	return ctx.backpack.select().id(Summoner.familiar.getIngredient1()).count() != Summoner.familiar.getWithdrawAmt1();
    }
    
    private boolean isCorrect2() {
	return ctx.backpack.select().id(Summoner.familiar.getIngredient2()).count() != Summoner.familiar.getWithdrawAmt2()
		&& ctx.backpack.select().id(Summoner.familiar.getIngredient1()).count() == Summoner.familiar.getWithdrawAmt1();
    }
    
    private boolean containsItem() {
	return ctx.backpack.select().id(Summoner.familiar.getIngredient1()).count() == Summoner.familiar.getWithdrawAmt1()
		&& ctx.backpack.select().id(Summoner.familiar.getIngredient2()).count() == Summoner.familiar.getWithdrawAmt2();
    }
    
    
    @Override
    public boolean validate() {
	return ctx.bank.isOpen() && !containsItem() && ctx.backpack.select().id(Summoner.familiar.getPouchID()).isEmpty();
    }

    @Override
    public void execute() {
	
	if(isCorrect1()) {
	    Summoner.state = "Withdrawing Ingredient 1";
	    ctx.bank.withdraw(Summoner.familiar.getIngredient1(), Summoner.familiar.getWithdrawAmt1());
	    sleep(300, 1000);
	}
	if(isCorrect2()) {
	    Summoner.state = "Withdrawing Ingredient 2";
	    ctx.bank.withdraw(Summoner.familiar.getIngredient2(), Summoner.familiar.getWithdrawAmt2());
	    sleep(50, 600);
	}
	if(containsItem()) {
	    ctx.bank.close();
	}
    }
}
	
