package tilemap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

import javax.imageio.*;



import game.GamePanel;

public class TileMap {
	
	//position
	private double x;
	private double y;
	
	//boundary
	private int xMin;
	private int yMin;
	private int xMax;
	private int yMax;

	//Map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numColumns;
	private int width;
	private int height;
	
	//Tileset
	private BufferedImage tileSet;
	private int numTilesAcross;
	private Tile[][] tiles;
	//Integer one is position in array, and Integer two is a value of 0 or 1 based on the tile type
	private HashMap<Integer, Integer> blocked;
	
	//drawing bounds
	private int rowOffSet;
	private int colonOffSet;
	private int numRowsToDraw;
	private int numColonsToDraw;
	
	public TileMap(int tileSize) {
		
		this.tileSize = tileSize;
		numRowsToDraw = 240/tileSize ;
		numColonsToDraw = 320/tileSize ;
		blocked = new HashMap<>();
	}
	
	/**
	 * Loads the tiles into memory
	 * @param s
	 */
	public void loadTiles(String s) {
		
		tileSet = null;
		try {
			
			//get the tilemap(image)
			tileSet = ImageIO.read(getClass().getResourceAsStream(s));
			numTilesAcross = tileSet.getWidth()/tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			//import the tileset
			BufferedImage subImage;
			for(int col = 0; col < numTilesAcross; col++ ) {

				for(int j = 0; j < 2; j++) {
					subImage = tileSet.getSubimage(col * tileSize,j*tileSize , tileSize, tileSize);
					tiles[j][col] = new Tile(subImage, j);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Loads the map file into memory
	 */
	public void loadMap(String s) {

			try {

				InputStream in = getClass().getResourceAsStream(s);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));

				numColumns = 20;
				numRows = 15;
				map = new int[numRows][numColumns];
				width = numColumns * tileSize;
				height = numRows * tileSize;

				xMin = GamePanel.WIDTH - width;
				xMax = 0;
				yMin = GamePanel.HEIGHT - height;
				yMax = 0;

				//limitors for whitespace
				String delims = ",";
				for (int row = 0; row < numRows; row++) {
					//get our line
					String line = br.readLine();
					//split by whitespace
					String[] tokens = line.split(delims);
					for (int cols = 0; cols < numColumns; cols++) {
						map[row][cols] += Integer.parseInt(tokens[cols]);

                        if(map[row][cols] != 0 && map[row][cols] != 1 && map[row][cols] != 17) {

                            blocked.put(map[row][cols], Tile.BLOCKED);

                        } else {

                            blocked.put(map[row][cols], Tile.NORMAL);

                        }
                    }
                }

			} catch (Exception e) {

				e.printStackTrace();
			}
	}
	
	public int getTileSize() {
		return tileSize;
	}
	public int getx() {
		return (int) x;
	}
	public int gety() {
		return (int) y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getNumXTiles() {
        return numColumns;
    }
    public int getNumYTiles() {
        return numRows;
    }

	public boolean isBlockedTile(int xpos, int ypos) {

		/**
		 * DET VERKAR FINNAS EN BUG HÄR NÄR PLAYER POSITION ÄR 0, 0 - GER ArrayIndexOutOfBoundsException: -1
		 * Kollisionen verkar dock fortf fungera. ??
		 */
		if( (blocked.get(map[ypos/tileSize][xpos/tileSize])) == Tile.BLOCKED) {
			return true;
		}
		return false;
	}
	
	public void setPosition(double x, double y) {
		
		this.x += (x - this.x);
		this.y += (y - this.y);
		
		fixBounds();
		
		colonOffSet = (int) -this.x / tileSize;
		rowOffSet = (int) -this.y / tileSize;
	}
	private void fixBounds() {
		
		if(x > xMin) x = xMin;
		if(y > yMin) y = yMin;
		if(x < xMax) x = xMax;
		if(y < yMax) y = yMax;
	}
	
	public void draw(Graphics2D g) {

		for(int row = rowOffSet; row < rowOffSet + numRowsToDraw; row ++) {
			
			if(row >= numRows) break;
			
			for(int col = colonOffSet; col < colonOffSet + numColonsToDraw; col++) {
				
				if(col >= numColumns) break;
				
				//if the image with tiles is empty at that position dont bother drawing it
				//if(map[row][col] == 0) continue;
				
				//get the tile value
				int rc = map[row][col];
				//find out which tile it is
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int) x +col*tileSize, (int) y + row*tileSize, null);

			}
		}
	}
}
