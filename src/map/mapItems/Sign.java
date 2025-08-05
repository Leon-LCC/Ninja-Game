package map.mapItems;

import model.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Sign extends Item {
    private Point location;
    private final Dimension size;
    private int type;
    private String text;
    private int adjust = 0;
    private static Image[] images = {null, null, null, null};

    public Sign(Point location, Dimension size, int type, String text) {
        this.location = location;
        this.size = size;
        this.type = type;
        this.text = text;
        if(type == 2){
            adjust = 1;
        }
        try {
            images[0] = ImageIO.read(new File("assets/Object/Sign_1.png"));
            images[1] = ImageIO.read(new File("assets/Object/Sign_2.png"));
            images[2] = ImageIO.read(new File("assets/Object/Sign_3.png"));
            images[3] = ImageIO.read(new File("assets/Object/Sign_4.png"));
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
        g.setColor(Color.BLACK);
        Font myFont = new Font ("Courier New", 1, 15);
        g.setFont(myFont);
        g.drawString(text, location.x+(size.width/(7+adjust*7)), location.y+(size.height/11)*(4+adjust*1));
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, new Dimension(0,0));
    }
}