package ninja;

import fsm.CyclicSequence;
import fsm.ImageState;
import fsm.StateMachine;
import map.mapItems.Floor;
import media.AudioPlayer;
import model.Direction;
import model.Item;

import java.awt.*;
import java.util.List;

import static ninja.Ninja.Event.FALL;

public class Walking extends CyclicSequence {
    public static final String AUDIO_STEP1 = "step1";
    public static final String AUDIO_STEP2 = "step2";
    public static final String AUDIO_STEP3 = "step3";
    public static final String AUDIO_STEP4 = "step4";
    private int stepSoundIDX = 0;
    private final Ninja ninja;
    private final StateMachine stateMachine;

    public Walking(Ninja ninja, StateMachine stateMachine, List<ImageState> states) {
        super(states);
        this.ninja = ninja;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (ninja.isAlive()) {
            super.update();
            stepSoundIDX = (stepSoundIDX+1)%32;
            if(stepSoundIDX == 0){
                AudioPlayer.playSounds(AUDIO_STEP1);
            }else if(stepSoundIDX == 8){
                AudioPlayer.playSounds(AUDIO_STEP2);
            }else if(stepSoundIDX == 16){
                AudioPlayer.playSounds(AUDIO_STEP3);
            }else if(stepSoundIDX == 24){
                AudioPlayer.playSounds(AUDIO_STEP4);
            }
            for (Direction direction : ninja.getDirections()) {
                ninja.getWorld().move(ninja, direction.translate());
            }
        }
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
    public void init(){
        stepSoundIDX = 0
;    }

    @Override
    public String toString() {
        return "Walking";
    }
}
