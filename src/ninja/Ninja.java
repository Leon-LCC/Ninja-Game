package ninja;

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
import static ninja.Ninja.Event.*;
import static utils.ImageStateUtils.imageStatesFromFolder;


public class Ninja extends HealthPointSprite {
    public static final int NINJA_HP = 5000;
    private final SpriteShape shape;
    private final FiniteStateMachine fsm;
    private final Set<Direction> directions = new CopyOnWriteArraySet<>();
    private int damage;
    private int LongRangedamage;
    public boolean jumpAttackCD = false;
    public boolean jumpThrowCD = false;
    public String buff = "";
    public int buffTime = 0;

    public enum Event {
        WALK, STOP, ATTACK, THROW, DAMAGED, JUMP, FALL, DEAD, REVIVE
    }

    public Ninja(int damage, int LongRangedamage, Point location, int type) {
        this(damage, LongRangedamage, location, type, NINJA_HP);
    }

    public Ninja(int damage, int LongRangedamage, Point location, int type, int hp) {
        super(hp);
        this.damage = damage;
        this.LongRangedamage = LongRangedamage;
        this.location = location;
        shape = new SpriteShape(new Dimension(52, 88),
                new Dimension(11, 3), new Dimension(34, 85));
        fsm = new FiniteStateMachine();
        int female = (type==2)? 1 : 0;
        ImageRenderer imageRenderer = new NinjaImageRenderer(this,0, 0, 0, 0);
        ImageRenderer imageRendererRun = new NinjaImageRenderer(this, 15-5*female, 30-10*female, 1, 1);
        ImageRenderer imageRendererAttack = new NinjaImageRenderer(this, 10-14*female, 70-30*female, 5-8*female, 5+8*female);
        ImageRenderer imageRendererJump = new NinjaImageRenderer(this, 15-5*female, 30-10*female, 0-5*female, 0+8*female);
        ImageRenderer imageRendererDead = new NinjaImageRenderer(this, -30, 70, -10, 20);

        State idle = new WaitingPerFrame(4,
                new Idle(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/idle", imageRenderer)));
        State walking = new WaitingPerFrame(2,
                new Walking(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/walking", imageRendererRun)));
        State attacking = new WaitingPerFrame(3,
                new Attacking(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/attack", imageRendererAttack)));
        State throwing = new WaitingPerFrame(3,
                new Throwing(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/throw", imageRendererRun)));
        State jumping = new WaitingPerFrame(2,
                new Jumping(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/jumping", imageRendererJump)));
        State falling = new WaitingPerFrame(2,
                new Falling(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/falling", imageRendererJump)));
        State jumpAttacking = new WaitingPerFrame(2,
                new JumpAttacking(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/jumpAttacking", imageRendererAttack)));
        State jumpThrowing = new WaitingPerFrame(2,
                new JumpThrowing(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/jumpThrowing", imageRendererJump)));
        State dying = new WaitingPerFrame(3,
                new Dead(this, fsm, imageStatesFromFolder("assets/Ninja"+type+"/dead", imageRendererDead)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(idle).when(JUMP).to(jumping));
        fsm.addTransition(from(idle).when(ATTACK).to(attacking));
        fsm.addTransition(from(idle).when(THROW).to(throwing));
        fsm.addTransition(from(idle).when(DEAD).to(dying));
        fsm.addTransition(from(idle).when(FALL).to(falling));
        fsm.addTransition(from(walking).when(STOP).to(idle));
        fsm.addTransition(from(walking).when(JUMP).to(jumping));
        fsm.addTransition(from(walking).when(ATTACK).to(attacking));
        fsm.addTransition(from(walking).when(FALL).to(falling));
        fsm.addTransition(from(walking).when(THROW).to(throwing));
        fsm.addTransition(from(jumping).when(FALL).to(falling));
        fsm.addTransition(from(jumping).when(ATTACK).to(jumpAttacking));
        fsm.addTransition(from(jumping).when(THROW).to(jumpThrowing));
        fsm.addTransition(from(falling).when(ATTACK).to(jumpAttacking));
        fsm.addTransition(from(falling).when(THROW).to(jumpThrowing));
        fsm.addTransition(from(jumpAttacking).when(THROW).to(jumpThrowing));
        fsm.addTransition(from(jumpAttacking).when(FALL).to(falling));
        fsm.addTransition(from(jumpThrowing).when(ATTACK).to(jumpAttacking));
        fsm.addTransition(from(jumpThrowing).when(FALL).to(falling));
        fsm.addTransition(from(dying).when(REVIVE).to(idle));
    }

    public void attack() {
        if(!jumpAttackCD){
            fsm.trigger(ATTACK);
        }
    }

    public void throwing() {
        if (!jumpThrowCD) {
            fsm.trigger(THROW);
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getLongRangedamage() {
        return LongRangedamage;
    }

    public void move(Direction direction) {
        if (direction == Direction.LEFT || direction == Direction.RIGHT) {
            face = direction;
        }
        if(direction == Direction.JUMP){
            fsm.trigger(JUMP);
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
        if (!this.buff.isEmpty()) {
            g.setColor(Color.BLUE);
            Font myFont = new Font("Courier New", 1, 30);
            g.setFont(myFont);
            g.drawString(this.buff, (int) location.getX() - 20, (int) location.getY() - 50);
            this.buffTime--;
            if (this.buffTime <= 0) {
                this.removeBuff();
            }
        }
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

    public void addBuff(String buff) {
        this.buff = buff;
        this.buffTime = 100;
    }

    public String getBuff() {
        return buff;
    }
    public void removeBuff() {
        this.buff = "";
        this.buffTime = 0;
    }

    public void revive() {
        this.setLocation(new Point(20, 512));
        this.healed(NINJA_HP);
        fsm.trigger(REVIVE);
    }
}
