package map.LV4;

import controller.AI;
import controller.JackBossAI;
import controller.NinjaBossAI;
import jack.Jack;
import map.mapItems.*;
import model.Map;
import model.World;
import monster.Monster;
import ninja.Ninja;

import java.awt.*;

public class SecondMap extends Map {
    private Jack boss1 = null;
    private AI ai1;
    private Jack boss2 = null;
    private AI ai2;
    private boolean second_phase = false;

    public SecondMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 2));
        this.addItem(new Floor(new Point(-50, -500), new Dimension(50, 1300), 2));
        this.addItem(new Floor(new Point(1370, -500), new Dimension(50, 1300), 2));
        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(400, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(600, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 70), 18));
        this.addItem(new Floor(new Point(200, 300), new Dimension(70, 50), 31));
        this.addItem(new Floor(new Point(270, 300), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(340, 300), new Dimension(70, 50), 33));
        this.addItem(new Floor(new Point(530, 150), new Dimension(70, 50), 31));
        this.addItem(new Floor(new Point(600, 150), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(670, 150), new Dimension(70, 50), 33));
        this.addItem(new Floor(new Point(1000, 200), new Dimension(70, 50), 31));
        this.addItem(new Floor(new Point(1070, 200), new Dimension(70, 50), 32));
        this.addItem(new Floor(new Point(1140, 200), new Dimension(70, 50), 33));
        this.addItem(new Portal(new Point(1100, 500), new Dimension(100, 100)));
        this.BGM = 5;
    }

    @Override
    public void init(World world){
        super.init(world);
        super.init(world);
        boss1 = new Jack(200, new Point(900, 346), 1);
        ai1 = new JackBossAI(world,(Ninja)world.getPlayers().get(0),boss1);
        this.addSprite(boss1, ai1);
        boss2 = new Jack(100, new Point(boss1.getLocation().x, 92), 2);
        ai2 = new JackBossAI(world,(Ninja)world.getPlayers().get(0),boss2);
    }

    @Override
    public void update(){
        super.update();
        if(boss1.DeadEnd){
            if(!second_phase && !boss2.DeadEnd){
                this.addSprite(boss2, ai2);
                second_phase = true;
            }else{
                this.bossDead = true;
            }
        }
    }
}
