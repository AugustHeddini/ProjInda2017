package gamestate;

import mapobjects.Bandit;
import mapobjects.Monster;
import mapobjects.Player;
import mapobjects.Wizard;
import game.GamePanel;
import tilemap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by johan on 2017-05-17.
 */
public class Level3State extends GameState {

    //The tilemap of the level
    private TileMap tileMap;


    //Needs to be able to hold a character
    private Player myChar;

    private Monster[] monsters =
                    {new Bandit(16, 16, "/Tilesets/bandit.png"),
                            new Bandit(16*5, 16*10,"/Tilesets/bandit.png" ),
                    new Wizard(16*16, 16*3, "/Tilesets/characters.png")};

    private int battleMonster;

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

        isWizardDead();
        removeDeadMonster();

    }

    private void isWizardDead() {

        if(monsters[2].getHealth() <= 0) {
            gsm.endState(myChar);
        }
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
    }

    private void setPlayerPosition(int xMove, int yMove) {

        if(myChar.getX() + xMove <= 0 || myChar.getY() + yMove <= 0) {
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

            if(monsters[i] != null) {
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
