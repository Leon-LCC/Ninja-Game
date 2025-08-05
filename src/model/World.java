package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

import controller.*;
import map.conversation.MapConversation;
import map.mapItems.Portal;
import media.AudioPlayer;
import monster.Gem;
import map.mapItems.Gems;
import ninja.Ninja;


public class World {
    public static final Object AUDIO_TELEPORT = "teleport";
    public static final Object AUDIO_PICK = "pick";
    public static final Object AUDIO_BGM1 = "BGM1";
    public static final Object AUDIO_BGM2 = "BGM2";
    public static final Object AUDIO_BGM3 = "BGM3";
    public static final Object AUDIO_BGM4 = "BGM4";
    public static final Object AUDIO_BGM5 = "BGM5";
    public static final Object AUDIO_BGM6 = "BGM6";
    private final List<Sprite> players = new CopyOnWriteArrayList<>();
    private final List<Map> maps = new CopyOnWriteArrayList<>();
    private int currentMap = 0;
    private final CollisionHandler collisionHandler;
    private Game game;

    public World(CollisionHandler collisionHandler, Sprite... sprites) {
        this.collisionHandler = collisionHandler;
        addPlayers(sprites);
    }

    public void update() {
        if(maps.get(currentMap).isEndConversation() && !this.players.get(0).DeadEnd){
            for (Sprite player : players) {
                player.update();
            }
            maps.get(currentMap).update();
            pickup();
        }else if(this.players.get(0).DeadEnd){
            List<String> titles = new ArrayList<>();
            List<String> texts = new ArrayList<>();
            titles.add("     GAME OVER");
            texts.add("Stand up! Ninja!");
            texts.add("The world needs you!");
            texts.add("Press ENTER to restart.");
            texts.add("(May the God of Ninja be with you!)");
            int[] lines_per_page = {4};
            maps.get(currentMap).setConversation(new MapConversation(new Point(280, 20), new Dimension(750, 400), 1, texts,  lines_per_page, titles));
        }
    }

    public void addPlayers(Sprite... sprites) {
        stream(sprites).forEach(this::addPlayer);
    }

    public void addPlayer(Sprite sprite) {
        players.add(sprite);
        sprite.setWorld(this);
    }

    public void removePlayer(Sprite sprite) {
        players.remove(sprite);
        sprite.setWorld(null);
    }

    public void removeSprite(Sprite sprite, Gem gem) {
        if(gem != null){
            maps.get(currentMap).addGem(new Gems(sprite.getLocation(), new Dimension(30, 30), gem));
        }
        maps.get(currentMap).removeSprite(sprite);
        sprite.setWorld(null);
    }

    public void addMap(Map map) {
        maps.add(map);
        map.init(this);
    }

    public void removeMap(Map map) {
        maps.remove(map);
        map.setWorld(null);
    }

    public void moveNextMap(){
        if(this.maps.size() > this.currentMap+1){
            this.currentMap += 1;
            if(maps.get(currentMap).BGM > 0){
                AudioPlayer.stopSounds();
                switch (maps.get(currentMap).BGM){
                    case 1:
                        AudioPlayer.playSounds(AUDIO_BGM1);
                        break;
                    case 2:
                        AudioPlayer.playSounds(AUDIO_BGM2);
                        break;
                    case 3:
                        AudioPlayer.playSounds(AUDIO_BGM3);
                        break;
                    case 4:
                        AudioPlayer.playSounds(AUDIO_BGM4);
                        break;
                    case 5:
                        AudioPlayer.playSounds(AUDIO_BGM5);
                        break;
                    case 6:
                        AudioPlayer.playSounds(AUDIO_BGM6);
                        break;
                }
            }
        }
    }

    public void moveLastMap(){
        if(this.currentMap > 0) {
            this.currentMap -= 1;
        }
    }

