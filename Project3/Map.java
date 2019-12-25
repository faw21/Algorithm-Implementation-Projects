///
/// Contents: JPanel to display cities, tours, and trees.
/// Author:   John Aronis
/// Date:     July 2018
///

import java.awt.* ;
import javax.swing.* ;
import java.util.ArrayList ;

public class Map extends JPanel {

  public Color BACKGROUND_COLOR = Color.white ;
  public Color CITY_COLOR       = Color.red ;
  public int   CITY_SIZE        = 5 ;
  public Color EDGE_COLOR       = Color.blue ;
  public int   EDGE_WIDTH       = 3 ;

  private double maxX, maxY ;
  private int width, height ;

  public Map(double maxX, double maxY, int width, int height) {
    this.maxX = maxX ;
    this.maxY = maxY ;
    this.width = width ;
    this.height = height ;
    setPreferredSize(new Dimension(width,height)) ;
  }

  public void city(City city) {
    Graphics g = this.getGraphics() ;
    g.setColor(CITY_COLOR) ;
    int intX = (int)((city.getX()/maxX)*width) ;
    int intY = (int)((city.getY()/maxY)*height) ;
    g.fillOval(intX-CITY_SIZE/2,intY-CITY_SIZE/2,CITY_SIZE,CITY_SIZE) ;
  }

  public void edge(Edge edge) {
    Graphics g = this.getGraphics() ;
    g.setColor(EDGE_COLOR) ; 
    int city1X, city1Y, city2X, city2Y ;     
    city1X = (int)((edge.getCity1().getX()/maxX)*width) ;
    city1Y = (int)((edge.getCity1().getY()/maxY)*height) ;
    city2X = (int)((edge.getCity2().getX()/maxX)*width) ;
    city2Y = (int)((edge.getCity2().getY()/maxY)*height) ;
    g.drawLine(city1X,city1Y,city2X,city2Y) ;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g) ;
    g.setColor(BACKGROUND_COLOR) ;
  }

}

/// End-of-File
