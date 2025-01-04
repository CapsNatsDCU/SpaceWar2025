package main;

import entity.*;

public class CollisionChecker {
	
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public boolean shipCollision(Ship s) {
//		for(int i = 0; i < gp.t.length; i++) {
//			if(gp.t[i] != null) {
//				if(s.solidArea.intersects(gp.t[i].solidArea)) {
//					return true;
//				}
//			}
//		}
		for(int i = 0; i < gp.a.length; i++) {
			if(gp.a[i] != null) {
				if(s.solidArea.intersects(gp.a[i].solidArea)) {
					return true;
				}
			}
		}
		return false;
	}

	public void astroidCollision() {
		for(int i = 0; i < gp.t.length; i++) {
			if(gp.t[i] != null) {
			for(int j = 0; j < gp.a.length; j++)
				if(gp.a[j] != null){
					if(gp.a[j].solidArea.intersects(gp.t[i].solidArea)) {
						gp.a[j].stageChange();
						gp.t[i] = null;
						break;
					}
				}
			}
		}
	}
	
	
	
	
	
	
	
}
