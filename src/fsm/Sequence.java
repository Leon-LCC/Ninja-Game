package fsm;

import java.awt.*;
import java.util.List;

public abstract class Sequence implements State {
    protected final List<? extends State> states;
    protected int currentPosition;

    public Sequence(List<? extends State> states) {
        this.states = states;
    }

    @Override
    public void update() {
        currentPosition++;
        if (currentPosition >= states.size()) {
            onSequenceEnd();
        }
    }

    @Override
    public void render(Graphics g) {
        states.get(currentPosition).render(g);
    }

    @Override
    public void init() {
        currentPosition = 0;
    }

    protected abstract void onSequenceEnd();

}
