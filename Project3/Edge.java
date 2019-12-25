public class Edge
{
	private City city1;
	private City city2;
    
    public Edge()
    {
    	this(null, null);
	}

    public Edge(City a,City b)
    {
    	setCity1(a);
    	setCity2(b);
    }
	
	public City getCity1() { return city1; }
	public City getCity2() { return city2; }
	
	private void setCity1(City city1) { this.city1 = city1; }
	private void setCity2(City city2) { this.city2 = city2; }
}
