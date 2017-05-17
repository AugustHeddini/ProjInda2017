package mapobjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by johan on 2017-05-16.
 */
public class Bandit implements Monster {

    //Scaling
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static final int SCALE = 2;

    public static final int MISS = 0;
    public static final int HIT = 5;
    public static final int CRITICAL = 10;

    //position
    private int x;
    private int y;

    //image
    private String path;
    private BufferedImage image;

    //stats
    private int health;
    private Random random;

    public Bandit(int x, int y, String path) {

        this.x = x;
        this.y = y;
        this.path = path;
        health = 50;
        random = new Random();
        init();
    }

    public void init() {

        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = tempImage.getSubimage(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g) {


        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }

    @Override
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
}
