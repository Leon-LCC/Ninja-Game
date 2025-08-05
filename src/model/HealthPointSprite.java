package model;

import ninja.HealthPointBar;
import media.AudioPlayer;
import monster.Gem;

import java.awt.*;

public abstract class HealthPointSprite extends Sprite {
    public static final String AUDIO_DIE = "Die";

    protected HealthPointBar hpBar;

    public HealthPointSprite(int hp) {
        this.hpBar = new HealthPointBar(hp);
        hpBar.setOwner(this);
    }

    @Override
    public void onDamaged(Rectangle damageArea, int damage, Gem gem) {
        hpBar.onDamaged(damageArea, damage, gem);
        if (hpBar.isDead()) {
            this.deadAnimate();
            AudioPlayer.playSounds(AUDIO_DIE);
        }
    }

    protected abstract void deadAnimate();

    @Override
    public void render(Graphics g) {
        hpBar.render(g);
    }

    public void healed(int heal) {
        hpBar.healed(heal);
    }

    public void boost(int boost) {
        hpBar.boost(boost);
    }
}
