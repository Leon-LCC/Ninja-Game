package map.LV1;

import controller.AI;
import controller.MonsterNormalAI;
import map.conversation.MapConversation;
import map.mapItems.*;
import media.AudioPlayer;
import model.Map;
import model.World;
import monster.Monster;
import ninja.Ninja;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StartMap extends Map {

    public StartMap(){
        this.addItem(new BackGround(new Point(0, 0), new Dimension(1370, 700), 1));
        List<String> titles = new ArrayList<>();
        List<String> texts = new ArrayList<>();
        titles.add("      Story");
        texts.add("Once upon a time, there was");
        texts.add("a badass, Jack the pumpkin.");
        texts.add("He attacked the ninja village");
        texts.add("and kidnapped other ninjas.");
        texts.add("");
        texts.add("...Press ENTER to continue...");
        titles.add("      Story");
        texts.add("You're the bravest ninja in");
        texts.add("the village. Beat Jack and");
        texts.add("those mind-controlled ninjas!");
        titles.add("     TUTORIAL");
        texts.add("W: Jump");
        texts.add("A: Move Left");
        texts.add("D: Move Right");
        texts.add("S: Teleport to the next level");
        titles.add("      GEMS");
        texts.add("Beat slimes to get gems.");
        texts.add("There are 4 types of gems:");
        texts.add("Yellow, Green, Red, Blue");
        texts.add("Collect them to use skills.");
        titles.add("     ATTACK");
        texts.add("J: Basic Attack");
        texts.add("K: Throw Kunai");
        texts.add("L: Shinra Tensei [all gems * 1]");
        texts.add("(Deal damage to all enemies)");
        titles.add("     SKILL");
        texts.add("U: Block [yellow gem * 2]");
        texts.add("(resist damages from enemies)");
        texts.add("I: Boost [green gem * 2]");
        texts.add("(increase max HP)");
        titles.add("     SKILL");
        texts.add("O: Power [red gem * 2]");
        texts.add("(increase ninja's damage)");
        texts.add("P: Heal [blue gem * 2]");
        texts.add("(heal ninja when injured)");
        titles.add("     PORTAL");
        texts.add("Collect all mushrooms in the");
        texts.add("map to open the portal and");
        texts.add("teleport to the next level!");
        texts.add("(Hotkey for teleporting: S)");
        titles.add("     Tricks");
        texts.add("You can attack while jumping.");
        texts.add("This makes you jump higher.");
        texts.add("Use W+J+K or W+K+J to do a");
        texts.add("tripple jump.");
        int[] lines_per_page = {6, 3, 4, 4, 4, 4, 4, 4, 4};
        this.setConversation(new MapConversation(new Point(280, 20), new Dimension(750, 400), 1, texts, lines_per_page, titles));
        this.addItem(new Floor(new Point(-1, 0), new Dimension(1, 700), 2));
        this.addItem(new Floor(new Point(0, 600), new Dimension(200, 70), 1));
        this.addItem(new Floor(new Point(200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(400, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(600, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(800, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1000, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(1200, 600), new Dimension(200, 70), 2));
        this.addItem(new Floor(new Point(300, 200), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(370, 200), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(440, 200), new Dimension(70, 50), 15));
        this.addItem(new Floor(new Point(700, 400), new Dimension(70, 50), 13));
        this.addItem(new Floor(new Point(770, 400), new Dimension(70, 50), 14));
        this.addItem(new Floor(new Point(840, 400), new Dimension(70, 50), 15));
        this.addItem(new Floor(new Point(1000, 80), new Dimension(100, 50), 1));
        this.addItem(new Floor(new Point(1100, 80), new Dimension(100, 50), 2));
        this.addItem(new Floor(new Point(1200, 80), new Dimension(100, 50), 3));
        this.addItem(new Floor(new Point(1000, 130), new Dimension(100, 50), 4));
        this.addItem(new Floor(new Point(1100, 130), new Dimension(100, 50), 5));
        this.addItem(new Floor(new Point(1200, 130), new Dimension(100, 50), 6));
        this.addItem(new Floor(new Point(1000, 180), new Dimension(100, 50), 12));
        this.addItem(new Floor(new Point(1100, 180), new Dimension(100, 50), 9));
        this.addItem(new Floor(new Point(1200, 180), new Dimension(100, 50), 16));

        this.addItem(new Crate(new Point(1110, 20), new Dimension(60, 60), 1));
        this.addItem(new Stone(new Point(1160, 50), new Dimension(50, 30), 1));
        this.addItem(new Crate(new Point(350, 140), new Dimension(60, 60), 1));
        this.addItem(new Crate(new Point(410, 140), new Dimension(60, 60), 1));
        this.addItem(new Crate(new Point(380, 80), new Dimension(60, 60), 1));
        this.addItem(new Bush(new Point(320, 170), new Dimension(50, 30), 1));
        this.addItem(new Stone(new Point(750, 370), new Dimension(50, 30), 1));
        this.addItem(new Tree(new Point(780, 225), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(5, 425), new Dimension(168, 175), 3));
        this.addItem(new Tree(new Point(20, 350), new Dimension(240, 250), 2));
        this.addItem(new Stone(new Point(150, 570), new Dimension(50, 30), 1));
        this.addItem(new Sign(new Point(220, 530), new Dimension(60, 70), 2, "Start"));
        this.addItem(new Tree(new Point(1020, 560), new Dimension(100, 40), 1));
        this.addItem(new Bush(new Point(510, 540), new Dimension(100, 60), 3));
        this.addItem(new Bush(new Point(500, 570), new Dimension(50, 30), 2));
        this.addItem(new Bush(new Point(560, 555), new Dimension(75, 45), 4));
        this.addItem(new Bush(new Point(1220, 570), new Dimension(50, 30), 2));
        this.addItem(new Bush(new Point(960, 570), new Dimension(50, 30), 4));
        this.addGem(new Mushroom(new Point(1220, 55), new Dimension(30, 25), 1));
    }

    @Override
    public void init(World world){
        super.init(world);
        Monster m1 = new Monster(100, 100, new Point(350, 157), Generator.getGem());
        Monster m2 = new Monster(100, 100, new Point(1220, 557), Generator.getGem());
        Monster m3 = new Monster(100, 100, new Point(760, 357), Generator.getGem());
        Monster m4 = new Monster(100, 100, new Point(1220, 37), Generator.getGem());
        Monster m5 = new Monster(100, 100, new Point(550, 557), Generator.getGem());
        Monster m6 = new Monster(100, 100, new Point(1070, 37), Generator.getGem());
        AI ai1 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m1);
        AI ai2 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m2);
        AI ai3 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m3);
        AI ai4 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m4);
        AI ai5 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m5);
        AI ai6 =  new MonsterNormalAI(world,(Ninja)world.getPlayers().get(0),m6);
        this.addSprite(m1, ai1);
        this.addSprite(m2, ai2);
        this.addSprite(m3, ai3);
        this.addSprite(m4, ai4);
        this.addSprite(m5, ai5);
        this.addSprite(m6, ai6);
    }
}
