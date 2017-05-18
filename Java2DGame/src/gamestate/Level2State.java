package gamestate;

import mapobjects.Bandit;
import mapobjects.Monster;
import mapobjects.Player;
import game.GamePanel;
import tilemap.Pathfinder;
import tilemap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by johan on 2017-05-16.
 */


public class Level2State extends GameState {

    //The tilemap of the level
    private TileMap tileMap;


    //Needs to be able to hold a character
    private Player myChar;

    private Monster[] monsters =
            {new Bandit(16, 16, "/Tilesets/Bandit.png"),
            new Bandit(16, 16*10, "/Tilesets/Bandit.png")};

    private int battleMonster;

    private Pathfinder finder;

    public Level2State(GameStateManager gsm, Player myChar) {

        this.gsm = gsm;
        this.myChar = myChar;
        init();


    }

    @Override
    public void init() {

        tileMap = new TileMap(16);
        tileMap.loadTiles("/Tilesets/TileSetNew.png");
        tileMap.loadMap("/Maps/MapDuo.csv");
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
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        //draw tilemap
        tileMap.draw(g);

        //draw player
        myChar.draw(g);

        for (Monster monster : monsters) {
            if(monster != null) {
                monster.draw(g);
            }
        }


    }

    @Override
    public void update() {

        removeDeadMonster();
    }

    @Override
    public void keyPressed(int k) {

        //Right arrow pressed move 1 tile to the right
        if(k == KeyEvent.VK_RIGHT) {

            setPlayerPosition(16, 0);
            myChar.setFacingDirection(3);

        }
        //Left arrow pressed move 1 to the left
        if(k == KeyEvent.VK_LEFT) {

            setPlayerPosition(-16, 0);
            myChar.setFacingDirection(2);

        }
        //Down arrow pressed move 1 tile down
        if(k == KeyEvent.VK_DOWN) {

            setPlayerPosition(0, 16);
            myChar.setFacingDirection(1);

        }
        //Up arrow pressed move 1 tile up
        if(k == KeyEvent.VK_UP) {

            setPlayerPosition(0, -16);
            myChar.setFacingDirection(0);

        }
        for(Monster monster: monsters) {
            if(monster != null) {
                monster.pathFind(myChar.getX(), myChar.getY());
            }
        }

    }

    private boolean playerEnteringNextLevel(int yMove, int xMove) {

        return ((myChar.getY() + yMove) >= 240) || (myChar.getX() + xMove) >= 320;
    }

    private void setPlayerPosition(int xMove, int yMove) {


        if (playerEnteringNextLevel(yMove, xMove)) {
            //when you go to the next screen the new state will first be created with my char
            //and then the state is set
            gsm.addNextLevel(myChar);
            gsm.setState(gsm.LEVEL3STATE);
            return;

        }

        if (tileMap.isBlockedTile(myChar.getX() + xMove, myChar.getY() + yMove)) {

            return;
        } else {
            myChar.setPosition(xMove, yMove);
        }
        //test if there is monster on the new position
        if(hasEncountered(myChar.getX(), myChar.getY())){
            gsm.startFightState(myChar, monsters[battleMonster]);
        }
    }
    private boolean hasEncountered(int x, int y) {

        for(int i = 0; i < monsters.length; i++) {

            if(monsters[i] != null ) {
                if (monsters[i].getX() == x && monsters[i].getY() == y) {
                    battleMonster = i;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void keyReleased(int k) {


    }

    private void removeDeadMonster() {

        for(int i = 0; i < monsters.length; i++) {
            if(monsters[i] != null) {
                if (monsters[i].getHealth() <= 0) {
                    monsters[i] = null;
                }
            }
        }

    }

}



