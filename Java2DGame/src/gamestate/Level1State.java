package gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import mapobjects.Monster;
import mapobjects.Player;
import game.GamePanel;
import tilemap.TileMap;

public class Level1State extends GameState {
	
	//The tilemap of the level
	private TileMap tileMap;


	
	//Needs to be able to hold a character
	private Player myChar;

	private Monster[] monsters;
	
	public Level1State(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		init();

	}
	@Override
	public void init() {
		
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/TileSetNew.png");
		tileMap.loadMap("/Maps/MapUno.csv");
		tileMap.setPosition(0, 0);

		myChar = new Player(32, 32);
		
	}

	@Override
	public void draw(Graphics2D g) {

		//clear screen
		g.setColor(new Color(0xaae9af));
		g.fillRect(0,0,GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw tilemap
		tileMap.draw(g);

		//draw player
		myChar.draw(g);
	
	}

	@Override
	public void update() {


		
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
	private boolean playerEnteringNextLevel(int yMove) {

		return (myChar.getY() + yMove) >= 240;
	}
	private void setPlayerPosition(int xMove, int yMove) {

        if(playerEnteringNextLevel(yMove)) {
            //when you go to the next screen the new state will first be created with my char
            //and then the state is set
            gsm.addNextLevel(myChar);
            gsm.setState(gsm.LEVEL2STATE);
            return;
        }

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
