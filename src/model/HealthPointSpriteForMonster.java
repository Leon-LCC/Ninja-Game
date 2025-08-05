package model;

import monster.HealthPointBarForMonster;
import media.AudioPlayer;
import monster.Gem;

import java.awt.*;

public abstract class HealthPointSpriteForMonster extends Sprite {
    public static final String AUDIO_SLIMEDIE = "slimedie";

    protected HealthPointBarForMonster hpBar;

    public HealthPointSpriteForMonster(int hp) {
        this.hpBar = new HealthPointBarForMonster(hp);
        hpBar.setOwner(this);
    }

    @Override
    public void onDamaged(Rectangle damageArea, int damage, Gem gem) {
        hpBar.onDamaged(damageArea, damage, gem);
        if (hpBar.isDead()) {
            this.DeadEnd = true;
            AudioPlayer.playSounds(AUDIO_SLIMEDIE);
        }
    }

    @Override
    public void render(Graphics g) {
        hpBar.render(g);
    }
}
