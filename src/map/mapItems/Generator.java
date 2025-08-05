package map.mapItems;

import monster.Gem;

import java.util.Random;

public final class Generator {
    public final static Random r = new Random();

    public static Gem getGem() {
        switch(r.nextInt(4)) {
            case 0:
                System.out.println("Summon yellow");
                return Gem.YELLOW;
            case 1:
                System.out.println("Summon green");
                return Gem.GREEN;
            case 2:
                System.out.println("Summon red");
                return Gem.RED;
            case 3:
                System.out.println("Summon blue");
                return Gem.BLUE;
        }
        System.out.println("Generator went wrong");
        return null;
    }
}