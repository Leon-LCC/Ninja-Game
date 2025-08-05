package zombie;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;

import java.awt.*;
import java.util.List;

public class Dead extends Sequence {
    private final Zombie zombie;
    private final StateMachine stateMachine;

    public Dead(Zombie zombie, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.zombie = zombie;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        this.zombie.DeadEnd = true;
    }
}
