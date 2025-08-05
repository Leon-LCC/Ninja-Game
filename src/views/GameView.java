package views;

import controller.Game;
import controller.GameLoop;
import model.Direction;
import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GameView extends JFrame {
    public static final int HEIGHT = 700;
    public static final int WIDTH = 1370;
    public static final int P1 = 1;
    private final Canvas canvas = new Canvas();
    private final Game game;
    Image[] image = new Image[] {null, null, null, null, null, null};

    public GameView(Game game) throws HeadlessException {
        this.game = game;
        game.setView(canvas);
    }

    public void launch() {
        // GUI Stuff
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvas);
        setSize(WIDTH, HEIGHT);
        setContentPane(canvas);
        setVisible(true);

        // Keyboard listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game.moveNinja(Direction.JUMP);
                        break;
                    case KeyEvent.VK_A:
                        game.moveNinja(Direction.LEFT);
                        break;
                    case KeyEvent.VK_S:
                        game.nextLevel();
                        break;
                    case KeyEvent.VK_D:
                        game.moveNinja(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_J:
                        game.attack();
                        break;
                    case KeyEvent.VK_K:
                        game.throwing();
                        break;
                    case KeyEvent.VK_L:
                        game.useAbility("holynova");
                        break;
                    case KeyEvent.VK_U:
                        game.useAbility("block");
                        break;
                    case KeyEvent.VK_I:
                        game.useAbility("boost");
                        break;
                    case KeyEvent.VK_O:
                        game.useAbility("power");
                        break;
                    case KeyEvent.VK_P:
                        game.useAbility("heal");
                        break;
                    case KeyEvent.VK_ENTER:
                        game.nextConversation();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_A:
                        game.stopNinja(Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.stopNinja(Direction.RIGHT);
                        break;
                }
            }
        });
    }

    public class Canvas extends JPanel implements GameLoop.View {
        private World world;

        @Override
        public void render(World world) {
            this.world = world;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            // Now, let's paint
            g.setColor(Color.WHITE); // paint background with all white
            g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);

            world.render(g); // ask the world to paint itself and paint the sprites on the canvas
            UIrender(g);
        }
    }

    private void UIrender(Graphics g) {
        try {
            image[0] = ImageIO.read(new File("assets/Object/Gem_Yellow.png"));
            image[1] = ImageIO.read(new File("assets/Object/Gem_Green.png"));
            image[2] = ImageIO.read(new File("assets/Object/Gem_Red.png"));
            image[3] = ImageIO.read(new File("assets/Object/Gem_Blue.png"));
            image[4] = ImageIO.read(new File("assets/Object/Mushroom_1.png"));
            image[5] = ImageIO.read(new File("assets/window/state_win.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] bp = game.getBackpack();
        g.setColor(Color.WHITE);
        Font myFont = new Font ("Courier New", 1, 30);
        g.setFont(myFont);
        for (int i = 0; i < 4; i++) {
            g.drawImage(image[5], 2, 50*i+2, 90, 46, null);
            g.drawImage(image[i], 8, 50*i+15, 20, 20, null);
            g.drawString(String.valueOf(bp[i]), 50, 50*i+35);
        }
        g.drawImage(image[5], 2, 50*4+2, 90, 46, null);
        g.drawImage(image[4], 8, 50*4+15, 20, 20, null);
        g.drawString(String.valueOf(game.getMushroom()), 50, 50*4+35);
    }
}
