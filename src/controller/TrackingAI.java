package controller;

import java.awt.*;
import java.util.Set;

import ninja.Ninja;
import model.Direction;
import model.World;
import model.Sprite;
import monster.Monster;

import static java.lang.Math.abs;

public class TrackingAI implements AI{
	private World world;
	private Ninja player;
	private Monster owner;

	public TrackingAI(World world, Ninja player, Monster owner){
		this.world = world;
		this.player = player;
		this.owner = owner;
	}

	public void decide(){
		Set<Direction> origin = this.owner.getDirections();
		Point pl = this.player.getLocation();
		Point ol = this.owner.getLocation();
		double dx = pl.getX() - ol.getX();
		if(abs(dx)>100) {
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
		}
	}
}