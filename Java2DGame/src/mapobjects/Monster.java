package mapobjects;

import tilemap.Pathfinder;

/**
 * Created by johan on 2017-05-12.
 */
public interface Monster {


    int getX();

    int getY();

    void setPosition(int x, int y);

    void draw(java.awt.Graphics2D g);

    int getHealth();

    int attack();

    void damaged(int dmg);

    void pathFind(int x, int y);
    void setFinder(Pathfinder finder);
}
