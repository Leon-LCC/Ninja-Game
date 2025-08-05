package map.LV1;

import controller.AI;
import controller.MonsterNormalAI;
import map.conversation.MapConversation;
import map.mapItems.*;
import model.Map;
import model.World;
import monster.Monster;
import ninja.Ninja;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SecondMap extends Map {
    public SecondMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 1));
        this.addItem(new Water(new Point(590, 600), new Dimension(80, 50), 1));
        this.addItem(new Water(new Point(670, 600), new Dimension(70, 50), 1));
        this.addItem(new Water(new Point(740, 600), new Dimension(100, 50), 1));
        this.addItem(new Water(new Point(590, 650), new Dimension(80, 50), 2));
        this.addItem(new Water(new Point(670, 650), new Dimension(70, 50), 2));
        this.addItem(new Water(new Point(740, 650), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(400, 600), new Dimension(100, 70), 8));
        this.addItem(new Floor(new Point(400, 530), new Dimension(100, 70), 4));
        this.addItem(new Floor(new Point(400, 460), new Dimension(100, 70), 1));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(500, 460), new Dimension(100, 70), 3));
        this.addItem(new Floor(new Point(500, 530), new Dimension(100, 70), 6));
        this.addItem(new Floor(new Point(500, 600), new Dimension(100, 70), 6));
        this.addItem(new Floor(new Point(830, 600), new Dimension(100, 70), 4));
        this.addItem(new Floor(new Point(830, 530), new Dimension(100, 70), 4));
        this.addItem(new Floor(new Point(830, 460), new Dimension(100, 70), 1));
        this.addItem(new Floor(new Point(930, 460), new Dimension(100, 70), 2));
        this.addItem(new Floor(new Point(930, 530), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(930, 600), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(1030, 460), new Dimension(100, 70), 2));
        this.addItem(new Floor(new Point(1030, 530), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(1030, 600), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(1130, 460), new Dimension(100, 70), 2));
        this.addItem(new Floor(new Point(1130, 530), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(1130, 600), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(1230, 460), new Dimension(100, 70), 2));
        this.addItem(new Floor(new Point(1230, 530), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(1230, 600), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(1330, 460), new Dimension(100, 70), 2));
        this.addItem(new Floor(new Point(1330, 530), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(1330, 600), new Dimension(100, 70), 5));
        this.addItem(new Floor(new Point(90, 90), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(160, 90), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(230, 90), new Dimension(70, 50), 15));
        this.addItem(new Floor(new Point(610, 460), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(680, 460), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(750, 460), new Dimension(70, 50), 15));
        this.addItem(new Floor(new Point(1110, 170), new Dimension(70, 50), 1));
        this.addItem(new Floor(new Point(1180, 170), new Dimension(70, 50), 2));
        this.addItem(new Floor(new Point(1250, 170), new Dimension(70, 50), 2));
        this.addItem(new Floor(new Point(1320, 170), new Dimension(70, 50), 2));
        this.addItem(new Floor(new Point(1110, 220), new Dimension(70, 50), 12));
        this.addItem(new Floor(new Point(1180, 220), new Dimension(70, 50), 9));
        this.addItem(new Floor(new Point(1250, 220), new Dimension(70, 50), 9));
        this.addItem(new Floor(new Point(1320, 220), new Dimension(70, 50), 9));

        this.addItem(new Stone(new Point(420, 430), new Dimension(50, 30), 1));
        this.addItem(new Tree(new Point(150, 350), new Dimension(240, 250), 3));
        this.addItem(new Crate(new Point(150, 480), new Dimension(60, 60), 1));
        this.addItem(new Crate(new Point(100, 540), new Dimension(60, 60), 1));
        this.addItem(new Crate(new Point(180, 540), new Dimension(60, 60), 1));
        this.addItem(new Crate(new Point(280, 540), new Dimension(60, 60), 1));
        this.addItem(new Bush(new Point(200, 570), new Dimension(50, 30), 3));
        this.addItem(new Bush(new Point(90, 570), new Dimension(50, 30), 3));
        this.addItem(new Sign(new Point(470, 390), new Dimension(60, 70), 1, "Stop!"));
        this.addItem(new Bush(new Point(690, 430), new Dimension(50, 30), 3));
        this.addItem(new Tree(new Point(110, 50), new Dimension(100, 40), 1));
        this.addItem(new Tree(new Point(830, 285), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(870, 210), new Dimension(240, 250), 2));
    }

    @Override
    public void init(World world){
        super.init(world);
        Monster m1 = new Monster(200, 120, new Point(170, 47), Generator.getGem());
        Monster m2 = new Monster(200, 120, new Point(680, 417), Generator.getGem());
        Monster m3 = new Monster(200, 120, new Point(1100, 417), Generator.getGem());
        Monster m4 = new Monster(200, 120, new Point(1210, 417), Generator.getGem());
        Monster m5 = new Monster(200, 120, new Point(1230, 127), Generator.getGem());
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
    }
}
