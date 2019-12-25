public class Garment
{

	public int x;
	public int y;
	public int width;
	public int height;
	public int value;
	public String name;
	
	public Garment(int x, int y, int w, int h, int v, String n)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		this.value = v;
		this.name = n;
	}
	
	public String toString()
	{
		return "[" + this.name +"," + this.x + "," + this.y + "]";
	}
}