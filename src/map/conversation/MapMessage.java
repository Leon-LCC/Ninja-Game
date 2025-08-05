package map.conversation;

import model.Conversation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;

public class MapMessage extends Conversation {
    private Point location;
    private final Dimension size;
    private int type;
    private List<String> texts;
    private int[] line_per_page;
    private int pageIDX = 0;
    private int textIDX = 0;

    public MapMessage(Point location, Dimension size, int type, List<String> texts, int[] line_per_page) {
        this.location = location;
        this.size = size;
        this.type = type;
        this.texts = texts;
        this.line_per_page = line_per_page;
    }

    @Override
    public void update() {
        textIDX += line_per_page[pageIDX];
        pageIDX += 1;
    }

    @Override
    public boolean isEnd() {
        return pageIDX == line_per_page.length;
    }

    @Override
    public void render(Graphics g) {
        Image image = null;
        String pathname = "assets/window/con_win"+type+".png";
        try {
            image = ImageIO.read(new File(pathname));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, location.x, location.y, size.width, size.height, null);
        g.setColor(Color.BLACK);
        Font myFont = new Font ("Courier New", 1, 30);
        g.setFont(myFont);
        for(int i = 0; i < line_per_page[pageIDX]; i++){
            String text = texts.get(textIDX+i);
            g.drawString(text, location.x+(size.width/6), location.y+(size.height/10)*(i+3));
        }
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, new Dimension(0,0));
    }
}