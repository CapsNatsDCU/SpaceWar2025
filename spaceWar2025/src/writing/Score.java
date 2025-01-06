package writing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Score {

	private int score;
	GamePanel gp;
	BufferedImage ship;

    public Score(GamePanel gp) {
    	this.gp = gp;
        score = 0;
        try {
			ship = ImageIO.read(getClass().getResourceAsStream("/ship/miniShip.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void add(int points) {
        score += points;
    }

    public void resetScore() {
        score = 0;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(new Font("Monospaced", Font.BOLD, 48));
        g2.setColor(Color.WHITE);
        g2.drawString(String.valueOf(score), gp.screenHeight / 30, gp.screenWidth / 30);
        for(int i = gp.extraShips; i > 0; i--) {
        	g2.drawImage(ship, (29 * (gp.screenWidth / 30)) - (i * 55), gp.screenWidth / 30, null);
        }
    }

    public int getScore() {
        return score;
    }
	
}
