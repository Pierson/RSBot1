package nodes;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.Bank.Amount;

import prayer.PBurier;

import utils.Node;

public class Bank extends Node {

    public Bank(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	return ctx.backpack.isEmpty() && ctx.bank.isOnScreen();
    }

    @Override
    public void execute() {
	PBurier.status = "Opening Bank...";
	if(ctx.bank.open()) {
	    if(ctx.backpack.count() < 28) {
		if(ctx.bank.withdraw(PBurier.bone.getBone(), Amount.ALL)) {
		    PBurier.status = "Withdrawing Bones...";
		    if(ctx.backpack.count() == 28) {
			if(ctx.bank.close()) {
			}
		    }
		}
	    }
	}
    }
}
