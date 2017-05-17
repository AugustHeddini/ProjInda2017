package gamestate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import characters.Player;
import game.GamePanel;
import tilemap.TileMap;

public class LevelState extends GameState {
	
	//The tilemap of the level
	private TileMap tileMap;
	
	//Needs to be able to hold a character
	private Player myChar;
	
	public LevelState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		init();
		

	}
	@Override
	public void init() {
		
		tileMap = new TileMap(16);
		tileMap.loadTiles("/Tilesets/TileTest.png");
		tileMap.loadMap("/Maps/Tilemaptest1.csv");
		tileMap.setPosition(100, 100);
		myChar = new Player(0, 0);
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		//clear screen
		g.setColor(Color.WHITE);
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
			myChar.setPosition(16, 0);
			//test if the character has gone of left side of screen
			if(myChar.outOfBounds()) {
				myChar.setPosition(-16, 0);
			}
			//test if collsion with blocked tile
//			if(wasCollision(myChar.getX(), myChar.getY())) {
//				myChar.setPosition(-16, 0);
//			}
		}
		//Left arrow pressed move 1 to the left
		if(k == KeyEvent.VK_LEFT) {
			myChar.setPosition(-16, 0);
			//test if character has gone off the right side of screen
			if(myChar.outOfBounds()) {
				myChar.setPosition(16, 0);
			}
			//test if collsion with blocked tile
//			if(wasCollision(myChar.getX(), myChar.getY())) {
//				myChar.setPosition(16, 0);
//			}
		}
		//Down arrow pressed move 1 tile down
		if(k == KeyEvent.VK_DOWN) {
			myChar.setPosition(0, 16);
			//test if character has gone off the bottom the screen
			if(myChar.outOfBounds()) {
				myChar.setPosition(0, -16);
			}
			//test if collsion with blocked tile
//			if(wasCollision(myChar.getX(), myChar.getY())) {
//				myChar.setPosition(0, -16);
//			}
		}
		//Up arrow pressed move 1 tile up
		if(k == KeyEvent.VK_UP) {
			myChar.setPosition(0, -16);
			//test if the character has gone through the top of screen
			if(myChar.outOfBounds()) {
				myChar.setPosition(0, 16);
			}
			//test if collsion with blocked tile
//			if(wasCollision(myChar.getX(), myChar.getY())) {
//				myChar.setPosition(0, 16);
//			}
		}

	}

	@Override
	public void keyReleased(int k) {
		
		
		
	}
	/**
	 * FInd out if there is a collision with a blocked tile
	 *
	 */
	private boolean wasCollision(int xPos, int yPos) {

		if(tileMap.getTileType(yPos, xPos) == 1) {
			System.out.println("Collision");
			return true;
		}
		return false;

	}

}
