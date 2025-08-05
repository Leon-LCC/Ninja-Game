package map.conversation;

import media.AudioPlayer;
import model.Conversation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;

public class MapConversation extends Conversation {
    public static final Object AUDIO_CHANGEPAGE = "changepage";
    private Point location;
    private final Dimension size;
    private int type;
    private List<String> title;
    private List<String> texts;
    private int[] line_per_page;
    private int pageIDX = 0;
    private int textIDX = 0;

    public MapConversation(Point location, Dimension size, int type, List<String> texts, int[] line_per_page, List<String> title) {
        this.location = location;
        this.size = size;
        this.type = type;
        this.title = title;
        this.texts = texts;
        this.line_per_page = line_per_page;
    }

    @Override
    public void update() {
        textIDX += line_per_page[pageIDX];
        pageIDX += 1;
        AudioPlayer.playSounds(AUDIO_CHANGEPAGE);
    }

    @Override
    public boolean isEnd() {
        return pageIDX == line_per_page.length;
    }

    @Override
    public void render(Graphics g) {
        Image image1 = null;
        Image image2 = null;
        String pathname1 = "assets/window/con_win"+type+".png";
        try {
            image1 = ImageIO.read(new File(pathname1));
            image2 = ImageIO.read(new File("assets/window/title1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image1, location.x, location.y, size.width, size.height, null);
        g.drawImage(image2, location.x+size.width/4, location.y, size.width/2, size.height/8, null);
        Font myFont = new Font ("Courier New", 1, 25);
        g.setFont(myFont);
        g.setColor(Color.WHITE);
        g.drawString(title.get(pageIDX), location.x+size.width/3, location.y+size.height/12);
        myFont = new Font ("Courier New", 1, 30);
        g.setFont(myFont);
        g.setColor(Color.BLACK);
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