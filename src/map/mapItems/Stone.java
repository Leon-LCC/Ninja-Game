package map.mapItems;

import model.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Stone extends Item {
    private Point location;
    private final Dimension size;
    private int type;
    private static Image[] images = {null, null, null, null};

    public Stone(Point location, Dimension size, int type) {
        this.location = location;
        this.size = size;
        this.type = type;
        try {
            images[0] = ImageIO.read(new File("assets/Object/Stone"+1+".png"));
            images[1] = ImageIO.read(new File("assets/Object/Stone"+2+".png"));
            images[2] = ImageIO.read(new File("assets/Object/Stone"+3+".png"));
            images[3] = ImageIO.read(new File("assets/Object/Stone"+4+".png"));
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
