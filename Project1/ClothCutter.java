import java.util.*;

public class ClothCutter
{
	public boolean type;
	
	public int width;
	public int height;
	public int value;
	public int cut;
	
	public Pattern pattern;
	
	public ClothCutter leftNode;
	public ClothCutter rightNode;
	
	private ArrayList<Pattern> patterns = new ArrayList<Pattern>();
	private ArrayList<Cut> cuts = new ArrayList<Cut>();
	private ArrayList<Garment> garments = new ArrayList<Garment>();

	private int verticallyBest;
	private int horizontallyBest;
	
	public ClothCutter()
	{
		this.type = false;
		this.width = 0;
		this.height = 0;
		this.cut = 0;
		this.value = 0;
		this.pattern = new Pattern();
		this.leftNode = null;
		this.rightNode = null;
	}
	
	
	public ClothCutter(int w, int h, ArrayList<Pattern> p)
	{
		this.width = w;
		this.height = h;
		this.patterns = p;	
		this.leftNode= null;
		this.rightNode= null;
	}
	

	public ClothCutter(boolean type, int width, int height,int cut,int value, Pattern pattern, ClothCutter leftNode, ClothCutter rightNode)
	{
		this.type = type;
		this.width = width;
		this.height = height;
		this.cut = cut;
		this.value = value;
		this.pattern = pattern;
		this.leftNode = leftNode;
		this.rightNode = rightNode;
	}

	public void optimize ()
	{
		ClothCutter[][] memo = new ClothCutter[this.width+1][this.height+1];
		optimize(this.width, this.height, memo);
		this.value = memo[this.width][this.height].value;
		getCuts(0,0, memo[this.width][this.height], this.cuts);
		getGarments(0,0, memo[this.width][this.height], this.garments);
	}
	
	public void optimize (int w, int h, ClothCutter[][] memo)
	{
		int best = 0;
		boolean type = false;

		if(memo[w][h]!=null) return;
		
		ClothCutter temp = new ClothCutter(false, w, h, 0, 0, null, null, null);
		
		for (Pattern p: this.patterns)
		{
			if(p.height <= h && p.width <= w && p.price > best)
			{
				temp = new ClothCutter(false, w, h, 0, p.price, p, null, null);
				best = temp.value;
			}
				if(best==0) return;
		}
		
		
		if (best !=0)
		{
			for (int i=1; i<w; i++)
			{
				ClothCutter left = new ClothCutter(i, h, this.patterns);
				ClothCutter right = new ClothCutter(w-i, h, this.patterns);
				
				optimize(i, h, memo);
				optimize(w-i, h, memo);
				
				left = (ClothCutter)memo[i][h];
				right = (ClothCutter)memo[w-i][h];
			
				if (left!=null && right!=null)
				{
					verticallyBest = left.value + right.value; 
					if(verticallyBest > best)
					{
						best = verticallyBest;
						type = false;
						temp = new ClothCutter(type, w, h, i, best, new Pattern(), left, right); 
					}
				}
			}
		
			for (int i=1; i<h; i++)
			{
			
				ClothCutter top = new ClothCutter(w, i, this.patterns);
				ClothCutter bottom = new ClothCutter(w, h-i, this.patterns);
				optimize(w, i, memo);
				optimize(w, h-i, memo);
				top = (ClothCutter)memo[w][i];
				bottom = (ClothCutter)memo[w][h-i];
				if(top!=null && bottom!=null)
				{
					horizontallyBest = top.value + bottom.value;
					if(horizontallyBest > best)
					{
						best = horizontallyBest;
						type = true;
						temp = new ClothCutter(type, w, h, i, best, new Pattern(), top, bottom);
					}
				}
			}

			memo[w][h]=temp;
		}
	}
		
		
	public int value()
	{
	    return this.value;
	} 

	public ArrayList<Cut> cuts()
	{
		  return this.cuts; 
	} 
	
	public ArrayList<Garment> garments()
	{
	    return this.garments;
	} 

	public void getCuts(int x, int y, ClothCutter cutter, ArrayList<Cut> cuts)
	{
		if(cutter.leftNode==null) return;
		cuts.add(new Cut(x, y, cutter));
		getCuts(x, y, cutter.leftNode, cuts);
		if(!cutter.type) getCuts(x+cutter.leftNode.width, y, cutter.rightNode, cuts);
		else getCuts(x, y + cutter.leftNode.height, cutter.rightNode, cuts);
		return;
	}
	
	public void getGarments(int x, int y, ClothCutter cutter, ArrayList<Garment> garments)
	{
		if (cutter.leftNode != null)
		{
			getGarments(x, y, cutter.leftNode, garments);
			if (!cutter.type) getGarments(x+cutter.leftNode.width, y, cutter.rightNode, garments);
			else getGarments(x, y + cutter.leftNode.height, cutter.rightNode, garments);
		}
		
		if (cutter.pattern.price != '0' && cutter.pattern.name != "" ) 
		{
			garments.add(new Garment(x,y,cutter.width,cutter.height,cutter.pattern.price,cutter.pattern.name));
		}
	}
		 


}