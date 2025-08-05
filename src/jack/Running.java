package jack;

import fsm.CyclicSequence;
import fsm.ImageState;
import fsm.StateMachine;
import map.mapItems.Floor;
import media.AudioPlayer;
import model.Direction;
import model.Item;
import model.Sprite;
import model.World;
import ninja.Ninja;

import java.awt.*;
import java.util.List;

import static jack.Jack.Event.*;

public class Running extends CyclicSequence {
    public static final Object AUDIO_STEP5 = "step5";
    public static final Object AUDIO_STEP6 = "step6";
    public static final Object AUDIO_STEP7 = "step7";
    public static final Object AUDIO_STEP8 = "step8";
    private int stepSoundIDX = 0;
    private final Jack jack;
    private final StateMachine stateMachine;

    public Running(Jack jack, StateMachine stateMachine, List<ImageState> states) {
        super(states);
        this.jack = jack;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (jack.isAlive()) {
            super.update();
            stepSoundIDX = (stepSoundIDX+1)%12;
            if(stepSoundIDX == 0){
                AudioPlayer.playSounds(AUDIO_STEP5);
            }else if(stepSoundIDX == 6){
                AudioPlayer.playSounds(AUDIO_STEP7);
            }
            for (Direction direction : jack.getDirections()) {
                int x = direction.translate().width;
                int y = direction.translate().height;
                jack.getWorld().move(jack, new Dimension(2*x,y));
            }
            effectDamage();
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
        if(!jack.getDirections().contains(Direction.RUN) ||
           !(jack.getDirections().contains(Direction.LEFT) || jack.getDirections().contains(Direction.RIGHT))){
            stateMachine.reset();
            if(jack.getDirections().contains(Direction.LEFT) || jack.getDirections().contains(Direction.RIGHT)){
                stateMachine.trigger(WALK);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Rectangle damageArea = damageArea();
        //g.setColor(Color.BLUE);
        //g.drawRect(damageArea.x, damageArea.y, damageArea.width, damageArea.height);
    }

    private void effectDamage() {
        World world = jack.getWorld();
        Rectangle damageArea = damageArea();
        var sprites = world.getCurrentMapSprites(damageArea);
        boolean hasClash = false;
        for (Sprite sprite : sprites) {
            if (jack != sprite && sprite instanceof Ninja) {
                sprite.onDamaged(damageArea, jack.getDamage(), sprite.getGem());
                hasClash = true;
            }
        }
    }

    private Rectangle damageArea() {
        return  jack.getArea(new Dimension(jack.getBody().width+20, 0),
                new Dimension(jack.getBody().width/4, jack.getBody().height*4/3));
    }

    @Override
    public void init(){
        stepSoundIDX = 0;
    }

    @Override
    public String toString() {
        return "Walking";
    }
}
