package gamestate;

import mapobjects.Bandit;
import mapobjects.Monster;
import mapobjects.Player;
import mapobjects.Wizard;
import game.GamePanel;
import tilemap.Pathfinder;
import tilemap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by johan on 2017-05-17.
 */
public class Level3State extends GameState{

    //The tilemap of the level
    private TileMap tileMap;

    private Pathfinder finder;



    //Needs to be able to hold a character
    private Player myChar;

    private Monster[] monsters =
            {new Bandit(16, 16, "/Tilesets/bandit.png"),
            new Wizard(240, 24, "/Tilesets/wizard.jpg")};

    public Level3State(GameStateManager gsm, Player myChar) {

        this.gsm = gsm;
        this.myChar = myChar;
        init();


    }
    @Override
    public void init() {

        tileMap = new TileMap(16);
        tileMap.loadTiles("/Tilesets/TileSetNew.png");
        tileMap.loadMap("/Maps/MapTrio.csv");
        tileMap.setPosition(0, 0);

        finder = new Pathfinder(tileMap);

        for(Monster monster: monsters) {
            monster.setFinder(finder);
        }

    }

    @Override
    public void draw(Graphics2D g) {



        //clear screen
        g.setColor(new Color(0xaae9af));
        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);

        //draw tilemap
        tileMap.draw(g);

        //draw player
        myChar.draw(g);

        for(Monster monster: monsters) {
            if(monster.getHealth() > 0) {
                monster.draw(g);
            }
        }
    }

    @Override
    public void update() {




    }

    @Override
    public void keyPressed(int k) {

        //Right arrow pressed move 1 tile to the right
        if (k == KeyEvent.VK_RIGHT) {
            if (myChar.getCurrDirection() == 3) {
                setPlayerPosition(16, 0);
                myChar.setFacingDirection(3);
                actMonsterTurn();
            } else {
                myChar.setFacingDirection(3);
            }
        }
        //Left arrow pressed move 1 to the left
        if (k == KeyEvent.VK_LEFT) {

            if (myChar.getCurrDirection() == 2) {
                setPlayerPosition(-16, 0);
                myChar.setFacingDirection(2);
                actMonsterTurn();
            } else {
                myChar.setFacingDirection(2);
            }
        }
        //Down arrow pressed move 1 tile down
        if (k == KeyEvent.VK_DOWN) {

            if ( myChar.getCurrDirection() == 1) {
                setPlayerPosition(0, 16);
                myChar.setFacingDirection(1);
            } else {
                myChar.setFacingDirection(1);
            }
        }
        //Up arrow pressed move 1 tile up
        if (k == KeyEvent.VK_UP) {

            if (myChar.getCurrDirection() == 0) {
                setPlayerPosition(0, -16);
                myChar.setFacingDirection(0);
            } else {
                myChar.setFacingDirection(0);
            }
        }
        if (k == KeyEvent.VK_R) {

            gsm.startFightState(myChar, monsters[0]);

        }
        if (k == KeyEvent.VK_Z) {

            myChar.attack(myChar.getCurrDirection(), monsters);
        }
    }

    private void actMonsterTurn() {
        //The monster's turn
        for(Monster monster: monsters) {
            monster.pathFind(myChar.getX(), myChar.getY());
        }
    }

    private void setPlayerPosition(int xMove, int yMove) {

        if(tileMap.isBlockedTile(myChar.getX() + xMove, myChar.getY() + yMove)) {

            return;
        } else {
            myChar.setPosition(xMove, yMove);
        }
    }

    @Override
    public void keyReleased(int k) {


    }
}
