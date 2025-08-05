package monster;

import fsm.CyclicSequence;
import fsm.State;
import fsm.StateMachine;
import map.mapItems.Floor;
import model.Direction;
import model.Item;
import model.Sprite;
import model.World;
import ninja.Ninja;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static monster.Monster.Event.WALK;

public class Falling extends CyclicSequence {
    private final Monster monster;
    private final StateMachine stateMachine;
    private boolean OnTheFloor = false;
    private int Gravity = 4;
    private int Speed = 4;
    private final Set<Integer> damagingStateNumbers = new HashSet<>(List.of(2));

    public Falling(Monster monster, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.monster = monster;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (monster.isAlive()) {
            super.update();
            for (Direction direction : monster.getDirections()) {
                monster.getWorld().move(monster, direction.translate());
            }
            List<Item> items = monster.getWorld().getCurrentMapItems();
            Rectangle bodyEXtend = monster.getBody();
            bodyEXtend.setSize(bodyEXtend.width, bodyEXtend.height+3);
            for(Item item : items){
                if(item instanceof Floor){
                    if (bodyEXtend.intersects(item.getRange())) {
                        OnTheFloor = true;
                        break;
                    }
                }
            }
            if (!OnTheFloor){
                monster.getWorld().move(monster, new Dimension(0, Speed));
                Speed += Gravity;
            }else{
                currentPosition = 0;
                OnTheFloor = false;
                Speed = 4;
                stateMachine.reset();
                if(!monster.getDirections().isEmpty()) {
                    stateMachine.trigger(WALK);
                }
            }
            if (damagingStateNumbers.contains(currentPosition)) {
                effectDamage();
            }
        }
    }

    private void effectDamage() {
        World world = monster.getWorld();
        Rectangle damageArea = damageArea();
        var sprites = world.getCurrentMapSprites(damageArea);
        boolean hasClash = false;
        for (Sprite sprite : sprites) {
            if (monster != sprite && sprite instanceof Ninja) {
                sprite.onDamaged(damageArea, Math.max(0, monster.getDamage() - sprite.getBlock()), sprite.getGem());
                hasClash = true;
            }
        }
    }

    private Rectangle damageArea() {
        Rectangle bodyEXtend = monster.getBody();
        bodyEXtend.setLocation(bodyEXtend.x-2, bodyEXtend.y-2);
        bodyEXtend.setSize(bodyEXtend.width+4, bodyEXtend.height+4);
        return bodyEXtend;
    }

    @Override
    public void init(){
        super.init();
        Speed = 4;
    }
}
