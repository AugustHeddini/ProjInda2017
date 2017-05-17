package gamestate;

import characters.Bandit;
import characters.Monster;
import characters.Player;
import game.GamePanel;
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
                {new Bandit(16, 16, "/Tilesets/soldier_tilesheet.png")};

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
            tileMap.loadTileTypes();
            myChar.setPosition(304, 0);


        }

        public void getPlayer(Player myChar) {
            this.myChar = myChar;
        }

        @Override
        public void draw(Graphics2D g) {



            //clear screen
            g.setColor(Color.GREEN);
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
                myChar.setPosition(16, 0);
                //test if the character has gone of left side of screen
                if (myChar.outOfBounds()) {
                    myChar.setPosition(-16, 0);
                }

            }
            //Left arrow pressed move 1 to the left
            if (k == KeyEvent.VK_LEFT) {
                myChar.setPosition(-16, 0);
                //test if character has gone off the right side of screen
                if (myChar.outOfBounds()) {
                    myChar.setPosition(16, 0);
                }

            }
            //Down arrow pressed move 1 tile down
            if (k == KeyEvent.VK_DOWN) {
                myChar.setPosition(0, 16);
                //test if character has gone off the bottom the screen
                if (myChar.outOfBounds()) {
                    myChar.setPosition(0, -16);
                }


            }
            //Up arrow pressed move 1 tile up
            if (k == KeyEvent.VK_UP) {
                myChar.setPosition(0, -16);
                //test if the character has gone through the top of screen
                if (myChar.outOfBounds()) {
                    myChar.setPosition(0, 16);
                }



            }
            if (k == KeyEvent.VK_R) {

                gsm.startFightState(myChar, monsters[0]);

            }
        }

        @Override
        public void keyReleased(int k) {


        }

}



