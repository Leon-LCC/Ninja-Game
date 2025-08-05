package jack;

import fsm.CyclicSequence;
import fsm.State;
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

public class Falling extends CyclicSequence {
    public static final Object AUDIO_LAND = "land";
    private final Jack jack;
    private final StateMachine stateMachine;
    private boolean OnTheFloor = false;
    private int Gravity = 2;
    private int Speed = 2;

    public Falling(Jack jack, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.jack = jack;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (jack.isAlive()) {
            super.update();
            for (Direction direction : jack.getDirections()) {
                jack.getWorld().move(jack, direction.translate());
            }
            List<Item> items = jack.getWorld().getCurrentMapItems();
            Rectangle bodyEXtend = jack.getBody();
            bodyEXtend.setSize(bodyEXtend.width, bodyEXtend.height+3);
            for(Item item : items){
                if(item instanceof Floor){
                    if (bodyEXtend.intersects(item.getRange())) {
                        OnTheFloor = true;
                        break;
                    }
                }
            }
            if (!OnTheFloor){
                jack.getWorld().move(jack, new Dimension(0, Speed));
                Speed += Gravity;
                effectDamage();
            }else{
                AudioPlayer.playSounds(AUDIO_LAND);
                currentPosition = 0;
                OnTheFloor = false;
                Speed = 3;
                stateMachine.reset();
                if(!jack.getDirections().isEmpty()) {
                    stateMachine.trigger(WALK);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        //Rectangle damageArea = damageArea();
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
        Rectangle bodyEXtend = jack.getBody();
        bodyEXtend.setLocation(bodyEXtend.x-20, bodyEXtend.y+bodyEXtend.height-50);
        bodyEXtend.setSize(bodyEXtend.width+40, 50);
        return bodyEXtend;
    }

    @Override
    public void init(){
        super.init();
        Speed = 2;
    }
}