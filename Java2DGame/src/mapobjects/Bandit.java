package mapobjects;

import tilemap.Pathfinder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

import static java.lang.Math.abs;

/**
 * Created by johan on 2017-05-16.
 */
public class Bandit implements Monster {

    //Scaling
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    public static final int SCALE = 2;

    //position
    private int x;
    private int y;

    //image
    private String path;
    private BufferedImage image;

    //stats
    private int health;

    // Pathfinder
    private Pathfinder finder;

    public Bandit(int x, int y, String path) {

        this.x = x;
        this.y = y;
        this.path = path;

        health = 30;
        init();
    }
    public void init() {

        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(getClass().getResourceAsStream(path));
        } catch(IOException e) {
            e.printStackTrace();
        }
        image = tempImage.getSubimage(0,0, WIDTH, HEIGHT);
    }

    public void setFinder(Pathfinder finder) {
        this.finder = finder;
    }


    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void pathFind(int targetX, int targetY) {

        // Placeholder to trigger an attack at melee range
        if ( (abs(targetX/16 - x/16) + abs(targetY/16 - y/16)) == 1) {
            attack();
        } else {
            //pathfinding
            int dir = finder.findPath(x/16, y/16, targetX/16, targetY/16);

            switch (dir) {
                case 0: setPosition(0, -16);
                    break;
                case 1: setPosition(0, 16);
                    break;
                case 2: setPosition(-16, 0);
                    break;
                case 3: setPosition(16, 0);
                    break;
                case 4:
                    break;
                default: System.out.println("Error in pathfinding");
                    break;
            }
        }
    }

    public void attack() {

    }

    public void dealDMG(int value) {
        this.health -= value;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void setPosition(int x, int y) {
        this.x += x;
        this.y += y;
    }
    public void setNewPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void draw(Graphics2D g) {

        g.drawImage(image, x, y, WIDTH, HEIGHT, null);
    }
}
