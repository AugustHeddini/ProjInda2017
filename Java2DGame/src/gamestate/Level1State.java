package gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import characters.Monster;
import characters.Player;
import game.GamePanel;
import tilemap.TileMap;

import javax.swing.*;

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
		//tileMap.loadTileTypes();
		myChar = new Player(32, 32);
		
	}

	@Override
	public void draw(Graphics2D g) {



		//clear screen
		g.setColor(Color.GREEN);
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

		}
		//Left arrow pressed move 1 to the left
		if(k == KeyEvent.VK_LEFT) {

			setPlayerPosition(-16, 0);

		}
		//Down arrow pressed move 1 tile down
		if(k == KeyEvent.VK_DOWN) {

			setPlayerPosition(0, 16);


		}
		//Up arrow pressed move 1 tile up
		if(k == KeyEvent.VK_UP) {


			setPlayerPosition(0, -16);
		}
		if(k == KeyEvent.VK_1) {
			//when you go to the next screen the new state will first be created with my char
			//and then the state is set
			gsm.addNextLevel(myChar);
			gsm.setState(gsm.LEVEL2STATE);
		}


	}
	private void setPlayerPosition(int xMove, int yMove) {


		if(tileMap.isBlockedTile(myChar.getX() + xMove, myChar.getY() + yMove)) {
			System.out.println("Blocked");
			return;
		} else {
			myChar.setPosition(xMove, yMove);
		}
	}

	@Override
	public void keyReleased(int k) {
		
		
		
	}




}
