package jack;

import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import model.Direction;
import model.HealthPointSprite;
import model.SpriteShape;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static fsm.FiniteStateMachine.Transition.from;
import static jack.Jack.Event.*;
import static utils.ImageStateUtils.imageStatesFromFolder;


public class Jack extends HealthPointSprite {
    public static final int JACK_HP = 5000;
    private final SpriteShape shape;
    private final FiniteStateMachine fsm;
    private final Set<Direction> directions = new CopyOnWriteArraySet<>();
    private int damage;

    public enum Event {
        WALK, RUN, STOP, DAMAGED, JUMP, FALL, SLIDE, DEAD
    }

    public Jack(int damage, Point location, int size) {
        super(JACK_HP);
        this.damage = damage;
        this.location = location;
        shape = new SpriteShape(new Dimension(200*size, 254*size),
                new Dimension(15*size, 40*size), new Dimension(150*size, 214*size));
        fsm = new FiniteStateMachine();


        ImageRenderer imageRenderer = new JackImageRenderer(this,0, 0, 10*size, 0);
        ImageRenderer imageRendererSlide = new JackImageRenderer(this, -10*size, -20*size, 55*size, -50*size);
        ImageRenderer imageRendererDead = new JackImageRenderer(this, 100*size, 100*size, -10*size, 30*size);

        State idle = new WaitingPerFrame(4,
                new Idle(this, fsm, imageStatesFromFolder("assets/jack/idle", imageRenderer)));
        State walking = new WaitingPerFrame(3,
                new Walking(this, fsm, imageStatesFromFolder("assets/jack/walking", imageRenderer)));
        State running = new WaitingPerFrame(3,
                new Running(this, fsm, imageStatesFromFolder("assets/jack/running", imageRenderer)));
        State jumping = new WaitingPerFrame(3,
                new Jumping(this, fsm, imageStatesFromFolder("assets/jack/jumping", imageRenderer)));
        State falling = new WaitingPerFrame(3,
                new Falling(this, fsm, imageStatesFromFolder("assets/jack/falling", imageRenderer)));
        State sliding = new WaitingPerFrame(4,
                new Sliding(this, fsm, imageStatesFromFolder("assets/jack/slide", imageRendererSlide)));
        State dying = new WaitingPerFrame(8,
                new Dead(this, fsm, imageStatesFromFolder("assets/jack/dead", imageRendererDead)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(idle).when(RUN).to(running));
        fsm.addTransition(from(idle).when(JUMP).to(jumping));
        fsm.addTransition(from(idle).when(SLIDE).to(sliding));
        fsm.addTransition(from(idle).when(FALL).to(falling));
        fsm.addTransition(from(idle).when(DEAD).to(dying));
        fsm.addTransition(from(walking).when(STOP).to(idle));
        fsm.addTransition(from(walking).when(JUMP).to(jumping));
        fsm.addTransition(from(walking).when(FALL).to(falling));
        fsm.addTransition(from(walking).when(RUN).to(running));
        fsm.addTransition(from(walking).when(SLIDE).to(sliding));
        fsm.addTransition(from(running).when(STOP).to(idle));
        fsm.addTransition(from(running).when(FALL).to(falling));
        fsm.addTransition(from(running).when(JUMP).to(jumping));
        fsm.addTransition(from(running).when(SLIDE).to(sliding));
        fsm.addTransition(from(jumping).when(FALL).to(falling));
        fsm.addTransition(from(falling).when(WALK).to(walking));
        fsm.addTransition(from(falling).when(RUN).to(running));
    }

    public int getDamage() {
        return damage;
    }

    public void throwing(){}

    public void attack(){}

    public void move(Direction direction) {
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            face = direction;
        }
        if (direction == Direction.JUMP) {
            fsm.trigger(JUMP);
        }else if (direction == Direction.SLIDE) {
            fsm.trigger(SLIDE);
        }else if(direction == Direction.RUN){
            if(directions.contains(Direction.RIGHT) || directions.contains(Direction.LEFT)) {
                this.directions.add(direction);
                fsm.trigger(RUN);
            }
        }else if (!directions.contains(direction)) {
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
    }

    @Override
    protected void deadAnimate() {
        fsm.reset();
        fsm.trigger(DEAD);
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
