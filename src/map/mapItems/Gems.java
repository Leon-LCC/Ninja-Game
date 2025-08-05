package map.mapItems;

import model.Item;
import monster.Gem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Gems extends Item {
    private Point location;
    private final Dimension size;
    private Gem gem;
    private static Image[] images = {null, null, null, null};

    public Gems(Point location, Dimension size, Gem gem) {
        this.location = location;
        this.size = size;
        this.gem = gem;
        try {
            images[0] = ImageIO.read(new File("assets/Object/Gem_Yellow.png"));
            images[1] = ImageIO.read(new File("assets/Object/Gem_Green.png"));
            images[2] = ImageIO.read(new File("assets/Object/Gem_Red.png"));
            images[3] = ImageIO.read(new File("assets/Object/Gem_Blue.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        int idx = 0;
        switch (gem){
            case YELLOW:
                idx = 0;
                break;
            case GREEN:
                idx = 1;
                break;
            case RED:
                idx = 2;
                break;
            case BLUE:
                idx = 3;
                break;
        }
        g.drawImage(images[idx], location.x, location.y, size.width, size.height, null);
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, size);
    }

    public Gem getGem() {
        return gem;
    }
}
