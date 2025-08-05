package ninja;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import media.AudioPlayer;
import model.Sprite;
import model.World;
import monster.Monster;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ninja.Ninja.Event.WALK;


public class Attacking extends Sequence {
    public static final String AUDIO_SWORD_SWING_1 = "swing1";
    private final Ninja ninja;
    private final StateMachine stateMachine;
    private final Set<Integer> damagingStateNumbers = new HashSet<>(List.of(6));

    public Attacking(Ninja ninja, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.ninja = ninja;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (ninja.isAlive()) {
            super.update();
            if (damagingStateNumbers.contains(currentPosition)) {
               effectDamage();
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
        World world = ninja.getWorld();
        Rectangle damageArea = damageArea();
        var sprites = world.getCurrentMapSprites(damageArea);
        boolean hasClash = false;
        for (Sprite sprite : sprites) {
            if (ninja != sprite) {
                sprite.onDamaged(damageArea, ninja.getDamage(), sprite.getGem());
                hasClash = true;
            }
        }
    }

    private Rectangle damageArea() {
        return ninja.getArea(new Dimension(55, 35),
                new Dimension(32, 52));
    }

    @Override
    public void init(){
        AudioPlayer.playSounds(AUDIO_SWORD_SWING_1);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        if(!ninja.getDirections().isEmpty()) {
            stateMachine.reset();
            stateMachine.trigger(WALK);
        }else{
            stateMachine.reset();
        }
    }
}
