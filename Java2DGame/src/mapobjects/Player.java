package mapobjects;

import game.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    //final dimensions
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static final int SCALE = 8;

    //position of the player
    private int x;
    private int y;


    //Health
    private int health;

    //image of player
    private BufferedImage image;


    public Player(int x, int y) {

        //set the original pos
        this.x = x;
        this.y = y;
        init();


    }

    public void init() {

        health = 100;
        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(getClass().getResourceAsStream("/Tilesets/player_tilesheet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = tempImage.getSubimage(0,0, (WIDTH - 6)*SCALE, HEIGHT*SCALE);

    }

    public void draw(Graphics2D g) {


        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * sets a new position based on what key was pressed
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {


        this.x += x;
        this.y += y;
    }
    public void setNewPosition(int x, int y) {

        this.x = x;
        this.y = y;
    }

    /**
     * find out if there was a collision with blocked wall
     */
    public boolean wasCollision() {




        return false;
    }
    /**
     * Finds out if the character has gone off screen
     */
    public boolean outOfBounds() {

        //touches the left wall
        if(x < 0) {
            return true;
        }

        //touches the right wall
        if(x > GamePanel.WIDTH - WIDTH) {
            return true;
        }
        //touches the ceiling of map
        if(y < 0) {
            return true;
        }
        //touches the floor of map
        if(y > GamePanel.HEIGHT - HEIGHT) {
            return true;
        }
        return false;
    }
    public int getX() {return x;}
    public int getY() {return y;}

    public boolean hasEncountered() {
        return true;
    }

}
