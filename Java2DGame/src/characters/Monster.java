package characters;

/**
 * Created by johan on 2017-05-12.
 */
public interface Monster {

    int getX();
    int getY();
    void setPosition(int x, int y);
    void draw(java.awt.Graphics2D g);

}
