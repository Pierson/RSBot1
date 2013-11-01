package nodes;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;

import prayer.PBurier;

import utils.Node;

public class Bury extends Node {

    public Bury(MethodContext arg0) {
	super(arg0);
    }

    @Override
    public boolean validate() {
	return !ctx.backpack.select().id(PBurier.bone.getBone()).isEmpty() && !ctx.bank.isOpen();
    }

    @Override
    public void execute() {
	PBurier.status = "Burying Bones...";
	for(Item bone : ctx.backpack.select().id(PBurier.bone.getBone())) {
	    if(bone.interact("Bury")) {
		sleep(200, 1300);
	    }
	}
    }
}
