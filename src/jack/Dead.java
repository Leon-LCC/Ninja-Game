package jack;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;

import java.awt.*;
import java.util.List;

public class Dead extends Sequence {
    private final Jack jack;
    private final StateMachine stateMachine;

    public Dead(Jack jack, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.jack = jack;
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
        this.jack.DeadEnd = true;
    }
}