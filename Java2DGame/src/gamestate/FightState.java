package gamestate;

import characters.Monster;
import characters.Player;
import tilemap.Background;

import java.awt.*;

/**
 * Created by johan on 2017-05-16.
 * A fight state with a pokemon fight style vibe
 */
public class FightState extends GameState{


    private Player myChar;
    private Monster theMonster;

    private Background background;

    private Font font;
    private Color fontColor;

    public FightState(GameStateManager gsm, Player myChar, Monster theMonster) {

        this.gsm = gsm;
        this.myChar = myChar;
        this.theMonster = theMonster;
        init();
    }

    public void init() {

        try {
            background = new Background("/Backgrounds/map.png", 1);
            background.setVector(0,  - 0.1);


        } catch(Exception e) {
            e.printStackTrace();
        }

        font = new Font("Arial", Font.PLAIN, 12);
        fontColor = Color.GREEN;
    }

    public void draw(java.awt.Graphics2D g) {

        //draw background
        background.draw(g);

        g.setColor(fontColor);
        g.setFont(font);

        g.drawString("Hello World", 100, 100 );

    }

    public void update() {

    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {

    }
}
