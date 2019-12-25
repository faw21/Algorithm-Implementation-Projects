public class Cut
{
	public boolean type;

	public int width;
	public int height;
	public int cut;
	public int value;
	public int x1;
	public int x2;
	public int y1;
	public int y2;

	public ClothCutter leftNode;
	public ClothCutter rightNode;
	
	public Cut(int x, int y, ClothCutter c)
	{

		if (!c.type)
		{
			this.x1 = x + c.leftNode.width;
			this.y1 = y;
			this.x2 = x + c.leftNode.width;
			this.y2 = y + c.height;
		}

		if (c.type)
		{
			this.x1 = x;
			this.y1 = y + c.leftNode.height;
			this.x2 = x + c.width;
			this.y2 = y + c.leftNode.height;
		}		
	}
}
