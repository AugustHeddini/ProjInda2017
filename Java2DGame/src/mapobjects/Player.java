package mapobjects;

import game.GamePanel;

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

    public static final int MISS = 0;
    public static final int HIT = 10;
    public static final int CRITICAL = 25;

    public static final String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT"};
    public static int CURR_DIRECTION = 1;

    //position of the player
    private int x;
    private int y;


    //Health
    private int health;

    //image of player
    private BufferedImage image;

    private Random random;

    private int score;


    public Player(int x, int y) {

        //set the original pos
        this.x = x;
        this.y = y;
        random = new Random();
        score = 0;
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
        image = tempImage.getSubimage(0, 0, (WIDTH - 6) * SCALE, HEIGHT * SCALE);

    }

    public void draw(Graphics2D g) {


        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    /**
     * sets a new position based on what key was pressed
     *
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


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public int attack() {

        int missOrHit = random.nextInt(3);

        if (missOrHit == 0) {
            return MISS;
        }
        if (missOrHit == 1) {
            return HIT;
        } else {
            return CRITICAL;
        }
    }

    public void damaged(int dmg) {
        health -= dmg;
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

    public int getScore(){ return score; }

    public void increaseScore(int points) {
        score += points;
    }
}
