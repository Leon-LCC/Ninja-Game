package map.LV3;

import controller.AI;
import controller.ZombieNormalAI;
import map.mapItems.*;
import model.Map;
import model.World;
import monster.Monster;
import ninja.Ninja;
import zombie.Zombie;

import java.awt.*;

public class SecondMap extends Map {
    public SecondMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 2));
        this.addItem(new Floor(new Point(-1, 0), new Dimension(1, 700), 2));

        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 17));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(400, 600), new Dimension(100, 70), 24));
        this.addItem(new Floor(new Point(400, 530), new Dimension(100, 70), 20));
        this.addItem(new Floor(new Point(400, 460), new Dimension(100, 70), 17));
        this.addItem(new Floor(new Point(500, 600), new Dimension(100, 70), 21));
        this.addItem(new Floor(new Point(500, 530), new Dimension(100, 70), 21));
        this.addItem(new Floor(new Point(500, 460), new Dimension(100, 70), 18));
        this.addItem(new Floor(new Point(600, 600), new Dimension(100, 70), 21));
        this.addItem(new Floor(new Point(600, 530), new Dimension(100, 70), 21));
        this.addItem(new Floor(new Point(600, 460), new Dimension(100, 70), 23));
        this.addItem(new Floor(new Point(700, 460), new Dimension(100, 70), 24));
        this.addItem(new Floor(new Point(700, 530), new Dimension(100, 70), 21));
        this.addItem(new Floor(new Point(700, 600), new Dimension(100, 70), 21));
        this.addItem(new Floor(new Point(700, 390), new Dimension(100, 70), 20));
        this.addItem(new Floor(new Point(700, 320), new Dimension(100, 70), 20));
        this.addItem(new Floor(new Point(700, 250), new Dimension(100, 70), 17));
        this.addItem(new Floor(new Point(800, 460), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(800, 530), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(800, 390), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(800, 320), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(800, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(1000, 460), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1000, 530), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1000, 390), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1000, 320), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1000, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(1200, 460), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1200, 530), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1200, 390), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1200, 320), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(1200, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(100, 120), new Dimension(70, 50), 31));
        this.addItem(new Floor(new Point(170, 120), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(240, 120), new Dimension(70, 50), 33));

        this.addGem(new Mushroom(new Point(120, 95), new Dimension(30, 25), 1));
        this.addItem(new Bush(new Point(150, 70), new Dimension(60, 50), 5));
        this.addItem(new Bush(new Point(190, 60), new Dimension(80, 60), 6));
        this.addItem(new Tree(new Point(1020, 50), new Dimension(180, 200), 4));
        this.addItem(new Sign(new Point(1000, 180), new Dimension(60, 70), 3, ""));
        this.addItem(new Bush(new Point(960, 220), new Dimension(50, 30), 6));
        this.addItem(new Stone(new Point(1290, 200), new Dimension(40, 50), 4));
        this.addItem(new Stone(new Point(790, 210), new Dimension(40, 40), 3));
        this.addItem(new Stone(new Point(430, 410), new Dimension(40, 50), 4));
        this.addItem(new Stone(new Point(1190, 220), new Dimension(50, 30), 2));
        this.addItem(new Stone(new Point(550, 430), new Dimension(50, 30), 2));
        this.addItem(new Stone(new Point(150, 570), new Dimension(50, 30), 2));
        this.addItem(new Stone(new Point(220, 550), new Dimension(40, 50), 4));
        this.addItem(new Stone(new Point(240, 70), new Dimension(40, 50), 4));
    }

    @Override
    public void init(World world){
        super.init(world);

    }
}
