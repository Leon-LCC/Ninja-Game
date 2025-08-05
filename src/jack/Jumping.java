package jack;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import media.AudioPlayer;
import model.Direction;

import java.awt.*;
import java.util.List;

import static jack.Jack.Event.FALL;

public class Jumping extends Sequence {
    private final Jack jack;
    private final StateMachine stateMachine;
    private int Gravity = 2;
    private int Speed = 20;

    public Jumping(Jack jack, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.jack = jack;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (jack.isAlive()) {
            super.update();
            for (Direction direction : jack.getDirections()) {
                jack.getWorld().move(jack, direction.translate());
            }
            if(Speed >= 0){
                jack.getWorld().move(jack, new Dimension(0, -Speed));
                Speed -= Gravity;
            }
        }
    }

    @Override
    public void init(){
        currentPosition = 0;
        Speed = 20;
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        this.stateMachine.trigger(FALL);
    }
}
