package jobs;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.util.Timer;

import pSmelter.Smelter;

import utils.Antipattern;
import utils.Node;

@SuppressWarnings("deprecation")
public class Smelt extends Node {

    public Smelt(MethodContext arg0) {
	super(arg0);
	antipattern = new Antipattern(ctx);
    }
    
    Antipattern antipattern;

    @Override
    public boolean validate() {
	final Component button = ctx.widgets.get(1370, 38); //pop up interface
	return button.isOnScreen();
    }
    
    @Override
    public void execute() {
	final Component button = ctx.widgets.get(1370, 38); //pop up interface
	Smelter.state = "Pressing button";
	if(button.getText().contains("Smelt")) {
	    if(button.click()) {
		Timer t = new Timer(2500);
		while(t.isRunning()) {
		    if(ctx.players.local().getAnimation() == 3243) {
			antipattern.antiban();
			Smelter.state = "Smelting...";
			t.reset();
		    }
		}
	    }
	}
    }
}

