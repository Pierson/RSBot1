package jobs.banking;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Bank.Amount;

import pSummoner.Summoner;

import core.Job;

public class Deposit extends Job {

    public Deposit(MethodContext arg0) {
	super(arg0);
    }
    
    @Override
    public boolean validate() {
	return ctx.bank.isOpen() 
		&& !ctx.backpack.select().id(Summoner.familiar.getPouchID()).isEmpty();
    }

    @Override
    public void execute() {
	if(ctx.bank.deposit(Summoner.familiar.getPouchID(), Amount.ALL)) {
	    Summoner.state = "Depositing Inventory";
	}
    }
}
