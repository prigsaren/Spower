package objects;


import java.awt.*;
import java.awt.geom.*;

import tools.C;
import towerDefence.*;

public abstract class Aim extends Defence{
	
	private int aimDiameter;
	Handler handler;
	
	private long lastShoot;
	private int shootDelay;
	private int damage;
	
	private Image image;
	
	private int xDistance = 0;
	private int yDistance = 1;
	private AffineTransform a;
	
	public Aim(int x, int y, int width, int height, int aimDiameter, Handler handler, ID id, int shootDelay, int damage, Image image) {
		super(x, y, width, height, id);
		
		this.aimDiameter = aimDiameter;
		this.handler = handler;
		this.shootDelay = shootDelay;
		this.damage = damage;
		this.image = image;
	}

	@Override
	public void tick() {
		GameObject gm = null;
		
			for(int i = 0; i<handler.getGameObject().size(); i++) {
				gm = handler.getGameObject().get(i);
		
			if(gm.getId() == ID.BasicEnemy && C.getClosestDistance(x+width/2, y+height/2, gm.getX() + gm.getWidth()/2, gm.getY()+gm.getHeight()/2) <= aimDiameter/2){
				xDistance = x + width/2 - gm.getX() - gm.getWidth()/2;
				yDistance = y + height/2- gm.getY() - gm.getHeight()/2;
				break;
				
			}
			gm = null;
				
		}
			if(System.currentTimeMillis() >= lastShoot + shootDelay && gm != null) {
				handler.addObject(new Bullet(x + width/2 ,y+height/2 ,gm.getX() + gm.getWidth()/2, gm.getY()+gm.getHeight(), damage, handler));
				lastShoot = System.currentTimeMillis();
			}
	}
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		
		double angle = -Math.atan((double)xDistance/Math.abs((double)yDistance));
		if(yDistance< 0)
			angle += Math.toRadians(180)-angle*2;
//		else if(yDistance < 0 && angle < 0)
//			angle+= Math.toRadians();
//		System.out.println(Math.toDegrees(angle));
		
		a = AffineTransform.getRotateInstance(angle, x+width/2, y+width/2);
//		a = AffineTransform.getRotateInstance(Math.toRadians(-45) , x+width/2, y+width/2);
		
		g2d.setTransform(a);
		g2d.drawImage(image, x, y, width, height, null);
		
		a = AffineTransform.getRotateInstance(0, x+width/2, y+width/2);
		g2d.setTransform(a);
		
	}

}
