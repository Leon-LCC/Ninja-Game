package map.mapItems;

import model.Item;
import monster.Gem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Mushroom extends Gems {
    private Point location;
    private final Dimension size;
    private int type;
    private static Image[] images = {null, null};

    public Mushroom(Point location, Dimension size, int type) {
        super(location, size, Gem.MUSHROOM);
        this.location = location;
        this.size = size;
        this.type = type;
        try {
            images[0] = ImageIO.read(new File("assets/Object/Mushroom_1.png"));
            images[1] = ImageIO.read(new File("assets/Object/Mushroom_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(images[type-1], location.x, location.y, size.width, size.height, null);
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, size);
    }
}