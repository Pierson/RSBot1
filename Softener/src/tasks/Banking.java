package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Bank.Amount;

import pSoftener.Softener;

import util.Node;

public class Banking extends Node{
    
    private final int softClay = 1761;
    private final int clay = 434;

    public Banking(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	return ctx.bank.isOnScreen() && (ctx.backpack.isEmpty() || ctx.backpack.select().id(softClay).count() == 28);
    }

    @Override
    public void execute() {
	Softener.status = "Opening Bank";
	if(ctx.bank.open()) {
	    if(ctx.backpack.select().id(clay).isEmpty()) {
		if(ctx.bank.withdraw(clay, Amount.ALL)) {
		    Softener.status = "Withdrawing Clay";
		    if(ctx.backpack.count() == 28) {
			Softener.status = "Closing Bank";
			if(ctx.bank.close()) {
			}
		    }
		}
	    }
	}
    }
}
