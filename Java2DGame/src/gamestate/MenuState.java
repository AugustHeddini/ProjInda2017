package gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game.GamePanel;
import tilemap.Background;

import javax.swing.*;

public class MenuState extends GameState {
	
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
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		try {
			bg = new Background("/Backgrounds/garden.png", 1);
			bg.setVector(-0.1,   0);
			
			titleColor = Color.BLACK;
			titleFont = new Font("Century Gothic", Font.BOLD, 28);
			font = new Font("Arial", Font.PLAIN, 12);
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
		
		//draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Wizard's Garden", 30, 50);
		
		//draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			
			if(i == currentChoice) {
				g.setColor(Color.RED);
			} else {
				g.setColor(Color.BLACK);
			}
			
			g.drawString(options[i], 145, 140 + i*15);
		}

		//JTextField text = new JTextField("Enter name here");
		//g.draw
	}

	@Override
	public void update() {


		bg.update();
		
	}
	private void select() {
		
		if(currentChoice == 0) {
			//picked start
			gsm.setState(gsm.LEVEL1STATE);

		}
		if(currentChoice == 1) {
			//picked help
			gsm.setState(gsm.HELPSTATE);
		}
		if(currentChoice == 2) {
			//picked quit
			System.exit(0);
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

	@Override
	public void keyReleased(int k) {
		
		
	}
}
