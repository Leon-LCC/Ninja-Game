package controller;

import model.Direction;
import model.World;
import monster.Monster;
import ninja.Ninja;

import java.awt.*;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class NinjaBossAI implements AI{
    private World world;
    private Ninja player;
    private Ninja owner;
    private final int OriginalLocation;
    private int count = -1;
    private int action = 0;

    public NinjaBossAI(World world, Ninja player, Ninja owner){
        this.world = world;
        this.player = player;
        this.owner = owner;
        this.OriginalLocation = owner.getLocation().x;
    }

    public void decide(){
        count = (count+1)%500;
        Set<Direction> origin = this.owner.getDirections();
        Point pl = this.player.getLocation();
        Point ol = this.owner.getLocation();
        double dx = pl.getX() - ol.getX();
        double dy = pl.getY() - ol.getY();
        if(count == 0){ //Change Action
            if (random() < 0.5){
                action = 0;
            }else{
                action = 1;
            }
        }
        if(action == 0){
            if(abs(dx)>300) {
                if (dx > 0) {
                    if (origin.contains(Direction.LEFT)) {
                        this.owner.stop(Direction.LEFT);
                    }
                    this.owner.move(Direction.RIGHT);
                } else {
                    if (origin.contains(Direction.RIGHT)) {
                        this.owner.stop(Direction.RIGHT);
                    }
                    this.owner.move(Direction.LEFT);
                }
            }else{
                if (origin.contains(Direction.LEFT)) {
                    this.owner.stop(Direction.LEFT);
                }
                if (origin.contains(Direction.RIGHT)) {
                    this.owner.stop(Direction.RIGHT);
                }
                this.owner.throwing();
            }
        }else{
            if(abs(dx)>70) {
                if (dx > 0) {
                    if (origin.contains(Direction.LEFT)) {
                        this.owner.stop(Direction.LEFT);
                    }
                    this.owner.move(Direction.RIGHT);
                } else {
                    if (origin.contains(Direction.RIGHT)) {
                        this.owner.stop(Direction.RIGHT);
                    }
                    this.owner.move(Direction.LEFT);
                }
            }else{
                if (origin.contains(Direction.LEFT)) {
                    this.owner.stop(Direction.LEFT);
                }
                if (origin.contains(Direction.RIGHT)) {
                    this.owner.stop(Direction.RIGHT);
                }
                this.owner.attack();
            }
        }

    }
}