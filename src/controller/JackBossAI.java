package controller;

import jack.Jack;
import model.Direction;
import model.World;
import ninja.Ninja;
import zombie.Zombie;

import java.awt.*;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class JackBossAI implements AI{
    private World world;
    private Ninja player;
    private Jack owner;
    private final int OriginalLocation;
    private int count = -1;
    private int action = 0;
    private boolean Summon = false;
    Zombie m1;
    Zombie m2;
    Zombie m3;
    AI ai1;
    AI ai2;
    AI ai3;

    public JackBossAI(World world, Ninja player, Jack owner){
        this.world = world;
        this.player = player;
        this.owner = owner;
        this.OriginalLocation = owner.getLocation().x;
        m1 = new Zombie(50, new Point(210, 210), 1);
        m2 = new Zombie(50, new Point(550, 60), 2);
        m3 = new Zombie(50, new Point(1100, 110), 1);
        ai1 = new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0), m1);
        ai2 = new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0), m2);
        ai3 = new ZombieNormalAI(world,(Ninja)world.getPlayers().get(0), m3);
    }

    public void decide(){
        count = (count+1)%100;
        Set<Direction> origin = this.owner.getDirections();
        Point pl = this.player.getLocation();
        Point ol = this.owner.getLocation();
        double dx = pl.getX() - ol.getX();
        double dy = pl.getY() - ol.getY();
        if(count == 0){ //Change Action
            double p = random();
            if (p < 0.3){
                action = 0;
            }else if (p >= 0.3 && p < 0.6){
                action = 1;
            }else if (p >= 0.6 && p < 0.8){
                action = 2;
            }else if (p >= 0.8 && p < 0.95){
                action = 3;
            }else if (p >= 0.999 && Summon == false){
                action = 4;
            }
        }
        switch (action) {
            case 0: //Walk left
                if(ol.getX() > 100){
                    this.owner.stop(Direction.RIGHT);
                    this.owner.move(Direction.LEFT);
                    if(random() < 0.5){
                        this.owner.move(Direction.RUN);
                    }
                }else{
                    this.owner.stop(Direction.RUN);
                    this.owner.stop(Direction.LEFT);
                    this.owner.stop(Direction.RIGHT);
                }
                break;
            case 1:
                if(ol.getX() < 1000){
                    this.owner.stop(Direction.LEFT);
                    this.owner.move(Direction.RIGHT);
                    if(random() < 0.5){
                        this.owner.move(Direction.RUN);
                    }
                }else{
                    this.owner.stop(Direction.RUN);
                    this.owner.stop(Direction.LEFT);
                    this.owner.stop(Direction.RIGHT);
                }
                break;
            case 2:
                if(ol.getX() > 600){
                    this.owner.move(Direction.LEFT);
                    this.owner.move(Direction.SLIDE);
                }else{
                    this.owner.move(Direction.RIGHT);
                    this.owner.move(Direction.SLIDE);
                }
                action = 5;
                break;
            case 3:
                if(ol.getX() > 600){
                    this.owner.move(Direction.LEFT);
                    this.owner.move(Direction.JUMP);
                }else{
                    this.owner.move(Direction.RIGHT);
                    this.owner.move(Direction.JUMP);
                }
                action = 5;
                break;
            case 5:
                if (!Summon) {
                    this.owner.getWorld().getCurrentMap().addSprite(m1, ai1);
                    this.owner.getWorld().getCurrentMap().addSprite(m2, ai2);
                    this.owner.getWorld().getCurrentMap().addSprite(m3, ai3);
                    Summon = true;
                }
                action = 5;
                break;
            default:
                this.owner.stop(Direction.JUMP);
                this.owner.stop(Direction.RUN);
                this.owner.stop(Direction.LEFT);
                this.owner.stop(Direction.RIGHT);
                break;
        }
    }
}