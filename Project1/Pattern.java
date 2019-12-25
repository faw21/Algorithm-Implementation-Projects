public class Pattern 
{
	public int height;
	public int width;
	public int price;
	public String name;

	public Pattern() 
	{
		this.height = 0;
		this.width = 0;
		this.price = 0;
		this.name = "";
	}

	public Pattern(int w, int h, int p, String n)
	{
		this.height = h;
		this.width = w;
		this.price = p;
		this.name = n;
	}
	
	
}