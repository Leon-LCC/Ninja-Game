package zombie;

import fsm.CyclicSequence;
import fsm.State;
import fsm.StateMachine;
import map.mapItems.Floor;
import model.Direction;
import model.Item;

import java.awt.*;
import java.util.List;

import static zombie.Zombie.Event.WALK;

public class Falling extends CyclicSequence {
    private final Zombie zombie;
    private final StateMachine stateMachine;
    private boolean OnTheFloor = false;
    private int Gravity = 4;
    private int Speed = 4;

    public Falling(Zombie zombie, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.zombie = zombie;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (zombie.isAlive()) {
            super.update();
            for (Direction direction : zombie.getDirections()) {
                zombie.getWorld().move(zombie, direction.translate());
            }
            List<Item> items = zombie.getWorld().getCurrentMapItems();
            Rectangle bodyEXtend = zombie.getBody();
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
                zombie.getWorld().move(zombie, new Dimension(0, Speed));
                Speed += Gravity;
            }else{
                currentPosition = 0;
                OnTheFloor = false;
                Speed = 4;
                stateMachine.reset();
                if(!zombie.getDirections().isEmpty()) {
                    stateMachine.trigger(WALK);
                }
            }
        }
    }

    @Override
    public void init(){
        super.init();
        Speed = 4;
    }
}