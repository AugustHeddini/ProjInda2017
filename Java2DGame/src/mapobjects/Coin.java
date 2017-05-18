package mapobjects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by johan on 2017-05-18.
 */
public class Coin implements Item {

    private BufferedImage image;

    private int x;
    private int y;

    private int value;

    public Coin(int x, int y, int value, String path) {

        this.x = x;
        this.y = y;
        this.value = value;

        init(path);
    }

    public void init(String path) {

        BufferedImage tempImage = null;
        try {
            tempImage = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void setPosition(int x, int y) {

    }
}
