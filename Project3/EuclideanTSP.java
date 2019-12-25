import java.util.*;

public class EuclideanTSP
{
    public static ArrayList<Edge> MST(ArrayList<City> c)
    {
    	ArrayList<Edge> MST = new ArrayList<Edge>();
		ArrayList<City> cities = new ArrayList<City>();
    	ArrayList<City> cityTree = new ArrayList<City>();
    	
    	cities.addAll(c);
    	cityTree.add(cities.remove(0));
    	
    	for(City city : c) cities.add(city);
    	
    	while(!cities.isEmpty()) 
    	{
    		Edge minEdge = null;
			double minDistance = Double.MAX_VALUE;	 

			for(City start : cityTree)
			{
				for(City end : cities)
				{		
					double distance = dist(start, end);		

					if( distance < minDistance )
					{	
						minEdge = new Edge(start, end);
						minDistance = distance;		
					}
				}
			}
			cities.remove(minEdge.getCity2());					
			cityTree.add(minEdge.getCity2());
			MST.add(minEdge);		
    	}
		return MST;
        
    }
	public static ArrayList<City> MSTTour(ArrayList<Edge> mst) 
	{
		ArrayList<Edge> MST = new ArrayList<Edge>();
		ArrayList<City> MSTTour = new ArrayList<City>();
		
		for(Edge edge : mst) MST.add(edge);
		
		return MSTTour(MST.get(0).getCity1(), MST, MSTTour);
	}
	private static ArrayList<City> MSTTour(City city1, ArrayList<Edge> mst, ArrayList<City> mstTour)
	{
		mstTour.add(city1);
		
		for(Edge edge : mst)
		{
			City parent = edge.getCity1();
			City child = edge.getCity2();
			if( parent.equals(city1) && !mstTour.contains(child) )
			{ mstTour = MSTTour(edge.getCity2(), mst, mstTour); }
		}
		return mstTour;
	}
	public static String weight(ArrayList<Edge> mst)
	{
		double weight = 0;
		
		for(Edge edge : mst)
		{ weight += dist(edge.getCity1() , edge.getCity2()); }
		
		return Double.toString(weight);
	}
	public static String length(ArrayList<City> mstTour)
	{
		double length = 0;
		
		for(int n=0; n<mstTour.size()-1;n++)
		{ length += dist( mstTour.get(n) , mstTour.get(n+1)); }
		
		return Double.toString(length);
	}

	public static double dist(City a, City b)
	{
		double aX = a.getX();
		double aY = a.getY();
		double bX = b.getX();
		double bY = b.getY();
		double x = bX - aX;
		double y = bY - aY;
		
		return Math.sqrt(Math.pow(x , 2.0)+Math.pow(y , 2.0));
	}

}
