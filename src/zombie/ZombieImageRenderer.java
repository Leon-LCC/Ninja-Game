package zombie;

import fsm.ImageRenderer;
import model.Direction;

import java.awt.*;

public class ZombieImageRenderer implements ImageRenderer {
    protected Zombie zombie;
    private int widthLeftOffset;
    private int widthRightOffset;
    private int HeightTopOffset;
    private int HeightBottomOffset;

    public ZombieImageRenderer(Zombie zombie, int widthLeftOffset, int widthRightOffset, int HeightTopOffset, int HeightBottomOffset) {
        this.zombie = zombie;
        this.widthLeftOffset = widthLeftOffset;
        this.widthRightOffset = widthRightOffset;
        this.HeightTopOffset = HeightTopOffset;
        this.HeightBottomOffset = HeightBottomOffset;
    }

    @Override
    public void render(Image image, Graphics g) {
        Direction face = zombie.getFace();
        Rectangle range = zombie.getRange();
        Rectangle body = zombie.getBody();
        if (face == Direction.LEFT) {
            g.drawImage(image, range.x + range.width + widthLeftOffset, range.y + HeightTopOffset,
                    -range.width - widthRightOffset, range.height + HeightBottomOffset, null);
        } else {
            g.drawImage(image, range.x - widthLeftOffset, range.y + HeightTopOffset,
                    range.width + widthRightOffset, range.height+HeightBottomOffset, null);
        }
         //g.setColor(Color.RED);
         //g.drawRect(body.x, body.y, body.width, body.height);
    }
}
