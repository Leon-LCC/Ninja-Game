package ninja;

import fsm.Sequence;
import fsm.State;
import fsm.StateMachine;
import media.AudioPlayer;
import model.Direction;
import model.Sprite;
import model.World;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ninja.Ninja.Event.FALL;

public class JumpThrowing extends Sequence{
    public static final String AUDIO_THROW = "swing2";
    private final Ninja ninja;
    private final StateMachine stateMachine;
    //private final Set<Integer> damagingStateNumbers = new HashSet<>(List.of(6));
    private Rectangle Kunai = new Rectangle(0,0,45,12);
    private Image KunaiImageLeft;
    private Image KunaiImageRight;
    private Direction KunaiDirection;
    private int Gravity = 2;
    private int Speed = 20;

    public JumpThrowing(Ninja ninja, StateMachine stateMachine, List<? extends State> states) {
        super(states);
        this.ninja = ninja;
        this.stateMachine = stateMachine;
        try {
            KunaiImageLeft = ImageIO.read(new File("assets/Ninja1/Kunai/KunaiLeft.png"));
            KunaiImageRight = ImageIO.read(new File("assets/Ninja1/Kunai/KunaiRight.png"));
        } catch (IOException e) {
            System.out.println("Kunai Image Load error");
        }
    }

    @Override
    public void update() {
        if (ninja.isAlive()) {
            super.update();
            //if (damagingStateNumbers.contains(currentPosition)) {
            effectDamage();
            //}
            for (Direction direction : ninja.getDirections()) {
                ninja.getWorld().move(ninja, direction.translate());
            }
            if(Speed >= 0){
                ninja.getWorld().move(ninja, new Dimension(0, -Speed));
                Speed -= Gravity;
            }
            if(KunaiDirection == Direction.RIGHT){
                Kunai.translate(30,0);
            }else {
                Kunai.translate(-30,0);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        Rectangle damageArea = damageArea();
        //g.setColor(Color.BLUE);
        //g.drawRect(damageArea.x, damageArea.y, damageArea.width, damageArea.height);
        if(KunaiDirection == Direction.RIGHT){
            g.drawImage(KunaiImageRight, damageArea.x, damageArea.y, damageArea.width, damageArea.height, null);
        }else {
            g.drawImage(KunaiImageLeft, damageArea.x, damageArea.y, damageArea.width, damageArea.height, null);
        }
    }

    private void effectDamage() {
        World world = ninja.getWorld();
        Rectangle damageArea = damageArea();
        var sprites = world.getCurrentMapSprites(damageArea);
        boolean hasClash = false;
        for (Sprite sprite : sprites) {
            if (ninja != sprite) {
                sprite.onDamaged(damageArea, ninja.getLongRangedamage(), sprite.getGem());
                hasClash = true;
            }
        }
    }

    private Rectangle damageArea() {
        return Kunai;
    }

    @Override
    public void init(){
        KunaiDirection = ninja.getFace();
        Kunai.setBounds(ninja.getArea(new Dimension(55, 35), new Dimension(45, 12)));
        currentPosition = 0;
        Speed = 20;
        this.ninja.jumpThrowCD = true;
        AudioPlayer.playSounds(AUDIO_THROW);
    }

    @Override
    protected void onSequenceEnd() {
        currentPosition = 0;
        stateMachine.trigger(FALL);
    }
}