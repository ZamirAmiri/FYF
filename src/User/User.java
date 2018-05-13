package User;

public class User {
	private String name;
	private double x,y;

	User(String name,double x, double y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	
}
