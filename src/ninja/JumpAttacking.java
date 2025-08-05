package ninja;

import fsm.*;
import media.AudioPlayer;
import model.Direction;
import model.Sprite;
import model.World;

import java.awt.*;
import java.util.List;

import static ninja.Ninja.Event.FALL;

public class JumpAttacking extends Sequence {
    public static final String AUDIO_AIR_SWING = "swing3";
    private final Ninja ninja;
    private final StateMachine stateMachine;
    private int Gravity = 2;
    private int Speed = 20;

    public JumpAttacking(Ninja ninja, StateMachine stateMachine, List<? extends State> states) {
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
            if(Speed >= 0){
                ninja.getWorld().move(ninja, new Dimension(0, -Speed));
                Speed -= Gravity;
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
        currentPosition = 0;
        Speed = 20;
        this.ninja.jumpAttackCD = true;
        AudioPlayer.playSounds(AUDIO_AIR_SWING);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        stateMachine.trigger(FALL);
    }
}
