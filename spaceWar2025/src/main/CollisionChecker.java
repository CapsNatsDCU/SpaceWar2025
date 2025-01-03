package main;

import entity.*;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public boolean shipCollision(Ship s) {
		for(int i = 0; i < gp.t.length;i++) {
			if(gp.t[i] != null) {
				if(s.solidArea.intersects(gp.t[i].solidArea)) {
					return true;
				}
			}
		}
		for(int i = 0; i < gp.a.length;i++) {
			if(gp.a[i] != null) {
				if(s.solidArea.intersects(gp.a[i].solidArea)) {
					return true;
				}
			}
		}
		return false;
	}

}
