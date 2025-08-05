import controller.Game;
import map.LV1.SecondMap;
import map.LV1.ThirdMap;
import map.LV2.StartMap;
import map.conversation.MapConversation;
import monster.Monster;
import ninja.*;
import model.*;
import views.GameView;
import skill.*;
import zombie.Zombie;

import java.awt.*;
import java.io.File;

import static media.AudioPlayer.addAudioByFilePath;

public class Main {
    public static void main(String[] args) {
        addAudioByFilePath(Walking.AUDIO_STEP1, new File("assets/audio/step1.wav"));
        addAudioByFilePath(Walking.AUDIO_STEP2, new File("assets/audio/step2.wav"));
        addAudioByFilePath(Walking.AUDIO_STEP3, new File("assets/audio/step3.wav"));
        addAudioByFilePath(Walking.AUDIO_STEP4, new File("assets/audio/step4.wav"));
        addAudioByFilePath(Attacking.AUDIO_SWORD_SWING_1, new File("assets/audio/swing1.wav"));
        addAudioByFilePath(Throwing.AUDIO_THROW, new File("assets/audio/swing2.wav"));
        addAudioByFilePath(JumpAttacking.AUDIO_AIR_SWING, new File("assets/audio/swing3.wav"));
        addAudioByFilePath(Jumping.AUDIO_JUMP, new File("assets/audio/jump.wav"));
        addAudioByFilePath(Falling.AUDIO_LAND, new File("assets/audio/jumpland.wav"));
        addAudioByFilePath(HealthPointSpriteForMonster.AUDIO_SLIMEDIE, new File("assets/audio/slimedie.wav"));
        addAudioByFilePath(Monster.AUDIO_SLIMESCREAM, new File("assets/audio/slime_scream.wav"));
        addAudioByFilePath(World.AUDIO_TELEPORT, new File("assets/audio/teleport.wav"));
        addAudioByFilePath(World.AUDIO_PICK, new File("assets/audio/pick.wav"));
        addAudioByFilePath(World.AUDIO_BGM1, new File("assets/audio/BGM1.wav"));
        addAudioByFilePath(World.AUDIO_BGM2, new File("assets/audio/BGM2.wav"));
        addAudioByFilePath(World.AUDIO_BGM3, new File("assets/audio/BGM3.wav"));
        addAudioByFilePath(World.AUDIO_BGM4, new File("assets/audio/BGM4.wav"));
        addAudioByFilePath(World.AUDIO_BGM5, new File("assets/audio/BGM5.wav"));
        addAudioByFilePath(World.AUDIO_BGM6, new File("assets/audio/BGM6.wav"));
        addAudioByFilePath(zombie.Attacking.AUDIO_ZOMBIEATTACK, new File("assets/audio/zombie_attack.wav"));
        addAudioByFilePath(zombie.Zombie.AUDIO_ZOMBIESCREAM1, new File("assets/audio/zombie1.wav"));
        addAudioByFilePath(zombie.Zombie.AUDIO_ZOMBIESCREAM2, new File("assets/audio/zombie2.wav"));
        addAudioByFilePath(zombie.Zombie.AUDIO_ZOMBIEDEAD, new File("assets/audio/zombie3.wav"));
        addAudioByFilePath(jack.Walking.AUDIO_STEP5, new File("assets/audio/step5.wav"));
        addAudioByFilePath(jack.Walking.AUDIO_STEP7, new File("assets/audio/step7.wav"));
        addAudioByFilePath(jack.Falling.AUDIO_LAND, new File("assets/audio/land.wav"));
        addAudioByFilePath(jack.Sliding.AUDIO_SLIDE, new File("assets/audio/slide.wav"));
        addAudioByFilePath(MapConversation.AUDIO_CHANGEPAGE, new File("assets/audio/changepage.wav"));


        // initialization procedure
        Ninja p1 = new Ninja(100, 50, new Point(20, 512), 1);
        World world = new World(new NinjaCollisionHandler(), p1);  // model
        world.addMap(new map.LV1.StartMap());
        world.addMap(new map.LV1.SecondMap());
        world.addMap(new map.LV1.ThirdMap());
        world.addMap(new map.LV2.StartMap());
        world.addMap(new map.LV2.SecondMap());
        world.addMap(new map.LV2.ThirdMap());
        world.addMap(new map.LV3.StartMap());
        world.addMap(new map.LV3.SecondMap());
        world.addMap(new map.LV3.ThirdMap());
        world.addMap(new map.LV4.StartMap());
        world.addMap(new map.LV4.SecondMap());
        world.addMap(new map.LV4.ThirdMap());
        Game game = new Game(world, p1);  // controller
        world.setGame(game);
        GameView view = new GameView(game);  // view
        game.start();  // run the game and the game loop
        view.launch(); // launch the GUI
    }
}