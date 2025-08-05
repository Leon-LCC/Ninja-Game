package jack;

import fsm.CyclicSequence;
import fsm.ImageState;
import fsm.StateMachine;
import map.mapItems.Floor;
import media.AudioPlayer;
import model.Direction;
import model.Item;

import java.awt.*;
import java.util.List;

import static jack.Jack.Event.FALL;
import static jack.Jack.Event.RUN;

public class Walking extends CyclicSequence {
    public static final Object AUDIO_STEP5 = "step5";
    public static final Object AUDIO_STEP6 = "step6";
    public static final Object AUDIO_STEP7 = "step7";
    public static final Object AUDIO_STEP8 = "step8";
    private int stepSoundIDX = 0;
    private final Jack jack;
    private final StateMachine stateMachine;

    public Walking(Jack jack, StateMachine stateMachine, List<ImageState> states) {
        super(states);
        this.jack = jack;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (jack.isAlive()) {
            super.update();
            stepSoundIDX = (stepSoundIDX+1)%16;
            if(stepSoundIDX == 0){
                AudioPlayer.playSounds(AUDIO_STEP5);
            }else if(stepSoundIDX == 8){
                AudioPlayer.playSounds(AUDIO_STEP7);
            }
            for (Direction direction : jack.getDirections()) {
                jack.getWorld().move(jack, direction.translate());
            }
        }
        List<Item> items = jack.getWorld().getCurrentMapItems();
        Rectangle bodyEXtend = jack.getBody();
        bodyEXtend.setSize(bodyEXtend.width, bodyEXtend.height+3);
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
    public void init(){
        if(jack.getDirections().contains(Direction.RUN)){
            stateMachine.trigger(RUN);
        }
        stepSoundIDX = 0;
    }

    @Override
    public String toString() {
        return "Walking";
    }
}
