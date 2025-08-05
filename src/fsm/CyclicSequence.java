package fsm;

import java.util.List;

public class CyclicSequence extends Sequence {
    public CyclicSequence(List<? extends State> states) {
        super(states);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0; // back to the first frame
    }
}
