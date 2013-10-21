package jobs;


import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Interactive;

import pSmelter.Smelter;
import utils.Node;

public class InteractFurnace extends Node {
    
    private final int FURNACE = 76293;

    public InteractFurnace(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	final GameObject furnace = ctx.objects.select().id(FURNACE).first().poll();
	final Component SKILL_INTERFACE = ctx.widgets.get(1370, 26); //pop up interface
	return !SKILL_INTERFACE.isOnScreen() && !SKILL_INTERFACE.isVisible() && !ctx.backpack.select().id(Smelter.bar.getFirstIngredient()).isEmpty() && furnace.isOnScreen();
    }

    @Override
    public void execute() {
	Smelter.state = "Interacting with Furnace";
	if (!ctx.objects.select().id(FURNACE).first().each(Interactive.doInteract("Smelt")).isEmpty()) {
	}
    }
}
			    
