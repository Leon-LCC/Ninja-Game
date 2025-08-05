package map.LV2;

import controller.AI;
import controller.MonsterNormalAI;
import map.mapItems.*;
import model.Map;
import model.World;
import monster.Monster;
import ninja.Ninja;

import java.awt.*;

public class SecondMap extends Map {
    public SecondMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 1));
        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(400, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(600, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 70), 2));

        this.addItem(new Tree(new Point(100, 425), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(230, 425), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(600, 425), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(740, 350), new Dimension(230, 250), 3));
        this.addItem(new Tree(new Point(980, 410), new Dimension(175, 190), 3));
        this.addItem(new Tree(new Point(1140, 425), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(1200, 448), new Dimension(143, 152), 3));
        this.addItem(new Tree(new Point(1130, 180), new Dimension(400, 420), 3));
        this.addItem(new Tree(new Point(150, 350), new Dimension(240, 250), 2));
        this.addItem(new Tree(new Point(470, 290), new Dimension(290, 310), 2));
        this.addItem(new Tree(new Point(350, 330), new Dimension(260, 270), 2));
        this.addItem(new Tree(new Point(700, 450), new Dimension(140, 150), 2));
        this.addItem(new Tree(new Point(1000, 310), new Dimension(280, 290), 2));
        this.addItem(new Tree(new Point(800, 250), new Dimension(340, 350), 2));

        this.addItem(new Bush(new Point(300, 570), new Dimension(50, 30), 2));
        this.addItem(new Bush(new Point(360, 555), new Dimension(75, 45), 4));
        this.addItem(new Bush(new Point(510, 555), new Dimension(75, 45), 3));
        this.addItem(new Bush(new Point(660, 555), new Dimension(75, 45), 1));
        this.addItem(new Bush(new Point(800, 555), new Dimension(75, 45), 1));
        this.addItem(new Bush(new Point(1220, 570), new Dimension(50, 30), 2));
        this.addItem(new Stone(new Point(1050, 570), new Dimension(50, 30), 1));
        this.addItem(new Sign(new Point(345, 530), new Dimension(60, 70), 2, ""));
    }

    @Override
    public void init(World world){
        super.init(world);
        Monster m1 = new Monster(500, 350, new Point(500, 557), Generator.getGem());
        Monster m2 = new Monster(500, 350, new Point(700, 557), Generator.getGem());
        Monster m3 = new Monster(500, 350, new Point(900, 557), Generator.getGem());
        Monster m4 = new Monster(500, 350, new Point(1000, 557), Generator.getGem());
        AI ai1 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m1);
        AI ai2 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m2);
        AI ai3 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m3);
        AI ai4 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m4);
        this.addSprite(m1, ai1);
        this.addSprite(m2, ai2);
        this.addSprite(m3, ai3);
        this.addSprite(m4, ai4);
    }
}
