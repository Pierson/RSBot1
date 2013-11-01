package tasks;

import java.util.concurrent.Callable;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Component;

import pSoftener.Softener;

import util.Antipattern;
import util.Node;

public class Soften extends Node {
    
    Antipattern antipattern;

    public Soften(MethodContext arg0) {
	super(arg0);
	antipattern = new Antipattern(ctx);
	
    }

    @Override
    public boolean validate() {
	final Component button = ctx.widgets.get(1370, 38); //pop up interface
	return button.isOnScreen();
    }

    @Override
    public void execute() {
	final Component button = ctx.widgets.get(1370, 38); //pop up interface
	Softener.status = "Pressing button";
	if(button.click()) {
	    Softener.status = "Softening";
	    antipattern.antiban();
	    Condition.wait(new Callable<Boolean>() {
		@Override
		public Boolean call() {
		    return ctx.players.local().getAnimation() == 11490;
		}
	    });
	}
    }
}
