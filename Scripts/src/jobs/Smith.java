package jobs;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;

import pSmelter.Smelter;

import utils.Node;
import utils.SelectItem;

public class Smith extends Node {

    public Smith(MethodContext arg0) {
	super(arg0);
	selectItem = new SelectItem(ctx);
    }
    
    SelectItem selectItem;
    
    

    @Override
    public boolean validate() {
	final Component INTERFACE = ctx.widgets.get(1, 1);
	return Smelter.smith && INTERFACE.isOnScreen() && INTERFACE.isValid();
    }

    @Override
    public void execute() {
	selectItem.
	
    }

}
