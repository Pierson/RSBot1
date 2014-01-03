package jobs.interfaces;

import java.util.concurrent.Callable;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Component;

import pSummoner.Summoner;
import utils.Antipattern;

import core.Job;

public class InterfaceHandler extends Job {

    public InterfaceHandler(MethodContext arg0) {
	super(arg0);
	random = new Antipattern(ctx);
    }
    
    private Antipattern random;
    
    final Component button = ctx.widgets.get(1370, 38);
    final Component select = ctx.widgets.get(1370, 27);

    @Override
    public boolean validate() {
	return select.isOnScreen();
    }

    @Override
    public void execute() {
	if(button.click()) {
	    random.antiban();
	    Summoner.state = "Selecting Interface";
	    Condition.wait(new Callable<Boolean>() {
		@Override
		public Boolean call() {
		    return !select.isOnScreen();
		}
	    }, 1000, 5);
	}
    }
}