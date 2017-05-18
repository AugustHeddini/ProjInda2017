package mapobjects;

import tilemap.Pathfinder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by johan on 2017-05-17.
 */
public class Wizard implements Monster {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;

    public static final int MISS = 0;
    public static final int HIT = 10;
    public static final int CRITICAL = 15;


    //image
    private BufferedImage image;


    //position
    private int x;
    private int y;

    //stats
    private int health;
    private Random random;

    private Pathfinder finder;

    private int points = 100;

    public Wizard(int x, int y, String path) {

        this.x = x;
        this.y = y;
        health = 75;
        random = new Random();
        init(path);

    }

    private void init(String path) {

        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = tempImage.getSubimage(0, HEIGHT * 11 - 5, WIDTH, HEIGHT);
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

    @Override
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

    public void setFinder(Pathfinder finder) {
        this.finder = finder;
    }

    public void pathFind(int targetX, int targetY) {

        if((abs(targetX/16 - x/16) + abs(targetY/16 - y/16)) == 0) {
            return;
        } else {
            // Placeholder to trigger an attack at melee range
            if ((abs(targetX / 16 - x / 16) + abs(targetY / 16 - y / 16)) == 1) {
                attack();
            } else {
                //pathfinding
                int dir = finder.findPath(x / 16, y / 16, targetX / 16, targetY / 16);

                switch (dir) {
                    case 0:
                        setPosition(0, -16);
                        break;
                    case 1:
                        setPosition(0, 16);
                        break;
                    case 2:
                        setPosition(-16, 0);
                        break;
                    case 3:
                        setPosition(16, 0);
                        break;
                    case 4:
                        break;
                    default:
                        System.out.println("Error in pathfinding");
                        break;
                }
            }
        }

    }
    public int getPoints() { return points;}
}
