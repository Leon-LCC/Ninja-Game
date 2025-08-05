package zombie;

import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import media.AudioPlayer;
import model.Direction;
import model.HealthPointSprite;
import model.SpriteShape;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static fsm.FiniteStateMachine.Transition.from;
import static java.lang.Math.random;
import static zombie.Zombie.Event.*;
import static utils.ImageStateUtils.imageStatesFromFolder;


public class Zombie extends HealthPointSprite {
    public static final int ZOMBIE_HP = 700;
    public static final Object AUDIO_ZOMBIESCREAM1 = "zombie1";
    public static final Object AUDIO_ZOMBIESCREAM2 = "zombie2";
    public static final Object AUDIO_ZOMBIEDEAD = "zombie3";
    private final SpriteShape shape;
    private final FiniteStateMachine fsm;
    private final Set<Direction> directions = new CopyOnWriteArraySet<>();
    private int damage;

    public enum Event {
        WALK, STOP, ATTACK, DAMAGED, FALL, DEAD
    }

    public Zombie(int damage, Point location, int type) {
        super(ZOMBIE_HP);
        this.damage = damage;
        this.location = location;
        shape = new SpriteShape(new Dimension(70, 90),
                new Dimension(10, 8), new Dimension(50, 82));
        fsm = new FiniteStateMachine();

        int female = (type==2)? 1 : 0;
        ImageRenderer imageRenderer = new ZombieImageRenderer(this,2+6*female, 7+3*female, -7, 10);
        ImageRenderer imageRendererDead = new ZombieImageRenderer(this, 30, 30, 0, 10);
        State idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/Zombie/"+type+"/idle", imageRenderer)));
        State walking = new WaitingPerFrame(2,
                new Walking(this, fsm, imageStatesFromFolder("assets/Zombie/"+type+"/walking", imageRenderer)));
        State attacking = new WaitingPerFrame(4,
                new Attacking(this, fsm, imageStatesFromFolder("assets/Zombie/"+type+"/attack", imageRenderer)));
        State falling = new WaitingPerFrame(2,
                new Falling(this, fsm, imageStatesFromFolder("assets/Zombie/"+type+"/walking", imageRenderer)));
        State dying = new WaitingPerFrame(2,
                new Dead(this, fsm, imageStatesFromFolder("assets/Zombie/"+type+"/dead", imageRendererDead)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(idle).when(ATTACK).to(attacking));
        fsm.addTransition(from(idle).when(DEAD).to(dying));
        fsm.addTransition(from(walking).when(STOP).to(idle));
        fsm.addTransition(from(walking).when(ATTACK).to(attacking));
        fsm.addTransition(from(walking).when(FALL).to(falling));
    }

    public void attack() {
        fsm.trigger(ATTACK);
    }

    public void throwing() {
    }

    public int getDamage() {
        return damage;
    }

    public void move(Direction direction) {
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            face = direction;
        }
        if (!directions.contains(direction)) {
            this.directions.add(direction);
            fsm.trigger(WALK);
        }
    }

    public void stop(Direction direction) {
        directions.remove(direction);
        if (directions.isEmpty()) {
            fsm.trigger(STOP);
        }
    }

    public void update() {
        fsm.update();
        if(random() < 0.005){
            AudioPlayer.playSounds(AUDIO_ZOMBIESCREAM1);
        }else if(random() > 0.995){
            AudioPlayer.playSounds(AUDIO_ZOMBIESCREAM2);
        }
    }

    @Override
    protected void deadAnimate() {
        fsm.reset();
        fsm.trigger(DEAD);
        AudioPlayer.playSounds(AUDIO_ZOMBIEDEAD);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        fsm.render(g);
    }

    public Set<Direction> getDirections() {
        return directions;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }

    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }

    public void addDamage(int n) {
        this.damage += n;
    }
}
