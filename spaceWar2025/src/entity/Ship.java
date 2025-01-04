package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.*;

public class Ship extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	Random rand = new Random();
	int hyperDriveCooldown;
	int gunCoolDown;
	
	public BufferedImage noThrust, slow, medium, fast;
	
	public Ship(GamePanel gp, KeyHandler keyH) {
	    this.gp = gp;
	    this.keyH = keyH;
	    worldX = 0; //gp.screenWidth / 2;
	    worldY = 0; //gp.screenHeight / 2;
	    heading = 0.0;
	    velo = 1.0;
	    hyperDriveCooldown = 0;
	    gunCoolDown = 0;
	    solidAreaDefaultX = 14;
	    solidAreaDefaultY = 10;
	    getShipImage();
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		//pick the appropriate image
		if (velo <= 2 || !keyH.thrustPressed) {
		    image = noThrust;
		} else if (velo > 2 && velo <= 4 && keyH.thrustPressed) {
		    image = slow;
		} else if (velo > 4 && velo <= 6 && keyH.thrustPressed) {
		    image = medium;
		} else {
		    image = fast;
		}
		
	    double angle = Math.toRadians(heading);  // Convert angle to radians

	    // Save the original transformation state
	    // Create a new AffineTransform for the rotation
	    AffineTransform transform = new AffineTransform();

	    // Move the origin to the center of the image, rotate, then move it back
	    transform.translate(worldX + 25, worldY + 25);  // Move to center of the image
	    transform.rotate(angle);  // Rotate by the angle
	    transform.translate(-25, -25);  // Move it back to the top-left corner
		
	    g2.drawImage(image, transform, null);
	    
	    if(gp.debug){
	    	if (solidArea != null) {
		    	g2.setColor(Color.red);
		    	g2.fill(solidArea); // Fills the rectangle
		    	g2.draw(solidArea);
		    } else {
		        System.out.println("Solid area is null!");
		    }
	    }
	}
	
	public void getShipImage() {
		try {
			noThrust = ImageIO.read(getClass().getResourceAsStream("/ship/ship0.png"));
			slow = ImageIO.read(getClass().getResourceAsStream("/ship/ship1.png"));
			medium = ImageIO.read(getClass().getResourceAsStream("/ship/ship2.png"));
			fast = ImageIO.read(getClass().getResourceAsStream("/ship/ship3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		// Accelerate when thrust is pressed
		if (keyH.thrustPressed) { 
			if (velo <= 2) {
				velo = 2.0;
			}
			if (velo < 8) {
				velo += 0.02;
			}
		} else if (velo > 2) {
			velo -= 0.02;
		}
		// Convert the heading to radians
		double radians = Math.toRadians(heading);
		// Calculate movement components for x and y based on velocity and heading
		double moveX = Math.cos(radians) * velo; // Horizontal movement (right or left)
		double moveY = Math.sin(radians) * velo; // Vertical movement (up or down)
		// Update position based on velocity
		if(velo > 2) {
			worldX += moveX;
			worldY += moveY;
		}
		// Invert Y to move upward on screen (y-coordinates increase downward)
		//rotation
		if(keyH.leftPressed) {
			heading -= 2;
		}
		if(keyH.rightPressed) {
			heading += 2;
		}
		//location control
		if (worldX < 0) { worldX = gp.screenWidth; }
		if (worldX > gp.screenWidth) { worldX = 0; }
		if (worldY < 0) { worldY = gp.screenHeight; }
		if (worldY > gp.screenHeight) { worldY = 0; }
		//hyper drive
		if(keyH.hyperPressed == true) {
			if(hyperDriveCooldown > 60) {
				worldX = rand.nextInt(gp.screenWidth);
				worldY = rand.nextInt(gp.screenHeight);
				hyperDriveCooldown = 0;
			}
		}
		hyperDriveCooldown++;
		
		// torpedo maker
		if(keyH.firePressed) { 
			if(gunCoolDown > 25) {
				int index = -1;
				for(int i = 0; i < gp.t.length; i++) {
					if(gp.t[i] == null) {
						index = i;
						break;
					}
				}
				if(index > -1) {
					Torpedo torri = new Torpedo(worldX, worldY, heading, gp);
					gp.t[index] = torri;
					gunCoolDown = 0;
				}
			}
		}
		gunCoolDown++;
		// set hitbox
	    solidArea = new Rectangle(worldX + solidAreaDefaultX, worldY + solidAreaDefaultY, 26, 26);
	    //Collioion
	    if(gp.cc.shipCollision(this)) {
	    	gp.score.add(-10);
	    }
		}
}
