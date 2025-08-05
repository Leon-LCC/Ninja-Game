package ninja;

import fsm.*;
import media.AudioPlayer;
import model.Sprite;
import model.World;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ninja.Ninja.Event.WALK;

public class Dead extends Sequence {
    private final Ninja ninja;
    private final StateMachine stateMachine;

    public Dead(Ninja ninja, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.ninja = ninja;
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
    public void init(){
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        this.ninja.DeadEnd = true;
    }
}
