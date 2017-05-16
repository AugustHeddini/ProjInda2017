package gamestate;

import characters.Monster;
import characters.Player;
import game.Game;

import java.util.ArrayList;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int LEVEL2STATE = 2;
	public static final int FIGHTSTATE = 3;


	public int currentLevel = 1;
	
	public GameStateManager() {
		
		gameStates = new ArrayList<GameState>();
		
		currentState = MENUSTATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new Level1State(this));


		
	}

	public void setState(int state) {
		
		currentState = state;
		//gameStates.get(currentState).init();
		
	}
	public GameState getState(int state) {
		return gameStates.get(state);
	}
	public void update() {
		gameStates.get(currentState).update();
	}
	public void draw(java.awt.Graphics2D g) {
		gameStates.get(currentState).draw(g);
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}

	public void startFightState(Player mychar, Monster theMonster) {

		gameStates.add( new FightState(this, mychar, theMonster));
		setState(currentState + 1);
	}

	public void addNextLevel( Player player) {

		if(currentLevel == 1) {
			gameStates.add(new Level2State(this, player));
		}
	}
}
