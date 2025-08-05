package model;

import java.awt.*;

public abstract class Conversation {
    protected World world;
    protected Point location = new Point();

    public abstract void render(Graphics g);

    public abstract void update();

    public abstract boolean isEnd();

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getX() {
        return getRange().x;
    }

    public int getY() {
        return getRange().y;
    }

    public abstract Rectangle getRange();
}