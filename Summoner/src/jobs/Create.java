package jobs;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.GameObject;

import pSummoner.Summoner;
import core.*;


public class Create extends Job {
    
    public Create(MethodContext ctx) {
        super(ctx);
    }
    
    private boolean containsItem() {
	return ctx.backpack.select().id(Summoner.familiar.getIngredient1()).count() == Summoner.familiar.getWithdrawAmt1()
		&& ctx.backpack.select().id(Summoner.familiar.getIngredient2()).count() == Summoner.familiar.getWithdrawAmt2();
    }
    
    final Component select = ctx.widgets.get(1370, 27);

    @Override
    public boolean validate() {
	GameObject obelisk = ctx.objects.select().id(67036).poll(); 
	return obelisk.isOnScreen() && containsItem() && !select.isOnScreen();
    }

    @Override
    public void execute() {
	Summoner.state = "Interacting with Obelisk";
	GameObject obelisk = ctx.objects.select().id(67036).poll();
	if(obelisk.interact("Infuse-pouch")) {
	}
    }
}