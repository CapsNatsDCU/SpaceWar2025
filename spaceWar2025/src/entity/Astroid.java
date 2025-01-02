package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;

public class Astroid extends Entity{
	
	BufferedImage image = null;
	GamePanel gp;
	int stage;
	Random rand = new Random();
	
	public Astroid(GamePanel gp, int x, int y, int stage) {
		int v = 0;
		this.gp = gp;
		worldX = x;
		worldY = y;
		this.stage = stage;
		heading = (double) rand.nextInt(360);
		if (stage == 0) {
			v = 2;
		} else if (stage == 1) {
			v = rand.nextInt(2) + 2;
		} else if (stage == 2) {
			v = rand.nextInt(4) + 3;
		}
		velo = (double) v;
	}
		
	public void update() {
		// Convert the heading to radians
		double radians = Math.toRadians(heading);
		// Calculate movement components for x and y based on velocity and heading
		double moveX = Math.cos(radians) * velo; // Horizontal movement (right or left)
		double moveY = Math.sin(radians) * velo; // Vertical movement (up or down)
		// Update position based on velocity
		worldX += moveX;
		worldY += moveY;
		
		if (worldX < 0) { worldX = gp.screenWidth; }
		if (worldX > gp.screenWidth) { worldX = 0; }
		if (worldY < 0) { worldY = gp.screenHeight; }
		if (worldY > gp.screenHeight) { worldY = 0; }
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.drawRect(worldX, worldY, gp.screenWidth / 12 , gp.screenWidth / 12);
	}
}

