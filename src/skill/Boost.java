package skill;

import ninja.Ninja;

import java.awt.*;

public class Boost extends Skill {
    private final int VALUE = 500;

    public Boost(Ninja ninja) {
        super(ninja, "Max HP Up");
        this.name = "boost";
        this.required = new int[] {0, 2, 0, 0};
    }

    public void doEffect() {
        ninja.boost(VALUE);
    }

    public String info() {
        return "increase max HP by " + String.valueOf(VALUE);
    }
}