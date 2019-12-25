//////////////////////////////////////////////////////////////////////
///
/// Contents: Test LCS program.
/// Author:   John Aronis
/// Date:     April 2013
///
//////////////////////////////////////////////////////////////////////

public class TestLCS {

  public static void main(String[] args) {
    String word1, word2 ;
    word1 = "axxbyyycz" ;
    word2 = "1a2b333c44444" ;
    System.out.print("LCS of " + word1 + " and " + word2 + ": ") ;
    System.out.println( LCS.findLCS(word1,word2) ) ;
  }

}

/// End-of-File

