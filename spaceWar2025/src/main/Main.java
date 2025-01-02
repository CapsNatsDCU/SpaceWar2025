package main;

import javax.swing.*;

public class Main {
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			JFrame window = new JFrame();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(false);
			window.setTitle("Space War 2025");
			
			GamePanel gPan = new GamePanel();
			window.add(gPan);
			
			window.pack();
			
			window.setLocationRelativeTo(null);
			window.setVisible(true);
			
			gPan.setupGame();
			gPan.startGameThread();
	}


}
