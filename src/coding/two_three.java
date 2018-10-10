package coding;

import java.util.Scanner;

public class two_three {
	public static void bfs(char[][] ferry,boolean[][] Bool, int a, int b) {
		Bool[a][b] = false;
		if(a+1<length && Bool[a+1][b] && ferry[a+1][b] == '+')
			bfs(ferry,Bool,a+1,b);
		else if(b+1<weith&& Bool[a][b+1] && ferry[a][b+1] == '+')
			bfs(ferry,Bool,a,b+1);
		else if(a-1>0 && Bool[a-1][b] && ferry[a-1][b] == '+')
			bfs(ferry,Bool,a-1,b);
		else if(b-1>0 && Bool[a][b-1] && ferry[a][b-1] == '+')
			bfs(ferry,Bool,a,b-1);
		else
			return;
	}
	static int length = 0;
	static int weith = 0;
	 public static int countOfShips(char[][] ferry) {
		 length = ferry.length;
		 weith = ferry[0].length;
		 int number = 0;
		 boolean[][] Bool = new boolean[length][weith];
		 for(int i = 0; i < length; i++) 
			 for(int j = 0; j < weith; j++) {
				 Bool[i][j] = true;
			 }
		 for(int i = 0; i < length; i++) 
			 for(int j = 0; j < weith; j++) {
				 if(ferry[i][j] == '+' && Bool[i][j]) {
					 number++;
					 bfs(ferry,Bool,i,j);
				 }
			 }
		 return number;
	 }
	 public static void main(String [] args) {
		 Scanner sc = new Scanner(System.in);
		 int length = sc.nextInt();
		 int weith = sc.nextInt();
		 char [][] c = new char[length][weith];
		 for(int i = 0; i < length; i++) {
			 for(int j = 0; j < weith; j++) {
				 c[i][j] = sc.next().charAt(0);
			 }
		 }
		 System.out.println(countOfShips(c));
		 
	 }
}
