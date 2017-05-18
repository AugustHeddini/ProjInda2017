package mapobjects;

import game.GamePanel;
import tilemap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Player {

    //final dimensions
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static final int SCALE = 8;
    public static final String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT"};
    private static int CURR_DIRECTION = 1;

    // values for attack
    public static int MISS = 0;
    public static int HIT = 15;
    private Random rnd = new Random();

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

    public void attack(int dir, Monster[] monsters) {

        int ax = 0;
        int ay = 0;
        switch (dir) {
            case 0:
                ax = x;
                ay = y - 16;
                break;
            case 1:
                ax = x;
                ay = y + 16;
                break;
            case 2:
                ax = x - 16;
                ay = y;
                break;
            case 3:
                ax = x + 16;
                ay = y;
                break;
            default:
                ax = -1;
                ay = -1;
                break;
        }

        for (Monster monster: monsters) {
            if (monster.getX() == ax && monster.getY() == ay) {
                int hit = rnd.nextInt(3);
                switch (hit) {
                    case 0:
                        monster.dealDMG(MISS);
                        break;
                    case 1:
                        monster.dealDMG(HIT + 4 - rnd.nextInt(9));
                        break;
                    case 2:
                        monster.dealDMG((HIT + 4 - rnd.nextInt(9))*2);
                        break;
                    default:
                        monster.dealDMG(MISS);
                }
            }
        }
        System.out.println("Player position: x = " + x/16 + " y = " + y/16 + " Dir: " + CURR_DIRECTION);
        System.out.println("Attacked at: x = " + ax/16 + " y = " + ay/16);
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
     * Sets the direction the player is facing, allowing for interaction checks.
     * @param direction the index value of the desired direction in DIRECTIONS
     */
    public void setFacingDirection(int direction) {
        if (!(direction >= 0 && direction < DIRECTIONS.length)) {
            System.out.println("Invalid direction input");
        } else {
            CURR_DIRECTION = direction;
        }

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

    public int getCurrDirection() {
        return CURR_DIRECTION;
    }

    public boolean hasEncountered() {
        return true;
    }
}
