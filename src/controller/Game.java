package controller;

import ninja.Ninja;
import model.Direction;
import model.World;
import model.Backpack;
import skill.*;
import map.mapItems.Gems;


public class Game extends GameLoop {
    private final Ninja p1;
    private final World world;
    private final Backpack backpack = new Backpack();
    private final Skill holynova, block, boost, power, heal;

    public Game(World world, Ninja p1) {
        this.p1 = p1;
        this.world = world;
        this.holynova = new Holynova(p1);
        this.block = new Block(p1);
        this.boost = new Boost(p1);
        this.power = new Power(p1);
        this.heal = new Heal(p1);
    }

    public void moveNinja(Direction direction) {
        p1.move(direction);
    }

    public void stopNinja(Direction direction) {
        p1.stop(direction);
    }

    public void attack() {
        p1.attack();
    }

    public void throwing(){
        p1.throwing();
    }

    public void nextLevel(){
        world.nextLevel();
    }

    public void nextConversation(){
        world.nextConversation();
    }

    public Ninja getPlayer() {
        return p1;
    }

    public int[] getBackpack() {
        return backpack.getCounter();
    }

    public int getMushroom(){
        return backpack.getMushroom();
    }

    public void addBackpack(Gems gem) {
        backpack.add(gem);
    }

    public boolean CheckAllMushroom(int required){
        return backpack.isEnoughMushroom(required);
    }

    public void useAbility(String ability) {
        Skill skill = null;
        switch (ability) {
            case "holynova":
                skill = holynova;
                break;
            case "block":
                skill = block;
                break;
            case "boost":
                skill = boost;
                break;
            case "power":
                skill = power;
                break;
            case "heal":
                skill = heal;
                break;
            default:
                return;
        }
        if (backpack.isValid(skill.getRequired())) {
            p1.addBuff(skill.getInfo());
            backpack.useGems(skill.getRequired());
            skill.doEffect();
        }
        return;
    }

    @Override
    protected World getWorld() {
        return world;
    }
}
