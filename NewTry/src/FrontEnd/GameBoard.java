package FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Jåström on 2017-05-09.
 */
public class GameBoard extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int SCALE = 1;
    private static final String NAME = "Spooky Dungeon";

    BufferedImage sprite;

    public GameBoard() {

        buildGUI();
        init();
    }

    private void buildGUI() {

        //Set the sizes for the Canvas
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

        //initiate the frame
        setName(NAME);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);


        //Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //resize everything to fit the window
        pack();

    }

    private void init() {

        BufferedImageLoader loader = new BufferedImageLoader();
        BufferedImage spriteSheet = null;
        try {
            spriteSheet = loader.loadImage("C:\\Users\\Jåström\\Documents\\Programmering\\GitHub\\NewTry\\Images\\roguelikeCity_magenta.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpriteSheet ss = new SpriteSheet(spriteSheet);


        sprite = ss.grabSprite(16,16, 16, 16);

    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(sprite, 100, 100, 50, 50, null);
        repaint();

    }

    public static void main(String[] args) {

        GameBoard myBoard = new GameBoard();
    }
}
