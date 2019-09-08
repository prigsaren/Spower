package towerDefence;


public class C{
	
	public static int getClosestDistance(int x, int y, int x2, int y2) {
		int xd = toPositive(x-x2);
		int yd = toPositive(y-y2);
	
		return (int)Math.round(Math.sqrt(xd*xd+yd*yd));
	}
	
	public static int toPositive(int x) {
		return Math.abs(x);
	}
	public static int toNegative(int x) {
		return -Math.abs(x);
	}
	
	public static int smallestNr(int a, int b) {
		if(a < b) {
			return a;
		}
		 return b;
	}
	public static int largestNr(int a, int b) {
		if (a > b) {
			return a;
		}
		return b;
	}
	
	public static int xScreenPos(int x) {
		return (int)Math.round((x/1920.0)*Window.WIDTH);
		
	}
	public static int yScreenPos(int y) {
		return (int)Math.round((y/1080.0)*Window.HEIGHT);
		
	}
	

}

