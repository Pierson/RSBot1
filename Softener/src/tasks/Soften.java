package tasks;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Timer;
import org.powerbot.script.wrappers.Component;

import pSoftener.Softener;

import util.Antipattern;
import util.Node;

@SuppressWarnings("deprecation")
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
	    sleep(600, 1600);
	    Softener.status = "Softening Clay";
	    Timer t = new Timer(2000);
	    while(t.isRunning()) {
		if(ctx.players.local().getAnimation() == 11490) {
		    antipattern.antiban();
		    t.reset();
		}
	    }
	}
    }
}
