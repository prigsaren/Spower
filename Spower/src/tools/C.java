package tools;


public class C{
	
	public static int getClosestDistance(int x, int y, int x2, int y2) {
		int xd = toPositive(x-x2);
		int yd = toPositive(y-y2);
	
		return (int)Math.round(Math.sqrt(xd*xd+yd*yd));
	}
	
	public static int toPositive(int x) {
		if(x > 0)
			return x;
		else
			return -x;
	}
	public static int toNegative(int x) {
		if(x < 0)
			return x;
		else
			return -x;
	}
	

}

