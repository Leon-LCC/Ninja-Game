package zombie;

import fsm.CyclicSequence;
import fsm.ImageState;
import fsm.StateMachine;
import map.mapItems.Floor;
import model.Direction;
import model.Item;

import java.awt.*;
import java.util.List;

import static zombie.Zombie.Event.FALL;

public class Walking extends CyclicSequence {
    private final Zombie zombie;
    private final StateMachine stateMachine;

    public Walking(Zombie zombie, StateMachine stateMachine, List<ImageState> states) {
        super(states);
        this.zombie = zombie;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (zombie.isAlive()) {
            super.update();
            for (Direction direction : zombie.getDirections()) {
                 Dimension dir = direction.translate();
                zombie.getWorld().move(zombie, new Dimension(dir.width/2, dir.height));
            }
        }
        List<Item> items = zombie.getWorld().getCurrentMapItems();
        Rectangle bodyEXtend = zombie.getBody();
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
        return "Walking";
    }
}
