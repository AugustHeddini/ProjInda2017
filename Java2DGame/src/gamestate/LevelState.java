package gamestate;

import java.awt.Graphics2D;

import characters.Player;
import tilemap.Background;

public class LevelState extends GameState {
	
	//The background of the level
	private Background bg;
	
	//Needs to be able to hold a character
	private Player myChar;
	
	public LevelState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/map.png", 1);
			bg.setVector(0,  0);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void init() {
		
		
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		//draw background
		bg.draw(g);
	
	}

	@Override
	public void update() {


		bg.update();
		
	}

	@Override
	public void keyPressed(int k) {
			
	}

	@Override
	public void keyReleased(int k) {
		
		
	}

}
