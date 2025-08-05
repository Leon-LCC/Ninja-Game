package model;

import java.awt.*;

public enum Direction {
    JUMP, DOWN, LEFT, RIGHT, RUN, SLIDE;

    public Dimension translate() {
        switch (this) {
            case LEFT:
                return new Dimension(-6, 0);
            case RIGHT:
                return new Dimension(6, 0);
            case RUN:
                return new Dimension(0, 0);
            default:
                throw new IllegalStateException("Impossible");
        }
    }
}
