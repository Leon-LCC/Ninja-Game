package map.mapItems;

import fsm.ImageRenderer;

import java.awt.*;

public class PortalRenderer implements ImageRenderer {
    private final int x;
    private final int y;
    private final int width;
    private final int height;


    public PortalRenderer(Portal portal){
        this.x = portal.getBody().x;
        this.y = portal.getBody().y;
        this.width = portal.getBody().width;
        this.height = portal.getBody().height;
    }

    @Override
    public void render(Image image, Graphics g) {
        g.drawImage(image, this.x, this.y, this.width, this.height, null);
        //g.setColor(Color.RED);
        //g.drawRect(this.x, this.y, this.width, this.height);
    }
}
