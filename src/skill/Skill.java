package skill;

import ninja.Ninja;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Skill {
    protected String name;
    protected int[] required;
    protected Ninja ninja;
    private String info;

    public Skill(Ninja ninja, String info) {
        this.ninja = ninja;
        this.info = info;
    }

    public String toString() {
        return this.name;
    }

    public int[] getRequired() {
        return this.required;
    }

    public void doEffect(){}

    public String getInfo() {
        return this.info;
    }
}