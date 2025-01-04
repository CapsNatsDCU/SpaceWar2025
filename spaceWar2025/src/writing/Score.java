package writing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Score {

	private int score;
	GamePanel gp;

    public Score(GamePanel gp) {
    	this.gp = gp;
        score = 0;
    }

    public void add(int points) {
        score += points;
    }

    public void resetScore() {
        score = 0;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(new Font("Arial", Font.BOLD, 48));
        g2.setColor(Color.WHITE);
        g2.drawString(String.valueOf(score), gp.screenHeight / 30, gp.screenWidth / 30);
    }

    public int getScore() {
        return score;
    }
	
}
