package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ShipDeath extends Entity {
	
	GamePanel gp;
	int life, x, y;
	BufferedImage part0, part1, part2;
	ShipPart a, b, c;
	
	public ShipDeath(int x, int y, double h, GamePanel gp) {
		this.gp = gp;
		this.x = x;
		this.y = y;
		life = 180;
		try {
			part0 = ImageIO.read(getClass().getResourceAsStream("/ship/shipePart0.png"));
			part1 = ImageIO.read(getClass().getResourceAsStream("/ship/shipePart1.png"));
			part2 = ImageIO.read(getClass().getResourceAsStream("/ship/shipePart2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		a = new ShipPart(this, part0, 0, x, y, h);
		b = new ShipPart(this, part1, 1, x, y, h);
		c = new ShipPart(this, part2, 2, x, y, h);
	}
	
	public void draw(Graphics2D g2) {
		a.draw(g2);		
		b.draw(g2);		
		c.draw(g2);		
	}
	
	public void update() {
		life--; 
		a.update();
		b.update();
		c.update();
		if(life < 0) { 
			if(gp.extraShips > 0) {
				if(gp.cc.isSpawnSafe()) {
					gp.newLife();
				}
			} else {
				//end the game
			}
		}
	}
	
}
