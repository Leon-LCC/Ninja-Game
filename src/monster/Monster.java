package monster;

import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import media.AudioPlayer;
import model.Direction;
import model.HealthPointSpriteForMonster;
import model.SpriteShape;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static fsm.FiniteStateMachine.Transition.from;
import static java.lang.Math.random;
import static monster.Monster.Event.*;
import static utils.ImageStateUtils.imageStatesFromFolder;


public class Monster extends HealthPointSpriteForMonster {
    public final int MONSTER_HP;
    private final SpriteShape shape;
    private final FiniteStateMachine fsm;
    private final Set<Direction> directions = new CopyOnWriteArraySet<>();
    private final int damage;
    private final Gem gem;
    public static final Object AUDIO_SLIMESCREAM = "slime_scream";

    public enum Event {
        WALK, STOP, FALL
    }

    public Monster(int hp, int damage, Point location, Gem gem) {
        super(hp);
        this.MONSTER_HP = hp;
        this.damage = damage;
        this.location = location;
        this.gem = gem;
        shape = new SpriteShape(new Dimension(52, 47),
                new Dimension(2, 2), new Dimension(48, 43));
        fsm = new FiniteStateMachine();

        ImageRenderer imageRenderer = new MonsterImageRenderer(this,0, 0, 0, 0);
        State idle = new WaitingPerFrame(4,
                new Idle(this, fsm, imageStatesFromFolder("assets/slime/idle", imageRenderer)));
        State walking = new WaitingPerFrame(2,
                new Walking(this, fsm, imageStatesFromFolder("assets/slime/walk", imageRenderer)));
        State falling = new WaitingPerFrame(2,
                new Falling(this, fsm, imageStatesFromFolder("assets/slime/walk", imageRenderer)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(walking).when(FALL).to(falling));
        fsm.addTransition(from(walking).when(STOP).to(idle));
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
        if(random() < 0.001){
            AudioPlayer.playSounds(AUDIO_SLIMESCREAM);
        }
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

    public Gem getGem() {
        return gem;
    }
}
