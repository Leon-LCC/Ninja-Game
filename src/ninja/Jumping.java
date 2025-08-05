package ninja;

import fsm.*;
import media.AudioPlayer;
import model.Direction;

import java.awt.*;
import java.util.List;

import static ninja.Ninja.Event.FALL;

public class Jumping extends Sequence {
    public static final String AUDIO_JUMP = "jump";
    private final Ninja ninja;
    private final StateMachine stateMachine;
    private int Gravity = 4;
    private int Speed = 40;

    public Jumping(Ninja ninja, StateMachine stateMachine, List<? extends State> states) {
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
    public void init(){
        currentPosition = 0;
        Speed = 40;
        AudioPlayer.playSounds(AUDIO_JUMP);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        stateMachine.trigger(FALL);
    }
}
