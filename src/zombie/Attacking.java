package zombie;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import media.AudioPlayer;
import model.Sprite;
import model.World;
import ninja.Ninja;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static zombie.Zombie.Event.WALK;

public class Attacking extends Sequence {
    public static final Object AUDIO_ZOMBIEATTACK = "zombie_attack";
    private final Zombie zombie;
    private final StateMachine stateMachine;
    private final Set<Integer> damagingStateNumbers = new HashSet<>(List.of(6));

    public Attacking(Zombie zombie, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.zombie = zombie;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (zombie.isAlive()) {
            super.update();
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
        World world = zombie.getWorld();
        Rectangle damageArea = damageArea();
        var sprites = world.getCurrentMapSprites(damageArea);
        boolean hasClash = false;
        for (Sprite sprite : sprites) {
            if (zombie != sprite && sprite instanceof Ninja) {
                sprite.onDamaged(damageArea, zombie.getDamage(), sprite.getGem());
                hasClash = true;
            }
        }
    }

    private Rectangle damageArea() {
        return zombie.getArea(new Dimension(55, 35),
                new Dimension(25, 54));
    }

    @Override
    public void init(){
        AudioPlayer.playSounds(AUDIO_ZOMBIEATTACK);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        if(!zombie.getDirections().isEmpty()) {
            stateMachine.reset();
            stateMachine.trigger(WALK);
        }else{
            stateMachine.reset();
        }
    }
}
