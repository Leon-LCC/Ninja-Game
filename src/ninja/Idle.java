package ninja;

import fsm.CyclicSequence;
import fsm.ImageState;
import fsm.StateMachine;
import map.mapItems.Floor;
import model.Item;

import java.awt.*;
import java.util.List;

import static ninja.Ninja.Event.FALL;

public class Idle extends CyclicSequence {
    private final Ninja ninja;
    private final StateMachine stateMachine;
    public Idle(Ninja ninja, StateMachine stateMachine, List<ImageState> states) {
        super(states);
        this.ninja = ninja;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update(){
        super.update();
        List<Item> items = ninja.getWorld().getCurrentMapItems();
        Rectangle bodyEXtend = ninja.getBody();
        bodyEXtend.setSize(bodyEXtend.width, bodyEXtend.height+1);
        boolean OnTheFloor = false;
        for(Item item : items){
            if(item instanceof Floor){
                if (bodyEXtend.intersects(item.getRange())) {
                    OnTheFloor = true;
                    break;
                }
            }
        }
        if(!OnTheFloor){
            stateMachine.trigger(FALL);
        }
    }

    @Override
    public String toString() {
        return "Idle";
    }
}
