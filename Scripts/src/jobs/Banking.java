package jobs;

import org.powerbot.script.methods.Bank;
import org.powerbot.script.methods.MethodContext;

import pSmelter.Smelter;

import utils.Node;

public class Banking extends Node {
    
    public Banking(MethodContext ctx) {
        super(ctx);       
    }

    @Override
    public boolean validate() {
	return (ctx.backpack.isEmpty() || !ctx.backpack.select().id(Smelter.bar.getBar()).isEmpty() || ctx.backpack.select().id(Smelter.bar.getFirstIngredient()).count() != Smelter.bar.getWithdrawAmt() || ctx.backpack.select().id(Smelter.bar.getSecondIngredient()).count() != Smelter.bar.getSecondWithdrawAmt()) && ctx.bank.isOnScreen();
    }

    @Override
    public void execute() {
	Smelter.state = "Banking Inventory";
        if(ctx.bank.open()) {
            if(ctx.backpack.select().id(Smelter.bar.getFirstIngredient()).count() != Smelter.bar.getWithdrawAmt() || ctx.backpack.select().id(Smelter.bar.getSecondIngredient()).count() != Smelter.bar.getSecondWithdrawAmt()) {
        	if(ctx.bank.depositInventory()) {
        	    sleep(50, 500);
        	    Smelter.state = "Depositing Inventory";
        	}
            }
            if(ctx.backpack.isEmpty()) {
        	Smelter.state = "Withdrawing First Ore";
        	if(ctx.bank.withdraw(Smelter.bar.getFirstIngredient(), Smelter.bar.getWithdrawAmt())) {
        	    Smelter.state = "Withdrawing Second Ore";
        	    if(ctx.bank.withdraw(Smelter.bar.getSecondIngredient(), Bank.Amount.ALL)) {
        		ctx.bank.close();
        	    }
        	}
            }
        }
    }
}
        	
