package model;

import java.awt.*;

public abstract class Item {
    protected World world;
    protected Point location = new Point();

    public abstract void update();

    public abstract void render(Graphics g);

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

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
