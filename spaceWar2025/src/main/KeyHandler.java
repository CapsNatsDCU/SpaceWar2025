package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean leftPressed, thrustPressed, firePressed, rightPressed, hyperPressed; 
	
	@Override
	public void keyTyped(KeyEvent e) {	
		//unused
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			thrustPressed = true;
		}
		if(code == KeyEvent.VK_F) {
			firePressed = true;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if(code == KeyEvent.VK_SPACE) {
			hyperPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    int code = e.getKeyCode();
	    
	    if (code == KeyEvent.VK_A) {
	        leftPressed = false;
	    }
	    if (code == KeyEvent.VK_S) {
	        thrustPressed = false;
	    }
	    if (code == KeyEvent.VK_F) {
	        firePressed = false;
	    }
	    if (code == KeyEvent.VK_D) {
	        rightPressed = false;
	    }
	    if (code == KeyEvent.VK_SPACE) {
	        hyperPressed = false;
	    }
	}
}