    public void nextLevel(){
        Portal portal = maps.get(currentMap).getPortal();
        if(portal != null && players.get(0).getBody().intersects(portal.getBody())){
            AudioPlayer.playSounds(AUDIO_TELEPORT);
            moveNextMap();
            players.get(0).setLocation(new Point(20, players.get(0).location.y));
        }
    }

    public void nextConversation(){
        if(!maps.get(currentMap).isEndConversation()){
            maps.get(currentMap).nextConversation();
        }
        if(this.players.get(0).DeadEnd){
            Ninja player = (Ninja) players.get(0);
            player.revive();
            this.players.get(0).DeadEnd = false;
            this.players.get(0).setLocation(new Point(20, 512));
            this.currentMap = 0;
            AudioPlayer.playSounds(AUDIO_BGM1);
        }
    }

    public void move(Sprite from, Dimension offset) {
        Point originalLocation = new Point(from.getLocation());
        from.getLocation().translate(offset.width, offset.height);
        if(players.contains(from)) {
            if (from.getLocation().x > 1370- from.getBody().width) {
                moveNextMap();
                from.setLocation(new Point(10, from.location.y));
            } else if (from.getLocation().x < 0 - from.getBody().width) {
                moveLastMap();
                from.setLocation(new Point(1370 - from.getBody().width, from.location.y));
            }
        }
        Rectangle body = from.getBody();
        // collision detection
        for (Sprite to : players) {
            if (to != from && body.intersects(to.getBody())) {
                collisionHandler.handle(originalLocation, from, to);
            }
        }
        List<Sprite> sprites = getCurrentMapSprites();
        for (Sprite to : sprites) {
            if (to != from && body.intersects(to.getBody())) {
                collisionHandler.handle(originalLocation, from, to);
            }
        }
        List<Item> items = getCurrentMapItems();
        for(Item to : items){
            if (body.intersects(to.getRange())) {
                collisionHandler.handle(originalLocation, from, to);
            }
        }
    }

    public Collection<Sprite> getCurrentMapSprites(Rectangle area) {
        List<Sprite> mapSprites = getCurrentMapSprites();
        List<Sprite> sprites = new CopyOnWriteArrayList<>();
        for (Sprite sprite : mapSprites){
            sprites.add(sprite);
        }
        sprites.add(players.get(0));
        return sprites.stream()
                .filter(s -> area.intersects(s.getBody()))
                .collect(toSet());
    }

    public List<Sprite> getCurrentMapSprites() {
        return this.maps.get(currentMap).getSprites();
    }

    public List<Sprite> getPlayers() {
        return players;
    }

    public Map getCurrentMap(){
        return maps.get(currentMap);
    }

    public Collection<Gems> getCurrentMapGems(Rectangle area) {
        List<Gems> gems = getCurrentMapGems();
        return gems.stream()
                .filter(s -> area.intersects(s.getRange()))
                .collect(toSet());
    }

    public List<Item> getCurrentMapItems(){
        return this.maps.get(currentMap).getItems();
    }

    public List<Gems> getCurrentMapGems(){
        return this.maps.get(currentMap).getGems();
    }

    // Actually, directly couple your model with the class "java.awt.Graphics" is not a good design
    // If you want to decouple them, create an interface that encapsulates the variation of the Graphics.
    public void render(Graphics g) {
        if(this.players.get(0).DeadEnd) {
            maps.get(currentMap).renderconversation(g);
        }else{
            maps.get(currentMap).render(g);
            for (Sprite player : players) {
                player.render(g);
            }
        }
    }

    public void setGame(Game game) {
        this.game = game;
        AudioPlayer.playSounds(AUDIO_BGM1);
    }

    private void pickup() {
        var gems = getCurrentMapGems(players.get(0).getRange());
        for (Gems gem : gems) {
            AudioPlayer.playSounds(AUDIO_PICK);
            game.addBackpack(gem);
            maps.get(currentMap).removeGem(gem);
        }
    }

    public boolean CheckAllMushroom(int required){
        return game.CheckAllMushroom(required);
    }
}
