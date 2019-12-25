public class LCS
{
	private static String word1;
	private static String word2;
	
	public static String findLCS(String w1, String w2)
	{
		word1 = w1;
		word2 = w2;

		int m= word2.length()+1;
		int n= word1.length()+1;
		int[][] opt = new int[n][m];
		char[][] parent=new char[n][m];
		for(int j=0;j<m;j++)
		{
			opt[0][j]=0;
		}
		for(int i=1;i<n;i++){
			opt[i][0]=0;
			for(int j=1;j<m;j++)
			{
				if(word1.charAt(i-1)==word2.charAt(j-1))
				{
					opt[i][j]=opt[i-1][j-1]+1;
					parent[i][j]='d';
				}
				else if(opt[i][j-1]>=opt[i-1][j]) {
					opt[i][j]=opt[i][j-1];
					parent[i][j]='l';
				}
				else{
					opt[i][j]=opt[i-1][j];
				    parent[i][j]='u';
				}
			}
		}
		int i=n-1,j=m-1;
		String output="";
		while(i>0 && j>0){
			if(parent[i][j]=='d')
			{
				output=Character.toString(word1.charAt(i-1))+output;
				i--;j--;
			}
			else if(parent[i][j]=='u'){
				i--;
			}
			else{
				j--;
			}
		}
		return output;
	}
}