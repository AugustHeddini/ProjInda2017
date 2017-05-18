package gamestate;

import mapobjects.Player;
import tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by johan on 2017-05-18.
 */
public class EndState extends GameState {


    private Background bg;

    private String[] options = {
            "Start",
            "Help",
            "Quit"
    };
    private int currentChoice = 0;

    private Color titleColor;
    private Font titleFont;
    private Font font;

    private Player myChar;
    private int score;
    private boolean win;


    public EndState(GameStateManager gsm, Player myChar) {

        this.gsm = gsm;

        this.myChar = myChar;

        try {
            bg = new Background("/Backgrounds/garden.png", 1);
            bg.setVector(-0.1, 0);

            titleColor = Color.BLACK;
            titleFont = new Font("Century Gothic", Font.BOLD, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
        init();
    }


    @Override
    public void init() {

        score = myChar.getScore();
        if(myChar.getHealth() > 0) {
            win = true;
        } else {
            win = false;
        }

    }

    @Override
    public void draw(Graphics2D g) {

        //draw background
        bg.draw(g);

        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        if(win) {
            g.drawString("YOU WON!!", 100, 50);
        } else {
            g.drawString("YOU LOST!!", 100, 50);
        }

        //draw menu options
        g.setFont(font);
        g.setColor(Color.RED);
        g.drawString("Your final score was: " + score, 120, 100);
        g.drawString("Press enter to quit", 120, 140);
    }

    @Override
    public void update() {


        bg.update();

    }


    @Override
    public void keyPressed(int k) {

        if(k == KeyEvent.VK_ENTER) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(int k) {


    }
}
