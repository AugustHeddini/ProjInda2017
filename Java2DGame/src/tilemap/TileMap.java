package tilemap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

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
	
	//might not implement
	private double tween;
	
	//Map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numColons;
	private int width;
	private int height;
	
	//Tileset
	private BufferedImage tileSet;
	private int numTilesAcross;
	private Tile[][] tiles;
	
	//drawing bounds
	private int rowOffSet;
	private int colonOffSet;
	private int numRowsToDraw;
	private int numColonsToDraw;
	
	public TileMap(int tileSize) {
		
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT/tileSize + 2;
		numColonsToDraw = GamePanel.WIDTH/tileSize + 2;
		tween = 0.07;
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
				
				//first row
				subImage = tileSet.getSubimage(col * tileSize,0 , tileSize, tileSize);
				tiles[0][col] = new Tile(subImage, Tile.NORMAL);
				
				//second row
				subImage = tileSet.getSubimage(col * tileSize,tileSize , tileSize, tileSize);
				tiles[1][col] = new Tile(subImage, Tile.BLOCKED);
				
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
			
			numColons = 40;
			numRows = 30;
			map = new int[numRows][numColons];
			width = numColons * tileSize;
			height = numRows * tileSize;
			
			//limitors for whitespace
			String delims = ",";
			for(int row = 0; row < numRows; row++) {
				//get our line 
				String line = br.readLine();
				//split by whitespace
				String[] tokens = line.split(delims);
				for(int cols = 0; cols < numColons; cols++) {
					map[row][cols] = Integer.parseInt(tokens[cols]);
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
	public int getTileType(int row, int col) {
		
		//get the tile value
		int rc = map[row][col];
		//find out which tile it is
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		return tiles[r][c].getType();
	}
	
	public void setPosition(double x, double y) {
		
		this.x += (x - this.x)*tween;
		this.y += (y - this.y)*tween;
		
		fixBounds();
		
		colonOffSet = (int) -this.x / tileSize;
		rowOffSet = (int) -this.y / tileSize;
	}
	private void fixBounds() {
		
		if(x < xMin) x = xMin;
		if(y < yMin) y = yMin;
		if(x > xMax) x = xMax;
		if(y > yMax) y = yMax;
	}
	
	public void draw(Graphics2D g) {
		
		for(int row = rowOffSet; row < rowOffSet + numRowsToDraw; row ++) {
			
			if(row >= numRows) break;
			
			for(int col = colonOffSet; col < colonOffSet + numColonsToDraw; col++) {
				
				if(col >= numColons) break;
				
				//if the image with tiles is empty at that position dont bother drawing it
				if(map[row][col] == 0) continue;
				
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
