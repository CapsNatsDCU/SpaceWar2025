package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Torpedo extends Entity{
	
	BufferedImage image = null;
	GamePanel gp;
	public int life;
	
	
	public Torpedo(int x, int y, double startingHeading, GamePanel gp) {
		System.out.println("new Torpedo");
		this.gp = gp;
		this.worldX = x; 
		worldY = y;
		heading = startingHeading;	
		velo = 14.0;
		life = 80;
	    solidAreaDefaultX = 24;
	    solidAreaDefaultY = 23;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Torpedo/torpedo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		if(life > 0) {
			g2.drawImage(image, worldX, worldY, 50, 50, null);
		}
	    if (solidArea != null) {
	    	g2.setColor(Color.red);
	    	g2.fill(solidArea); // Fills the rectangle
	    	g2.draw(solidArea);
	    } else {
	        System.out.println("Solid area is null!");
	    }
	}
	
	public void update() {
		life--;
		// Convert the heading to radians
		double radians = Math.toRadians(heading);
		// Calculate movement components for x and y based on velocity and heading
		double moveX = Math.cos(radians) * velo; // Horizontal movement (right or left)
		double moveY = Math.sin(radians) * velo; // Vertical movement (up or down)
		
		worldX += moveX;
		worldY += moveY;
		
		//location control
		if (worldX < 0) { worldX = gp.screenWidth; }
		if (worldX > gp.screenWidth) { worldX = 0; }
		if (worldY < 0) { worldY = gp.screenHeight; }
		if (worldY > gp.screenHeight) { worldY = 0; }
		
		solidArea = new Rectangle(worldX + solidAreaDefaultX, worldY + solidAreaDefaultY, 3, 3);
		
	}

}
