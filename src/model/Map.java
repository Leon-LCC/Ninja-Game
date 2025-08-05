package model;

import controller.AI;
import map.mapItems.Portal;
import map.mapItems.Gems;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;

public class Map {
    private final List<Item> items  = new CopyOnWriteArrayList<>();

    private final List<Sprite> sprites = new CopyOnWriteArrayList<>();
    private final List<Gems> gems = new CopyOnWriteArrayList<>();
    private List<AI> ais = new CopyOnWriteArrayList<>();
    protected World world;
    private Conversation conversation = null;
    private boolean EndConversation = true;
    private Item portal = null;
    protected boolean bossDead = false;
    public int BGM = 0;

    public void init(World world){
        setWorld(world);
    }

    public void setWorld(World world){
        this.world = world;
        for(Item item : items){
            item.setWorld(world);
        }
    }

    public boolean isEndConversation(){
        return EndConversation;
    }

    public List<Item> getItems(){
        return this.items;
    }

    public List<Sprite> getSprites(){
        return this.sprites;
    }

    public List<Gems> getGems(){
        return this.gems;
    }

    public Portal getPortal(){
        if(items.get(items.size()-1) instanceof Portal){
            return (Portal) items.get(items.size()-1);
        }else {
            return null;
        }
    }

    public void addItems(Item... items) {
        stream(items).forEach(this::addItem);
    }

    public void addItem(Item item) {
        if(item instanceof Portal){
            portal = item;
            portal.setWorld(this.world);
        }else{
            items.add(item);
            item.setWorld(this.world);
        }
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setWorld(null);
    }

    public void addSprite(Sprite sprite, AI ai) {
        sprites.add(sprite);
        ais.add(ai);
        sprite.setWorld(world);
    }

    public void removeSprite(Sprite sprite) {
        int idx = sprites.indexOf(sprite);
        sprites.remove(sprite);
        ais.remove(idx);
        sprite.setWorld(null);
    }

    public void addGems(Gems... gems) {
        stream(gems).forEach(this::addGem);
    }

    public void addGem(Gems gem) {
        gems.add(gem);
        gem.setWorld(this.world);
    }

    public void removeGem(Gems gem) {
        gems.remove(gem);
        gem.setWorld(null);
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
        EndConversation = false;
    }

    public void nextConversation(){
        conversation.update();
        if(conversation.isEnd()){
            EndConversation = true;
        }
    }

    public void update(){
        for(int i = 0; i < sprites.size(); i++){
            ais.get(i).decide();
            sprites.get(i).update();
            if(sprites.get(i).DeadEnd == true){
                world.removeSprite(sprites.get(i), sprites.get(i).getGem());
            }
        }
        if(portal != null && (world.CheckAllMushroom(2) || this.bossDead == true)){
            items.add(portal);
        }
        if(items.get(items.size()-1) instanceof Portal){
            items.get(items.size()-1).update();
        }
    }

    public void render(Graphics g){
        for(Item item : items){
            item.render(g);
        }
        for (Gems gem : gems) {
            gem.render(g);
        }
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
        if(!EndConversation){
            conversation.render(g);
        }
    }

    public void renderconversation(Graphics g) {
        conversation.render(g);
    }
}
