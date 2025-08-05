package map.LV4;

import controller.AI;
import controller.MonsterNormalAI;
import controller.ZombieNormalAI;
import map.mapItems.*;
import model.Map;
import model.World;
import ninja.Ninja;
import zombie.Zombie;

import java.awt.*;

public class StartMap extends Map {
    public StartMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 2));
        this.addItem(new Floor(new Point(-10, -500), new Dimension(10, 1300), 2));
        this.addItem(new Floor(new Point(0, 250), new Dimension(70, 50), 31));
        this.addItem(new Floor(new Point(70, 250), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(140, 250), new Dimension(70, 50), 33));

        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 200), 17));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 200), 18));
        this.addItem(new Floor(new Point(400, 600), new Dimension(200, 200), 18));
        this.addItem(new Floor(new Point(600, 600), new Dimension(200, 200), 18));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 200), 18));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 200), 18));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 200), 18));

        this.addItem(new Crate(new Point(550, 540), new Dimension(60, 60), 2));
        this.addItem(new Crate(new Point(610, 540), new Dimension(60, 60), 2));
        this.addItem(new Bush(new Point(20, 570), new Dimension(50, 30), 6));
        this.addItem(new Stone(new Point(70, 550), new Dimension(40, 50), 3));
        this.addItem(new Tree(new Point(1200, 350), new Dimension(240, 250), 4));
        this.addItem(new Stone(new Point(350, 570), new Dimension(50, 30), 2));
        this.addItem(new Sign(new Point(220, 530), new Dimension(60, 70), 3, ""));
        this.addItem(new Stone(new Point(1180, 560), new Dimension(40, 40), 3));
        this.addItem(new Tree(new Point(50, 400), new Dimension(180, 200), 4));
        this.addItem(new Bush(new Point(950, 540), new Dimension(80, 60), 6));
        this.addItem(new Bush(new Point(990, 550), new Dimension(60, 50), 5));
        this.addItem(new Stone(new Point(140, 190), new Dimension(40, 60), 4));
        this.addItem(new Bush(new Point(5, 200), new Dimension(60, 50), 5));
        this.BGM = 4;
    }

    @Override
    public void init(World world){
        super.init(world);
        Zombie m1 = new Zombie(200, new Point(1000, 510), 1);
        Zombie m2 = new Zombie(200, new Point(300, 510), 2);
        AI ai1 = new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0), m1);
        AI ai2 = new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0), m2);
        this.addSprite(m1, ai1);
        this.addSprite(m2, ai2);
    }
}