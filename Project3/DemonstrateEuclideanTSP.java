///
/// Contents: Demonstrate Euclidean TSP Programs
/// Author:   John Aronis
/// Date:     July 2018
///

import java.awt.* ;
import javax.swing.* ;
import java.util.ArrayList ;
import java.util.Date ;
import java.util.Random ;

public class DemonstrateEuclideanTSP {

  public static int WIDTH=800, HEIGHT=800, WAIT=3000 ;

  public static void main(String[] args) {

    //
    // Create cities:
    //

//    ArrayList<City> cities = smallCities() ;     // Small test.
    ArrayList<City> cities = randomCities(Integer.parseInt(args[0]),WIDTH,HEIGHT) ;

    //
    // MST-walk algorithm:
    //

    ArrayList<Edge> MST = EuclideanTSP.MST(cities) ;
    ArrayList<City> MSTTour = EuclideanTSP.MSTTour(MST) ;
    System.out.println("MST weight: " + EuclideanTSP.weight(MST)) ;
    System.out.println("MST tour length: " + EuclideanTSP.length(MSTTour)) ;

    //
    // Create JFrame and map:
    //

    JFrame mstwalk_tour_frame = new JFrame("MST-Walk Tour") ;
    Map mstwalk_tour_map = new Map(WIDTH,HEIGHT,WIDTH,HEIGHT) ;
    mstwalk_tour_frame.getContentPane().add(mstwalk_tour_map) ;
    mstwalk_tour_frame.pack() ;
    mstwalk_tour_frame.setVisible(true) ;
    mstwalk_tour_frame.repaint() ;
    sleep(WAIT) ;

    //
    // Display cities:
    //

    mstwalk_tour_map.CITY_COLOR = Color.blue ;
    for (City c : cities) { mstwalk_tour_map.city(c) ; }
    sleep(WAIT) ;

    //
    // Display MST:
    //

    mstwalk_tour_map.EDGE_COLOR = Color.gray ;
    for (int i=0 ; i<MST.size() ; i++) {
      mstwalk_tour_map.edge(MST.get(i)) ;
    }
    sleep(WAIT) ;

    //
    // Display MST-Walk:
    //

    mstwalk_tour_map.EDGE_COLOR = Color.red ;
    for (int i=0 ; i<MSTTour.size()-1 ; i++) {
      mstwalk_tour_map.edge(new Edge(MSTTour.get(i),MSTTour.get(i+1))) ;
    }
    sleep(WAIT) ;

  }

  public static ArrayList<City> smallCities() {
    ArrayList<City> result = new ArrayList<City>() ;
    result = new ArrayList<City>() ;
    result.add(new City("a",100.0,100.0)) ;
    result.add(new City("b",300.0,300.0)) ;
    result.add(new City("c",300.0,500.0)) ;
    result.add(new City("d",500.0,100.0)) ;
    result.add(new City("e",600.0,100.0)) ;
    result.add(new City("f",200.0,600.0)) ;
    result.add(new City("g",300.0,600.0)) ;
    return result ;
  }

  public static ArrayList<City> randomCities(int n, double maxX, double maxY) {
    Random r = new Random() ;
    ArrayList<City> result = new ArrayList<City>() ;
    for (int i=0 ; i<n ; i++) {
      result.add( new City("city"+i,r.nextDouble()*maxX,r.nextDouble()*maxY) ) ;
    }
    return result ;
  }

  public static void sleep(long milliseconds) {
    Thread thread = new Thread() ;
    try { thread.sleep(milliseconds) ; }
    catch (Exception e) {}
  }

}

/// End-of-File
