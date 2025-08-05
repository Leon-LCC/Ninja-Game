package jack;

import fsm.CyclicSequence;
import fsm.ImageState;
import fsm.StateMachine;
import map.mapItems.Floor;
import model.Item;

import java.awt.*;
import java.util.List;

import static jack.Jack.Event.FALL;

public class Idle extends CyclicSequence {
    private final Jack jack;
    private final StateMachine stateMachine;

    public Idle(Jack jack, StateMachine stateMachine, List<ImageState> states) {
        super(states);
        this.jack = jack;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update(){
        super.update();
        boolean OnTheFloor = false;
        List<Item> items = jack.getWorld().getCurrentMapItems();
        Rectangle bodyEXtend = jack.getBody();
        bodyEXtend.setSize(bodyEXtend.width, bodyEXtend.height+1);
        for(Item item : items){
            if(item instanceof Floor){
                if (bodyEXtend.intersects(item.getRange())) {
                    OnTheFloor = true;
                    break;
                }
            }
        }
        if (!OnTheFloor){
            stateMachine.trigger(FALL);
        }
    }

    @Override
    public String toString() {
        return "Idle";
    }
}
