package jack;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import media.AudioPlayer;
import model.Direction;
import model.Sprite;
import model.World;
import ninja.Ninja;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jack.Jack.Event.WALK;


public class Sliding extends Sequence {
    public static final Object AUDIO_SLIDE = "slide";
    private final Jack jack;
    private final StateMachine stateMachine;
    private final Set<Integer> damagingStateNumbers = new HashSet<>(List.of(6));
    private Direction slideToward;

    public Sliding(Jack jack, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.jack = jack;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (jack.isAlive()) {
            super.update();
            if(slideToward == Direction.LEFT){
                jack.getWorld().move(jack, new Dimension(-10, 0));
            }else{
                jack.getWorld().move(jack, new Dimension(10, 0));
            }
            if (damagingStateNumbers.contains(currentPosition)) {
               effectDamage();
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
        return jack.getArea(new Dimension(jack.getBody().width,jack.getBody().height/2),
                new Dimension(jack.getBody().width/2, jack.getBody().height*2/3));
    }

    @Override
    public void init(){
        slideToward = jack.getFace();
        AudioPlayer.playSounds(AUDIO_SLIDE);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        if(!jack.getDirections().isEmpty()) {
            stateMachine.reset();
            stateMachine.trigger(WALK);
        }else{
            stateMachine.reset();
        }
    }
}
