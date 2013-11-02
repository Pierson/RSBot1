package tasks;

import org.powerbot.script.methods.MethodContext;
import pSoftener.Softener;

import util.Node;

public class BankOpen extends Node{
    
    private final int softClay = 1761;

    public BankOpen(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	return ctx.bank.isOnScreen() 
		&& ctx.backpack.select().id(softClay).count() == 28;
    }

    @Override
    public void execute() {
	Softener.status = "Opening Bank";
	if(ctx.bank.open()) {
	    Softener.status = "Depositing Inventory";
	    if(ctx.bank.depositInventory()) {
		sleep(50, 600);
	    }
	}
    }
}