package controller;

import model.Direction;
import model.World;
import monster.Monster;
import ninja.Ninja;

import java.awt.*;
import java.util.Set;

import static java.lang.Math.abs;
import static java.lang.Math.random;

public class MonsterNormalAI implements AI{
    private World world;
    private Ninja player;
    private Monster owner;
    private final int OriginalLocation;

    public MonsterNormalAI(World world, Ninja player, Monster owner){
        this.world = world;
        this.player = player;
        this.owner = owner;
        this.OriginalLocation = owner.getLocation().x;
    }

    public void decide(){
        Set<Direction> origin = this.owner.getDirections();
        Point pl = this.player.getLocation();
        Point ol = this.owner.getLocation();
        double dx = pl.getX() - ol.getX();
        double dy = pl.getY() - ol.getY();
        if(abs(dx)<200 && abs(dy)<50) {
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
            double deviation = this.OriginalLocation - ol.getLocation().x;
            double changeAction = random();
            if (abs(deviation) > 50) {
                if(deviation > 0){
                    if (origin.contains(Direction.LEFT)) {
                        this.owner.stop(Direction.LEFT);
                    }
                    this.owner.move(Direction.RIGHT);
                }else{
                    if (origin.contains(Direction.RIGHT)) {
                        this.owner.stop(Direction.RIGHT);
                    }
                    this.owner.move(Direction.LEFT);
                }
            }else if (changeAction < 0.05){
                double action = random();
                if(action < 0.4){
                    if (origin.contains(Direction.LEFT)) {
                        this.owner.stop(Direction.LEFT);
                    }
                    this.owner.move(Direction.RIGHT);
                }else if(action < 0.8){
                    if (origin.contains(Direction.RIGHT)) {
                        this.owner.stop(Direction.RIGHT);
                    }
                    this.owner.move(Direction.LEFT);
                }else{
                    this.owner.stop(Direction.RIGHT);
                    this.owner.stop(Direction.LEFT);
                }
            }
        }
    }
}