package map.mapItems;

import fsm.CyclicSequence;
import fsm.ImageState;
import fsm.State;

import java.util.List;

public class PortalIdle extends CyclicSequence {
    public PortalIdle(List<ImageState> states) {
        super(states);
    }

    @Override
    public String toString() {
        return "Idle";
    }
}
