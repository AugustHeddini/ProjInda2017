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
public class Level3State extends GameState{

    //The tilemap of the level
    private TileMap tileMap;



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
            monster.draw(g);
        }



    }

    @Override
    public void update() {




    }

    @Override
    public void keyPressed(int k) {

        //Right arrow pressed move 1 tile to the right
        if (k == KeyEvent.VK_RIGHT) {

            setPlayerPosition(16, 0);

        }
        //Left arrow pressed move 1 to the left
        if (k == KeyEvent.VK_LEFT) {


            setPlayerPosition(-16, 0);

        }
        //Down arrow pressed move 1 tile down
        if (k == KeyEvent.VK_DOWN) {


            setPlayerPosition(0, 16);


        }
        //Up arrow pressed move 1 tile up
        if (k == KeyEvent.VK_UP) {

            setPlayerPosition(0, -16);

        }
        if (k == KeyEvent.VK_R) {

            gsm.startFightState(myChar, monsters[0]);

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
