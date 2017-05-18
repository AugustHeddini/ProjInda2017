package gamestate;

import tilemap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @Author August Heddini
 * @Date 2017-05-11.
 */
public class HelpState extends GameState {

    private Background bg;

    private String[] options = {
            "Back"
    };
    private int currentChoice = 0;

    private Color titleColor;
    private Font titleFont;
    private Font font;

    public HelpState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            bg = new Background("/Backgrounds/garden.png", 1);
            bg.setVector(-0.1,  0);

            titleColor = Color.BLACK;
            titleFont = new Font("Century Gothic", Font.BOLD, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public void init(){

    }
    public void draw(java.awt.Graphics2D g){

        // Render the background
        bg.draw(g);


        //draw title
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Options", 30, 50);

        //draw menu options
        g.setFont(font);
        g.drawString("Git gud scrub nub!", 30, 70);

        for(int i = 0; i < options.length; i++) {

            if(i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }

            g.drawString(options[i], 145, 140 + i*15);
        }

    }
    public  void update(){
        bg.update();

    }
    private void select() {
        // Framework is here for an options menu
        if(currentChoice == 0) {
            //picked back
            gsm.setState(gsm.MENUSTATE);

        }
    }

    @Override
    public void keyPressed(int k) {
        //if user presses enter
        if(k == KeyEvent.VK_ENTER) {
            select();
        }

        if(k == KeyEvent.VK_UP) {
            //we go up the optionslist
            currentChoice --;
            //if it goes out of bounds
            if(currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if(k == KeyEvent.VK_DOWN) {
            //we go down the options
            currentChoice ++;
            if(currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }
    public void keyReleased(int k){

    }

}
