package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;

import gamestate.GameStateManager;


public class GamePanel extends JPanel implements Runnable, KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//dimensions
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 3;
	
	//Game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000/60;
	
	//image 
	private BufferedImage image;
	private Graphics2D g;
	
	//game state manager
	private GameStateManager gsm;
	
	
	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		
		if(thread == null) {
			//starts the game on a new thread
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	private void init() {
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
	}
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		//game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed/1000000;
			if(wait < 0 ) {
				wait = 5;
			}
			try{
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	private void update() {
		gsm.update();
	}
	private void draw() {
		gsm.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0,0,WIDTH*SCALE, HEIGHT*SCALE, null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {
		
	}
	
	public void keyPressed(KeyEvent arg0) {
		gsm.keyPressed(arg0.getKeyCode());
	}
	
	public void keyReleased(KeyEvent arg0) {
		gsm.keyReleased(arg0.getKeyCode());
	}




	
	
	

}
