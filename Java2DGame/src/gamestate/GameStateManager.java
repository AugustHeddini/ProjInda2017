package gamestate;

import mapobjects.Monster;
import mapobjects.Player;


import java.util.ArrayList;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int HELPSTATE = 1;
    public static final int LEVEL1STATE = 2;
    public static final int LEVEL2STATE = 3;
    public static final int LEVEL3STATE = 4;


    private int currentLevel;

    public GameStateManager() {

        gameStates = new ArrayList<GameState>();
        currentLevel = 1;
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new HelpState(this));
        gameStates.add(new Level1State(this));


    }

    public void setState(int state) {

        currentState = state;


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

        gameStates.add(new FightState(this, mychar, theMonster));
        setState(currentState + 1);
    }

    public void endFightState() {
        currentState -= 1;
        gameStates.remove(currentState + 1);

    }

    public void addNextLevel(Player player) {

        if (currentLevel == 1) {

            player.setNewPosition(320 - Player.WIDTH * 2, 0);
            System.out.println(player.getX() + " " + player.getY());
            gameStates.add(new Level2State(this, player));
            currentLevel++;
            return;
        }
        if (currentLevel == 2) {

            player.setNewPosition(0, 0);
            System.out.println(player.getX() + " " + player.getY());
            gameStates.add(new Level3State(this, player));
            currentLevel++;
            return;
        }
    }

    public void endState(Player myChar) {

        gameStates.add(new EndState(this, myChar));
        setState(currentState + 1);
    }
}
