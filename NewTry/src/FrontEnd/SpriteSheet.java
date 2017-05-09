package FrontEnd;

import java.awt.image.BufferedImage;

/**
 * Created by Jåström on 2017-05-09.
 */
public class SpriteSheet {


    public BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage ss) {

        this.spriteSheet = ss;
    }

    public BufferedImage grabSprite(int x, int y, int width, int height) {
        BufferedImage sprite = spriteSheet.getSubimage(x, y, width, height);
        return sprite;
    }
}
