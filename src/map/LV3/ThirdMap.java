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

public class ThirdMap extends Map {
    public ThirdMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 2));
        this.addItem(new Water(new Point(490, 500), new Dimension(210, 50), 1));
        this.addItem(new Water(new Point(700, 500), new Dimension(200, 50), 1));
        this.addItem(new Water(new Point(900, 500), new Dimension(200, 50), 1));
        this.addItem(new Water(new Point(1100, 500), new Dimension(200, 50), 1));
        this.addItem(new Water(new Point(1300, 500), new Dimension(100, 50), 1));
        this.addItem(new Water(new Point(490, 550), new Dimension(210, 150), 2));
        this.addItem(new Water(new Point(700, 550), new Dimension(200, 150), 2));
        this.addItem(new Water(new Point(900, 550), new Dimension(200, 150), 2));
        this.addItem(new Water(new Point(1100, 550), new Dimension(200, 150), 2));
        this.addItem(new Water(new Point(1300, 550), new Dimension(100, 150), 2));
        this.addItem(new Floor(new Point(0, 460), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(0, 530), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(0, 390), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(0, 320), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(0, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(200, 460), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(200, 530), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(200, 390), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(200, 320), new Dimension(200, 70), 21));
        this.addItem(new Floor(new Point(200, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(400, 460), new Dimension(100, 70), 22));
        this.addItem(new Floor(new Point(400, 530), new Dimension(100, 70), 22));
        this.addItem(new Floor(new Point(400, 600), new Dimension(100, 70), 22));
        this.addItem(new Floor(new Point(400, 390), new Dimension(100, 70), 22));
        this.addItem(new Floor(new Point(400, 320), new Dimension(100, 70), 22));
        this.addItem(new Floor(new Point(400, 250), new Dimension(100, 70), 19));
        this.addItem(new Floor(new Point(520, 250), new Dimension(80, 70), 17));
        this.addItem(new Floor(new Point(520, 320), new Dimension(80, 70), 29));
        this.addItem(new Floor(new Point(600, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(600, 320), new Dimension(200, 70), 25));
        this.addItem(new Floor(new Point(800, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(800, 320), new Dimension(200, 70), 25));
        this.addItem(new Floor(new Point(1000, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(1000, 320), new Dimension(200, 70), 25));
        this.addItem(new Floor(new Point(1200, 250), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(1200, 320), new Dimension(200, 70), 25));
        this.addItem(new Floor(new Point(1370, -500), new Dimension(10, 1000), 1));

        this.addGem(new Mushroom(new Point(1220, 225), new Dimension(30, 25), 2));
        this.addItem(new Portal(new Point(200, 150), new Dimension(100, 100)));
    }

    @Override
    public void init(World world){
        super.init(world);
        Zombie m1 = new Zombie(100,new Point(1200, 160), 1);
        Zombie m2 = new Zombie(150, new Point(900, 160), 2);
        Zombie m3 = new Zombie(150, new Point(750, 160), 1);
        AI ai1 =  new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0),m1);
        AI ai2 =  new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0),m2);
        AI ai3 =  new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0),m3);
        this.addSprite(m1, ai1);
        this.addSprite(m2, ai2);
        this.addSprite(m3, ai3);
    }
}
