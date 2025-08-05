package map.mapItems;

import model.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Floor extends Item {
    private Point location;
    private final Dimension size;
    private int type;
    private static Image[] images = new Image[33];

    public Floor(Point location, Dimension size, int type) {
        this.location = location;
        this.size = size;
        this.type = type;
        try {
            for(int i = 0; i < 33; i++){
                images[i] = ImageIO.read(new File("assets/floor/"+(i+1)+".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(images[type-1], location.x, location.y,size.width, size.height, null);
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, size);
    }
}
