package algorithmExperiment;

import java.util.Random;

public class Trackback {
	
	public static void Knapsack(int[] v,int[] w, int c, int n, int [][]m){
		int jMax = Math.min(w[n]-1, c);
		for	(int j = 0; j < jMax; j++){     
			m[n][j] = 0;
		}
		for (int j = w[n]; j <= c; j++){
			m[n][j] = v[n];
		}
		for (int i = n-1; i> 1; i--){
			jMax = Math.min(w[i] - 1, c);
			for (int j = 0; j <= jMax; j++)
				m[i][j] = m[i+1][j];
			for (int j = w[i]; j <= c; j++)
				m[i][j] = Math.max(m[i+1][j], m[i + 1][j - w[i]] + v[i]);
		}
		m[1][c] = m[2][c];
		if(c>=w[1])
			m[1][c] = Math.max(m[1][c], m[2][c - w[1]] + v[1]);
	}
	
	public static void Trace(int[][] m, int[] w, int c, int n, int[] x){
		for (int i = 1; i < n; i++){
			if(m[i][c] == m[i+1][c])
				x[i] = 0;
			else{
				x[i] = 1;
				c = c- w[i];
			}
		}
		x[n] = m[n][c] == 0 ? 0 : 1;
	}
	
	public static void main(String []args){
		Random rd = new Random();
		int[] v = new int[21];//物品的价值
		int[] w = new int[21];//物品的重量
		int[][] m = new int[21][21];//运算数组
		int c = 20;//背包容量
		int n = 20;//物品个数
		int[] x = new int [21];//存放最优解
		
		System.out.println("物品序号   价值   重量");
		for(int i = 0; i <= 20; i++){
			v[i] = rd.nextInt(20) + 1;
			w[i] = rd.nextInt(10) + 1;
			if(i!=0)
				System.out.println("  "+i+"     "+v[i]+"   "+w[i]);
		}
		
		Knapsack(v,w,c,n,m);
		System.out.println(m[1][c]);
		Trace(m,w,c,n,x);
		System.out.println("物品序号为：");
		for(int i = 0; i < 20; i++){
			if(x[i]==1)
				System.out.print(i+" ");
		}
	}
}
