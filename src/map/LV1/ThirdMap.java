package map.LV1;

import controller.AI;
import controller.MonsterNormalAI;
import map.mapItems.*;
import model.Map;
import model.World;
import monster.Monster;
import ninja.Ninja;

import java.awt.*;

public class ThirdMap extends Map {
    public ThirdMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 1));
        this.addItem(new Floor(new Point(-10, 170), new Dimension(80, 50), 1));
        this.addItem(new Floor(new Point(70, 170), new Dimension(70, 50), 2));
        this.addItem(new Floor(new Point(140, 170), new Dimension(70, 50), 2));
        this.addItem(new Floor(new Point(210, 170), new Dimension(70, 50), 2));
        this.addItem(new Floor(new Point(-10, 220), new Dimension(80, 50), 12));
        this.addItem(new Floor(new Point(70, 220), new Dimension(70, 50), 9));
        this.addItem(new Floor(new Point(140, 220), new Dimension(70, 50), 9));
        this.addItem(new Floor(new Point(210, 220), new Dimension(70, 50), 9));
        this.addItem(new Floor(new Point(280, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(280, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(380, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(380, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(480, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(480, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(580, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(580, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(680, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(680, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(780, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(780, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(880, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(880, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(980, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(980, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(1080, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(1080, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(1180, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(1180, 220), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(1280, 170), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(1280, 220), new Dimension(100, 50), 9));

        this.addItem(new Floor(new Point(-10, 460), new Dimension(110, 70), 3));
        this.addItem(new Floor(new Point(-10, 530), new Dimension(110, 70), 6));
        this.addItem(new Floor(new Point(-10, 600), new Dimension(110, 70), 10));
        this.addItem(new Floor(new Point(100, 600), new Dimension(100, 70), 11));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(400, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(600, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1369, -500), new Dimension(10, 1300), 2));

        this.addItem(new Tree(new Point(610, 130), new Dimension(100, 40), 1));
        this.addItem(new Tree(new Point(640, -80), new Dimension(240, 250), 2));
        this.addItem(new Crate(new Point(150, 110), new Dimension(60, 60), 1));
        this.addItem(new Bush(new Point(690, 140), new Dimension(50, 30), 3));
        this.addItem(new Bush(new Point(1100, 120), new Dimension(70, 50), 1));
        this.addItem(new Bush(new Point(1300, 155), new Dimension(25, 15), 2));
        this.addItem(new Stone(new Point(1160, 150), new Dimension(40, 20), 1));
        this.addItem(new Tree(new Point(830, 425), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(870, 340), new Dimension(240, 260), 2));
        this.addItem(new Tree(new Point(840, 570), new Dimension(70, 30), 1));
        this.addItem(new Stone(new Point(925, 580), new Dimension(40, 20), 1));
        this.addItem(new Bush(new Point(235, 575), new Dimension(45, 25), 4));
        this.addItem(new Sign(new Point(250, 530), new Dimension(60, 70), 2, ""));
        this.addItem(new Bush(new Point(340, 560), new Dimension(60, 40), 1));
        this.addGem(new Mushroom(new Point(1270, 145), new Dimension(30, 25), 2));
        this.addItem(new Portal(new Point(1100, 500), new Dimension(100, 100)));
    }

    @Override
    public void init(World world){
        super.init(world);
        Monster m1 = new Monster(400, 200, new Point(250, 127), Generator.getGem());
        Monster m3 = new Monster(400, 200, new Point(700, 127), Generator.getGem());
        Monster m4 = new Monster(400, 200, new Point(1000, 127), Generator.getGem());
        Monster m5 = new Monster(400, 200, new Point(1300, 557), Generator.getGem());
        Monster m7 = new Monster(400, 200, new Point(850, 557), Generator.getGem());
        Monster m8 = new Monster(400, 200, new Point(690, 557), Generator.getGem());
        Monster m9 = new Monster(400, 200, new Point(385, 557), Generator.getGem());
        AI ai1 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m1);
        AI ai3 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m3);
        AI ai4 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m4);
        AI ai5 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m5);
        AI ai7 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m7);
        AI ai8 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m8);
        AI ai9 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m9);
        this.addSprite(m1, ai1);
        this.addSprite(m3, ai3);
        this.addSprite(m4, ai4);
        this.addSprite(m5, ai5);
        this.addSprite(m7, ai7);
        this.addSprite(m8, ai8);
        this.addSprite(m9, ai9);
    }
}
