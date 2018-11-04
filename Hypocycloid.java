import java.util.ArrayList;

public class Hypocycloid {
	private int fixedRadius;
	private int movingRadius;
	private int penOffset;
	private int loops;
	private int scale;
	private int xOffset;
	private int yOffset;
	private final double TwoPI;
	//TODO: changing the scale
	
	public Hypocycloid(int fixedRadius, int movingRadius, int penOffset, int loops, int scale, int xOffset, int yOffset) {
		this.fixedRadius = fixedRadius;
		this.movingRadius = movingRadius;
		this.penOffset = penOffset;
		this.loops = loops;
		this.scale = scale;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.TwoPI = Math.PI * 2;
	}
	
	public ArrayList<Tuple<Double, Double>> getCoordList() {
		int n = fixedRadius;
		int m = movingRadius;
		int f = penOffset;
		
		ArrayList<Tuple<Double, Double>> coordList = new ArrayList<Tuple<Double, Double>>();
		
		for (double theta = 0; theta <= TwoPI * loops; theta += 0.05) {
			double t = theta / n;
			double x =  (1 - (n / m)) * Math.cos(n * t) + ((f * (n / m)) * Math.cos((m - n) * t));
			double y =   (1 - (n / m)) * Math.sin(n * t) - ((f * (n / m)) * Math.sin((m - n) * t));
			System.out.println(x + " " +  y);
			x *= scale;
			y *= scale;
			x += xOffset;
			y += yOffset;
			
			coordList.add(new Tuple<Double, Double>(x, y));
		}
		
		return coordList;
	}
}