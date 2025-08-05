package map.LV2;

import controller.AI;
import controller.NinjaBossAI;
import map.mapItems.*;
import model.Map;
import model.World;
import ninja.Ninja;

import java.awt.*;

public class ThirdMap extends Map {
    private Ninja boss = null;

    public ThirdMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 1));
        this.addItem(new Floor(new Point(-10, -500), new Dimension(10, 1300), 2));
        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(400, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(600, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(-10, 130), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(60, 130), new Dimension(70, 50), 15));
        this.addItem(new Floor(new Point(-10, 280), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(60, 280), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(130, 280), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(200, 280), new Dimension(70, 50), 15));
        this.addItem(new Floor(new Point(550, 250), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(620, 250), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(690, 250), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(760, 250), new Dimension(70, 50), 15));
        this.addItem(new Floor(new Point(1240, 130), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(1310, 130), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(1100, 280), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(1170, 280), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(1240, 280), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(1310, 280), new Dimension(70, 50), 14));
        this.addItem(new Stone(new Point(620, 220), new Dimension(50, 30), 1));
        this.addItem(new Tree(new Point(90, 410), new Dimension(175, 190), 3));
        this.addItem(new Tree(new Point(355, 410), new Dimension(175, 190), 3));
        this.addItem(new Tree(new Point(620, 410), new Dimension(175, 190), 3));
        this.addItem(new Tree(new Point(885, 410), new Dimension(175, 190), 3));
        this.addItem(new Tree(new Point(1150, 410), new Dimension(175, 190), 3));
        this.addItem(new Floor(new Point(1370, -500), new Dimension(10, 1300), 2));
        this.addItem(new Portal(new Point(1100, 500), new Dimension(100, 100)));
        this.BGM = 2;
    }

    @Override
    public void init(World world){
        super.init(world);
        boss = new Ninja(80, 30, new Point(1200, 192), 2, 3000);
        AI ai = new NinjaBossAI(world,(Ninja)world.getPlayers().get(0),boss);
        this.addSprite(boss, ai);
    }

    @Override
    public void update(){
        super.update();
        if(boss.DeadEnd){
            this.bossDead = true;
        }
    }
}
