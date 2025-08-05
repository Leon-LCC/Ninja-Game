package skill;

import ninja.Ninja;

import java.awt.*;

public class Power extends Skill {
    private final int VALUE = 100;

    public Power(Ninja ninja) {
        super(ninja, "Power Up");
        this.name = "power";
        this.required = new int[] {0, 0, 2, 0};
    }

    public void doEffect() {
        ninja.addDamage(VALUE);
    }

    public String info() {
        return "increase attack damage by " + String.valueOf(VALUE);
    }
}