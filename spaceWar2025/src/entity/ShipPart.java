package entity;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ShipPart extends Entity{
	
	ShipDeath s;
	BufferedImage i;
	int kind;
	Random rand = new Random();
	double angle;
	
	public ShipPart(ShipDeath s, BufferedImage i, int kind, int x, int y, double h) {
		this.s = s;
		this.i = i;
		this.kind = kind;
		worldX = x;
		worldY = y;
		heading = rand.nextDouble(360.0);
		angle = Math.toRadians(h);
		velo = rand.nextDouble(2.0) + 2;
	}
	
	public void draw(Graphics2D g2) {
		
		AffineTransform transform = new AffineTransform();
		
		transform.translate(worldX + 25, worldY + 25); // Move to center of the image
		transform.rotate(angle); // Rotate by the angle
		transform.translate(-25, -25); // Move it back to the top-left corner
		
		g2.drawImage(i, transform, null);
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
	}

}
