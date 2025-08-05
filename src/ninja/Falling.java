package ninja;

import fsm.CyclicSequence;
import fsm.State;
import fsm.StateMachine;
import map.mapItems.Floor;
import media.AudioPlayer;
import model.Direction;
import model.Item;

import java.awt.*;
import java.util.List;

import static ninja.Ninja.Event.WALK;

public class Falling extends CyclicSequence {
    public static final String AUDIO_LAND = "jumpland";
    private final Ninja ninja;
    private final StateMachine stateMachine;
    private boolean OnTheFloor = false;
    private int Gravity = 4;
    private int Speed = 4;

    public Falling(Ninja ninja, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.ninja = ninja;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (ninja.isAlive()) {
            super.update();
            for (Direction direction : ninja.getDirections()) {
                ninja.getWorld().move(ninja, direction.translate());
            }
            List<Item> items = ninja.getWorld().getCurrentMapItems();
            Rectangle bodyEXtend = ninja.getBody();
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
                ninja.getWorld().move(ninja, new Dimension(0, Speed));
                Speed += Gravity;
            }else{
                currentPosition = 0;
                OnTheFloor = false;
                Speed = 4;
                stateMachine.reset();
                ninja.jumpAttackCD = false;
                ninja.jumpThrowCD = false;
                AudioPlayer.playSounds(AUDIO_LAND);
                if(!ninja.getDirections().isEmpty()) {
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