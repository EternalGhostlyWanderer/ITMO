package models;
/** 
 * Class Coordinates
 */
public class Coordinates {
	private long x; //Значение поля должно быть больше -418
    private double y; //Значение поля должно быть больше -287
	
	public Coordinates(long x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public Long getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public boolean validate() {
		return x > -418 && y >-287;
	}

	@Override
	public String toString() {
		return "Coordinates{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}