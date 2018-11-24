package green_cal_11_11;

import java.util.Scanner;

public class one_two {
	private static boolean[][]map;
	private static int x,y;
	public static int virusArea(int n,int m,char[][] area){
		/********* Begin *********/
		x = n;
		y = m;
		map = new boolean[n][m];

		for(int i = 0; i < n; i++) {
			map[i] = new boolean[m];
			for(int j = 0; j < m; j++) {
				if(area[i][j] == 'o')
					map[i][j] = true;
				else
					map[i][j] = false;
			}
		}
		int numb = 0;
		for(int i = 0; i < n; i++)
			for(int j = 0; j < m; j++) {
				if(map[i][j] == true) {
					dfs(i, j);
					numb++;
				}
			}
		/********* End *********/
		return numb;
	}
	
	public static void dfs(int n, int m) {
		if(n<0 || m<0 || n>=x || m>=y || map[n][m]==false)
			return;
		else
			map[n][m] = false;
		dfs(n+1, m);
		dfs(n, m+1);
		dfs(n-1, m);
		dfs(n, m-1);
	}
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		char[][] area = new char[n][m];
		for(int i = 0; i < n; i++) {
			area[i] = new char[m];
			for(int j = 0; j < m; j++) {
				area[i][j] = sc.next().charAt(0);
			}
		}
		System.out.print(virusArea(n,m,area));
	}
}
