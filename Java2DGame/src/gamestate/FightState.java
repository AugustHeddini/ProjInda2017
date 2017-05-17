package gamestate;

import mapobjects.Monster;
import mapobjects.Player;
import tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by johan on 2017-05-16.
 * A fight state with a pokemon fight style vibe
 */
public class FightState extends GameState {


    private Player myChar;
    private Monster theMonster;

    private Background background;

    private Font font;
    private Color fontColor;

    private Font barFont;
    private Color barColor;

    private int playerHP;
    private int monsterHP;

    private String help = "Press enter to attack";

    public FightState(GameStateManager gsm, Player myChar, Monster theMonster) {

        this.gsm = gsm;
        this.myChar = myChar;
        this.theMonster = theMonster;
        playerHP = myChar.getHealth();
        monsterHP = theMonster.getHealth();
        init();
    }

    public void init() {

        try {
            background = new Background("/Backgrounds/garden.png", 1);
            background.setVector(0, -0.1);


        } catch (Exception e) {
            e.printStackTrace();
        }

        font = new Font("Arial", Font.PLAIN, 12);
        fontColor = Color.RED;

        barFont = new Font("Arial", Font.PLAIN, 10);
        barColor = Color.BLACK;
    }

    public void draw(java.awt.Graphics2D g) {

        //draw background
        background.draw(g);

        g.setColor(fontColor);
        g.setFont(font);

        g.drawString("FIGHT!!!", 160, 10);
        g.drawString(help, 160, 220);
        g.setFont(barFont);
        g.drawString("HP:" + playerHP + "/100", 60, 20);
        g.drawString("MonsterHp:" + monsterHP + "/100", 240, 20);

        myChar.draw(g);

        theMonster.draw(g);

    }

    public void update() {
        playerHP = myChar.getHealth();
        monsterHP = theMonster.getHealth();
    }

    public void keyPressed(int k) {

        if (k == KeyEvent.VK_ENTER) {
            int dmg = myChar.attack();
            theMonster.damaged(dmg);
            dmg = theMonster.attack();
            myChar.damaged(dmg);
        }
    }

    public void keyReleased(int k) {

    }

    private void endFightState() {
        gsm.endFightState();
    }
}
