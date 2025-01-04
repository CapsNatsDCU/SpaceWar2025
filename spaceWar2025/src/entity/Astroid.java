package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class Astroid extends Entity{
	
	BufferedImage image = null;
	GamePanel gp;
	int stage;
	int index;
	int s;
	Random rand = new Random();
	
	public Astroid(GamePanel gp, int x, int y, int stage, int index) {
		int v = 0;
		this.gp = gp;
		this.index = index;
		worldX = x;
		worldY = y;
		this.stage = stage;
		heading = (double) rand.nextInt(360);
		solidArea = new Rectangle(worldX, worldY, 5, 5);
		if (stage == 0) {
			v = 4;
			s = 12;
		} else if (stage == 1) {
			v = rand.nextInt(2) + 4;
			s = rand.nextInt(4) + 18;
		} else if (stage == 2) {
			v = rand.nextInt(4) + 4;
			s = rand.nextInt(8) + 22;
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
		
		// define hitbox
		solidArea = new Rectangle(worldX, worldY, gp.screenWidth / s , gp.screenWidth / s);
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.white);
			if(gp.debug){
				if (stage == 1) {
				g2.setColor(Color.red);
			} else if (stage == 2) {
				g2.setColor(Color.blue);
			}
		}
		g2.drawRect(worldX, worldY, gp.screenWidth / s , gp.screenWidth / s);
	}
	
	public void stageChange() {
		//do something
		if(stage == 0) {
			for(int i = 0; i < 1 + rand.nextInt(3); i++) {
				newAss(1);
				gp.score.add(5);
			}
		}
		if (stage == 1) {
			for(int i = 0; i < 2 + rand.nextInt(3); i++) {
				newAss(2);
				gp.score.add(10);
			}
		}
		if (stage == 2) {
			gp.score.add(15);
		}
		gp.a[index] = null;
	}
	
	private void newAss(int stage) {
		int index = 0;
		for (int i = 0; i < gp.a.length; i++) {
			if(gp.a[i] == null) {
				index = i;
				break;
			}
		}
		gp.a[index] = new Astroid(gp, worldX, worldY, stage, index);
		
	}
}

