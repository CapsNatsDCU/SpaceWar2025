package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;
import main.KeyHandler;

import entity.*;

//import entity.Player;
//import object.SuperObject;
//import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	public final int shipSize = 50;
	public final int maxScreenColumn = 34;
	public final int maxScreenRow = 20;
	public final int screenWidth = shipSize * maxScreenColumn; //1700
	public final int screenHeight = shipSize * maxScreenRow; //1000
	
	public int FPS = 60;
	
	public boolean debug = false;
	
	KeyHandler keyH = new KeyHandler();  	
	Thread gameThread;
	public Torpedo[] t = new Torpedo[10];
	public Astroid[] a = new Astroid[48];
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public Ship ship = new Ship(this, keyH);
	public CollisionChecker cc = new CollisionChecker(this);
	//public AssetSetter aSetter = new AssetSetter(this);
	//public Player player = new Player(this, keyH);
	//public SuperObject obj[] = new SuperObject[10];

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {		
			update();
			repaint();
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;
				
				if (remainingTime < 0) { remainingTime = 0; }
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e ) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void update() {
		cc.astroidCollision();
		ship.update();
		for(int i = 0; i < t.length; i++) {
			if(t[i] != null) {
				t[i].update();
				if(t[i].life <= 0) {t[i] = null;}
			}
		}
		boolean empty = true;
		for(int i = 0; i < a.length; i++) {
			if(a[i] != null) {
				a[i].update();
				empty = false;
			}
		}
		if(empty) { setupGame(); }
	}
	
	public void setupGame() {
		Random rand = new Random();
		a[0] = new Astroid(this, rand.nextInt(screenWidth), rand.nextInt(screenHeight), 0, 0);		
		a[1] = new Astroid(this, rand.nextInt(screenWidth), rand.nextInt(screenHeight), 0, 1);		
		a[2] = new Astroid(this, rand.nextInt(screenWidth), rand.nextInt(screenHeight), 0, 2);		
		a[3] = new Astroid(this, rand.nextInt(screenWidth), rand.nextInt(screenHeight), 0, 3);		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// ship
		ship.draw(g2);
		
		//torpedos
		for(int i = 0; i < t.length; i++) {
			if(t[i] != null) {
				t[i].draw(g2);
			}
		}
		
		//astrods
		for(int i = 0; i < a.length; i++) {
			if(a[i] != null) {
				a[i].draw(g2);
			}
		}
		
		g2.dispose();
	}

}
