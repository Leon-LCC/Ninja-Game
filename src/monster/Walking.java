package monster;

import fsm.CyclicSequence;
import fsm.ImageState;
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

import static monster.Monster.Event.FALL;


public class Walking extends CyclicSequence {
    private final Monster monster;
    private final StateMachine stateMachine;
    private final Set<Integer> damagingStateNumbers = new HashSet<>(List.of(2));

    public Walking(Monster monster, StateMachine stateMachine, List<ImageState> states) {
        super(states);
        this.monster = monster;
        this.stateMachine = stateMachine;
    }

    @Override
    public void update() {
        if (monster.isAlive()) {
            super.update();
            for (Direction direction : monster.getDirections()) {
                Dimension offset = direction.translate();
                monster.getWorld().move(monster, new Dimension(offset.width/2, offset.height));
            }
        }
        List<Item> items = monster.getWorld().getCurrentMapItems();
        Rectangle bodyEXtend = monster.getBody();
        bodyEXtend.setSize(bodyEXtend.width, bodyEXtend.height+3);
        boolean OnTheFloor = false;
        for(Item item : items){
            if(item instanceof Floor){
                if (bodyEXtend.intersects(item.getRange())) {
                    OnTheFloor = true;
                    break;
                }
            }
        }
        if(!OnTheFloor){
            stateMachine.trigger(FALL);
        }
        if (damagingStateNumbers.contains(currentPosition)) {
            effectDamage();
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
    public String toString() {
        return "Walking";
    }
}
