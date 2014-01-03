package jobs.movement;

import java.util.concurrent.Callable;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;

import pSummoner.Summoner;
import core.Job;

public class Traverse extends Job {
    
    public Traverse(MethodContext ctx) {
        super(ctx);
    }
    
	private final TilePath path = new TilePath(ctx, new Tile[] {
		new Tile(2877, 3418, 0),
		new Tile(2882, 3417, 0),
		new Tile(2887, 3417, 0),
		new Tile(2892, 3415, 0),
		new Tile(2897, 3415, 0),
		new Tile(2902, 3415, 0),
		new Tile(2907, 3416, 0),
		new Tile(2912, 3422, 0),
		new Tile(2915, 3424, 0),
		new Tile(2919, 3425, 0),
		new Tile(2923, 3433, 0),
		new Tile(2923, 3436, 0),
		new Tile(2923, 3440, 0),
		new Tile(2923, 3444, 0),
		new Tile(2930, 3448, 0),
});
    
    
    private boolean containsItem() {
	return ctx.backpack.select().id(Summoner.familiar.getIngredient1()).count() == Summoner.familiar.getWithdrawAmt1()
		&& ctx.backpack.select().id(Summoner.familiar.getIngredient2()).count() == Summoner.familiar.getWithdrawAmt2();
    }

    @Override
    public boolean validate() {
	return containsItem()
		&& !ctx.bank.isOpen();
    }

    @Override
    public void execute() {
	Summoner.state = "Walking to Obelisk";
	path.traverse();
	Condition.wait(new Callable<Boolean>() {
	    @Override
	    public Boolean call() {
		return !ctx.players.local().isInMotion();
	    }
	}, 1000, 30);
    }
}