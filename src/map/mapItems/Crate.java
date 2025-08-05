package map.mapItems;

import model.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Crate extends Item {
    private Point location;
    private final Dimension size;
    private int type;
    private static Image[] images = {null, null};

    public Crate(Point location, Dimension size, int type) {
        this.location = location;
        this.size = size;
        this.type = type;
        try {
            images[0] = ImageIO.read(new File("assets/Object/Crate"+1+".png"));
            images[1] = ImageIO.read(new File("assets/Object/Crate"+2+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(images[type-1], location.x, location.y, size.width, size.height, null);
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, new Dimension(0,0));
    }
}
