package map.mapItems;

import model.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bush extends Item {
    private Point location;
    private final Dimension size;
    private int type;
    private static Image[] images = {null, null, null, null, null, null, null};

    public Bush(Point location, Dimension size, int type) {
        this.location = location;
        this.size = size;
        this.type = type;
        try {
            images[0] = ImageIO.read(new File("assets/Object/Bush_1.png"));
            images[1] = ImageIO.read(new File("assets/Object/Bush_2.png"));
            images[2] = ImageIO.read(new File("assets/Object/Bush_3.png"));
            images[3] = ImageIO.read(new File("assets/Object/Bush_4.png"));
            images[4] = ImageIO.read(new File("assets/Object/Bush_5.png"));
            images[5] = ImageIO.read(new File("assets/Object/Bush_6.png"));
            images[6] = ImageIO.read(new File("assets/Object/Bush_7.png"));
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
