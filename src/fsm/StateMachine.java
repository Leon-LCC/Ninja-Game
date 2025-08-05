package fsm;

import java.awt.*;

public interface StateMachine {
    void update();

    default void render(Graphics g) {
        currentState().render(g);
    }

    void trigger(Object event);

    State currentState();

    void reset();
}
