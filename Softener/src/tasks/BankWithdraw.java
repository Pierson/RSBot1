package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Bank.Amount;

import pSoftener.Softener;

import util.Node;

public class BankWithdraw extends Node{
    
    private final int clay = 434;

    public BankWithdraw(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	if(ctx.bank.isOpen()) {
	    System.out.println("TrUE");
	}
	return ctx.bank.isOpen() && ctx.backpack.select().id(clay).count() != 28;
    }

    @Override
    public void execute() {
	if(ctx.bank.withdraw(clay, Amount.ALL)) {
	    sleep(100, 625);
	    Softener.status = "Withdrawing Clay";
	    if(ctx.backpack.select().id(clay).count() == 28) {
		Softener.status = "Closing Bank";
		if(ctx.bank.close()) {
		}
	    }
	}
    }
}

