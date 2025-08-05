package fsm;

import java.awt.*;

public interface State {
    void update();

    void render(Graphics g);

    void init();
}
