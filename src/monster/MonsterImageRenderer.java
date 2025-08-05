package monster;

import fsm.ImageRenderer;
import model.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MonsterImageRenderer implements ImageRenderer {
    protected Monster monster;
    private int widthLeftOffset;
    private int widthRightOffset;
    private int HeightTopOffset;
    private int HeightBottomOffset;

    public MonsterImageRenderer(Monster monster, int widthLeftOffset, int widthRightOffset, int HeightTopOffset, int HeightBottomOffset) {
        this.monster = monster;
        this.widthLeftOffset = widthLeftOffset;
        this.widthRightOffset = widthRightOffset;
        this.HeightTopOffset = HeightTopOffset;
        this.HeightBottomOffset = HeightBottomOffset;
    }

    @Override
    public void render(Image image, Graphics g) {
        Direction face = monster.getFace();
        Rectangle range = monster.getRange();
        Rectangle body = monster.getBody();
        if (face == Direction.LEFT) {
            g.drawImage(image, range.x + range.width + widthLeftOffset, range.y + HeightTopOffset,
                    -range.width - widthRightOffset, range.height + HeightBottomOffset, null);
        } else {
            g.drawImage(image, range.x - widthLeftOffset, range.y + HeightTopOffset,
                    range.width + widthRightOffset, range.height+HeightBottomOffset, null);
        }
        //g.setColor(Color.RED);
        //g.drawRect(body.x, body.y, body.width, body.height);
        Image imageGem = null;
        try {
            switch (monster.getGem()){
                case YELLOW:
                    imageGem = ImageIO.read(new File("assets/Object/Gem_Yellow.png"));
                    break;
                case GREEN:
                    imageGem = ImageIO.read(new File("assets/Object/Gem_Green.png"));
                    break;
                case RED:
                    imageGem = ImageIO.read(new File("assets/Object/Gem_Red.png"));
                    break;
                case BLUE: default:
                    imageGem = ImageIO.read(new File("assets/Object/Gem_Blue.png"));
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(imageGem, range.x + range.width + widthLeftOffset, range.y + HeightTopOffset, 15, 15, null);
    }
}
