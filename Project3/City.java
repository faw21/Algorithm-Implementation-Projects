public class City
{
	private String cityIndex;
	private double x;
	private double y;
    
    public City()
    {
    	this(null, 0, 0);
    }
    public City(String index, double d, double e)
    {
    	setcityIndex(index);
    	setX(d);
    	setY(e);
    }

	private void setcityIndex(String cityIndex) { this.cityIndex = cityIndex; }
	public double getX() { return x; }
	public double getY() { return y; }
	private void setX(double x) { this.x = x; }
	private void setY(double y) { this.y = y; } 
}
