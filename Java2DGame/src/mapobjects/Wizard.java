package mapobjects;

import tilemap.Pathfinder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by johan on 2017-05-17.
 */
public class Wizard implements Monster {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;


    //image
    private BufferedImage image;


    //position
    private int x;
    private int y;

    //stats
    private int health;

    public Wizard(int x, int y, String path) {

        this.x = x;
        this.y = y;
        health = 100;
        init(path);

    }

    private void init(String path) {

        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        image = tempImage.getSubimage(0,0, WIDTH, HEIGHT);
    }

    public void setFinder(Pathfinder finder) {

    }

    public void pathFind(int targetX, int targetY) {

    }

    public void attack() {

    }

    public void dealDMG(int value) {

    }

    public int getHealth(){
        return health;
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
}
