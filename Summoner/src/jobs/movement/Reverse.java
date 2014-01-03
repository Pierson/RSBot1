package jobs.movement;

import java.util.concurrent.Callable;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Tile;

import pSummoner.Summoner;
import core.Job;

public class Reverse extends Job {

    public Reverse(MethodContext ctx) {
        super(ctx);
    }
    
	private final Tile[] path = new Tile[] {
		new Tile(2931, 3449, 0),
		new Tile(2923, 3448, 0),
		new Tile(2923, 3440, 0),
		new Tile(2923, 3433, 0),
		new Tile(2922, 3427, 0),
		new Tile(2917, 3423, 0),
		new Tile(2912, 3420, 0),
		new Tile(2908, 3415, 0),
		new Tile(2902, 3415, 0),
		new Tile(2895, 3415, 0),
		new Tile(2887, 3417, 0),
		new Tile(2881, 3417, 0),
		new Tile(2877, 3417, 0)
};
    
    private boolean containsItem() {
	return ctx.backpack.select().id(Summoner.familiar.getIngredient1()).count() == Summoner.familiar.getWithdrawAmt1()
		&& ctx.backpack.select().id(Summoner.familiar.getIngredient2()).count() == Summoner.familiar.getWithdrawAmt2();
    }

    @Override
    public boolean validate() {
	return !ctx.bank.isOnScreen() && !containsItem();
    }
    
    @Override
    public void execute() {
	Summoner.state = "Returning from Obelisk";
	ctx.movement.newTilePath(path).randomize(-1, 1).traverse();
	Condition.wait(new Callable<Boolean>() {
	    @Override
	    public Boolean call() {
		return ctx.players.local().isInMotion();
	    }
	}, 1000, 30);
    }
}
