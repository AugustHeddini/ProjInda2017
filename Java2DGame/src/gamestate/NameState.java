package gamestate;

import tilemap.Background;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author August Heddini
 * @version 12/05/2017
 */
public class NameState extends GameState {

    private Background bg;
    private Color titleColor;
    private Font titleFont;
    private Font font;
    private JPanel mainPanel;
    private JPanel myPanel;
    private JTextField nameField;

    public NameState(GameStateManager gsm, JPanel panel) {

        this.gsm = gsm;
        this.mainPanel = panel;

        nameField = new JTextField("Name: ", 15);
        myPanel = new JPanel();
        myPanel.setLocation(30,70);
        myPanel.setLayout(new OverlayLayout(myPanel));
        myPanel.add(nameField, BorderLayout.CENTER);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(myPanel);

        nameField.setOpaque(false);
        nameField.setLocation(30, 70);

        // Begränsar antalet bokstäver, samt tar bort spaces.
        nameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) { //Removes spaces
                    e.consume();
                }
                if (nameField.getText().length() >= 15 ) // limits the textfield to 15 characters
                    e.consume();
            }
        });


        try {
            bg = new Background("/Backgrounds/grassbg1.gif", 1);
            bg.setVector(-0.1,  0);

            titleColor = Color.BLACK;
            titleFont = new Font("Century Gothic", Font.BOLD, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void init() {

    }
    public void draw(java.awt.Graphics2D g){

        bg.draw(g);

        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Choose your name", 30, 50);


        myPanel.setOpaque(true);
        myPanel.setVisible(true);




    }
    public void update() {
        bg.update();

    }
    public void keyPressed(int k) {

    }
    public void keyReleased(int k) {

    }

}
