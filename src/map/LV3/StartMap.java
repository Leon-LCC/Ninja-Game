package map.LV3;

import controller.AI;
import controller.MonsterNormalAI;
import controller.ZombieNormalAI;
import map.mapItems.*;
import model.Map;
import model.World;
import monster.Monster;
import ninja.Ninja;
import zombie.Zombie;

import java.awt.*;

public class StartMap extends Map {
    public StartMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 2));
        this.addItem(new Floor(new Point(-1, 0), new Dimension(1, 700), 2));

        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 17));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(400, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(600, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 70), 18));

        this.addItem(new Floor(new Point(300, 350), new Dimension(70, 50), 31));
        this.addItem(new Floor(new Point(370, 350), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(440, 350), new Dimension(70, 50), 33));
        this.addItem(new Floor(new Point(700, 200), new Dimension(70, 50), 31));
        this.addItem(new Floor(new Point(770, 200), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(840, 200), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(910, 200), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(980, 200), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(1050, 200), new Dimension(70, 50), 33));
        this.addItem(new Crate(new Point(950, 140), new Dimension(60, 60), 2));
        this.addItem(new Crate(new Point(1010, 140), new Dimension(60, 60), 2));
        this.addItem(new Crate(new Point(980, 80), new Dimension(60, 60), 2));
        this.addItem(new Bush(new Point(920, 170), new Dimension(50, 30), 6));
        this.addItem(new Stone(new Point(320, 300), new Dimension(40, 50), 3));
        this.addItem(new Tree(new Point(20, 350), new Dimension(240, 250), 4));
        this.addItem(new Stone(new Point(350, 570), new Dimension(50, 30), 2));
        this.addItem(new Sign(new Point(220, 530), new Dimension(60, 70), 4, ""));
        this.addItem(new Bush(new Point(500, 550), new Dimension(100, 50), 7));
        this.addItem(new Stone(new Point(1180, 560), new Dimension(40, 40), 3));
        this.addItem(new Tree(new Point(810, 400), new Dimension(180, 200), 4));
        this.addItem(new Bush(new Point(950, 540), new Dimension(80, 60), 6));
        this.addItem(new Bush(new Point(990, 550), new Dimension(60, 50), 5));
        this.BGM = 3;
    }

    @Override
    public void init(World world){
        super.init(world);
        Zombie m1 = new Zombie(100,new Point(740, 510), 1);
        Zombie m2 = new Zombie(100, new Point(340, 260), 1);
        Zombie m3 = new Zombie(100, new Point(880, 110), 1);
        Zombie m4 = new Zombie(100, new Point(1150, 510), 2);
        AI ai1 =  new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0),m1);
        AI ai2 =  new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0),m2);
        AI ai3 =  new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0),m3);
        AI ai4 =  new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0),m4);
        this.addSprite(m1, ai1);
        this.addSprite(m2, ai2);
        this.addSprite(m3, ai3);
        this.addSprite(m4, ai4);
    }
}