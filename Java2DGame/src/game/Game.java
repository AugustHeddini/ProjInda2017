package game;


import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Spooky Dungeon");
		//Set the JPanel inside the frame
		window.setContentPane(new GamePanel());
		//set exit when close is hit
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Cant resize the frame
		window.setResizable(true);
		//Start the window in the center
		window.setLocationRelativeTo(null);
		//Resize window to fit everything without unneccessary space
		window.pack();
		//Make it appear
		window.setVisible(true);
		
	}

}
