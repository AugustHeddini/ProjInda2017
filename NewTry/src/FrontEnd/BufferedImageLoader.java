package FrontEnd;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Jåström on 2017-05-09.
 */
public class BufferedImageLoader {

    public BufferedImage loadImage(String pathRelativeToThis) throws IOException {

        URL url = this.getClass().getResource(pathRelativeToThis);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(pathRelativeToThis));
        } catch (IOException e) {
        }
        return img;

    }
}
