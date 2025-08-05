package map.mapItems;

import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import model.Item;
import ninja.NinjaImageRenderer;

import java.awt.*;

import static utils.ImageStateUtils.imageStatesFromFolder;

public class Portal extends Item{
    private final FiniteStateMachine fsm;
    private Point location;
    private Dimension size;

    public Portal(Point point, Dimension dimension){
        this.location = point;
        this.size = dimension;
        fsm = new FiniteStateMachine();
        ImageRenderer imageRenderer = new PortalRenderer(this);
        State idle = new WaitingPerFrame(8,
                new PortalIdle(imageStatesFromFolder("assets/portal", imageRenderer)));
        fsm.setInitialState(idle);
    }

    public Rectangle getBody(){
        return new Rectangle(location, size);
    }

    @Override
    public void update() {
        fsm.update();
    }

    @Override
    public void render(Graphics g) {
        fsm.render(g);
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, new Dimension(0,0));
    }
}
