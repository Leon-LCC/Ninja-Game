package map.LV2;

import controller.AI;
import controller.MonsterNormalAI;
import map.mapItems.*;
import model.Map;
import model.World;
import monster.Monster;
import ninja.Ninja;

import java.awt.*;

public class StartMap extends Map {
    public StartMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 1));
        this.addItem(new Floor(new Point(-1, 0), new Dimension(1, 700), 2));
        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 1));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(400, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(600, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(700, 160), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(770, 160), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(840, 160), new Dimension(70, 50), 15));
        this.addItem(new Floor(new Point(170, 150), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(240, 150), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(310, 150), new Dimension(70, 50), 15));

        this.addItem(new Crate(new Point(250, 540), new Dimension(60, 60), 1));
        this.addItem(new Crate(new Point(310, 540), new Dimension(60, 60), 1));
        this.addItem(new Crate(new Point(280, 480), new Dimension(60, 60), 1));
        this.addItem(new Bush(new Point(340, 570), new Dimension(50, 30), 3));
        this.addItem(new Bush(new Point(260, 510), new Dimension(50, 30), 2));
        this.addItem(new Tree(new Point(380, 425), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(1080, 425), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(950, 350), new Dimension(240, 250), 2));
        this.addItem(new Bush(new Point(210, 90), new Dimension(100, 60), 3));
        this.addItem(new Bush(new Point(200, 120), new Dimension(50, 30), 2));
        this.addItem(new Bush(new Point(260, 105), new Dimension(75, 45), 4));
        this.addItem(new Bush(new Point(1220, 570), new Dimension(50, 30), 2));
        this.addItem(new Bush(new Point(960, 570), new Dimension(50, 30), 4));
        this.addItem(new Bush(new Point(790, 130), new Dimension(50, 30), 2));
        this.addItem(new Bush(new Point(810, 110), new Dimension(80, 50), 3));
        this.addItem(new Bush(new Point(860, 590), new Dimension(20, 10), 4));
        this.addItem(new Stone(new Point(320, 120), new Dimension(50, 30), 1));

    }

    @Override
    public void init(World world){
        super.init(world);
        Monster m1 = new Monster(500, 300, new Point(230, 107), Generator.getGem());
        Monster m2 = new Monster(500, 300, new Point(750, 117), Generator.getGem());
        Monster m3 = new Monster(500, 300, new Point(570, 100), Generator.getGem());
        Monster m4 = new Monster(500, 300, new Point(1200, 200), Generator.getGem());
        Monster m5 = new Monster(500, 300, new Point(1020, 150), Generator.getGem());
        AI ai1 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m1);
        AI ai2 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m2);
        AI ai3 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m3);
        AI ai4 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m4);
        AI ai5 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m5);
        this.addSprite(m1, ai1);
        this.addSprite(m2, ai2);
        this.addSprite(m3, ai3);
        this.addSprite(m4, ai4);
        this.addSprite(m5, ai5);
        this.BGM = 1;
    }
}