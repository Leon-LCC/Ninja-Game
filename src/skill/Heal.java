package skill;

import ninja.Ninja;

import java.awt.*;

public class Heal extends Skill {
    private final int VALUE = 1000;

    public Heal(Ninja ninja) {
        super(ninja, "Heal Up");
        this.name = "heal";
        this.required = new int[] {0, 0, 0, 2};
    }

    public void doEffect() {
        ninja.healed(VALUE);
    }

    public String info() {
        return "healed by " + String.valueOf(VALUE);
    }
}