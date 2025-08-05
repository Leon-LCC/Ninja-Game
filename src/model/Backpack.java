package model;

import map.mapItems.Gems;

public class Backpack {
    private int[] counter = new int[5];

    public Backpack() {}

    public int[] getCounter() {return counter;}

    public void add(Gems gem) {
        switch (gem.getGem()) {
            case YELLOW:
                System.out.println("Get yellow gem");
                counter[0]++;
                break;
            case GREEN:
                System.out.println("Get green gem");
                counter[1]++;
                break;
            case RED:
                System.out.println("Get red gem");
                counter[2]++;
                break;
            case BLUE:
                System.out.println("Get blue gem");
                counter[3]++;
                break;
            case MUSHROOM:
                System.out.println("Get mushroom");
                counter[4]++;
                break;
            default:
                break;
        }
    }

    public boolean isValid(int[] required) {
        for (int i = 0; i < 4; i++) {
            if (required[i] > counter[i]) return false;
        }
        return true;
    }

    public void useGems(int[] required) {
        for (int i = 0; i < 4; i++) {
            counter[i] -= required[i];
        }
    }

    public boolean isEnoughMushroom(int required){
        if(counter[4] >= required){
            counter[4] = 0;
            return true;
        }
        return false;
    }

    public int getMushroom(){
        return counter[4];
    }
}