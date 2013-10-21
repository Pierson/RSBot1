package utils;


import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Random;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;

import pSmelter.Smelter;

public class Antipattern extends MethodProvider{
    
    public Antipattern(MethodContext arg0) {
	super(arg0);
    }
    
    public void antiban() {
	int antiban = Random.nextInt(0, 6);
	switch(antiban) {
	
	case 1:
	    Smelter.state = "Adjusting Camera Angle";
	    ctx.camera.setAngle(Random.nextInt(20, 85));
	    sleep(800, 1500);
	    break;
	    
	case 2:
	    Smelter.state = "Adjusting Camera Pitch";
	    ctx.camera.setPitch(Random.nextInt(5, 80));
	    sleep(800, 1700);
	    break;
	    
	case 3: 
	    Smelter.state = "Random Mouse Movement";
	    ctx.mouse.move(Random.nextInt(5, 200), Random.nextInt(40, 400));
	    sleep(900, 1600);
	    break;
	    
	case 4:
	    Smelter.state = "Random Script Rest";
	    sleep(1000, 4000);
	    break;
	    
	case 5:
	    for(GameObject object : ctx.objects.select().nearest().first()) {
		if(object.isOnScreen()) {
		    Smelter.state = "Hovering " +object.getName();
		    object.hover();
		    sleep(750, 1500);
		}
		return;
	    }
	    break;
	    
	case 6:
	    for(Item i : ctx.backpack.select().first()) {
		Smelter.state = "Hovering " +i.getName();
		i.hover();
		sleep(500, 1400);
	    }
	    break;
	}
    }
}
