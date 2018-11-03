package algorithm;

import java.util.Scanner;
/**
 * 分类：多源最短路径算法，算法复杂度为o(n^3)
 * 作用：1.求最短路。 2.判断一张图中的两点是否相连。
 * 优点：只有数据规模较小且时空复杂度都允许时才可以使用（NOIP上大概不会放出来的吧）。
 * 思想：3层循环，第一层枚举中间点k，第二层与第三层枚举两个端点i，j。
 * 		若有dis[i][j] > dis[i][k] + dis[k][j] 则把dis[i][j]
 * 		更新成dis[i][k] + dis[k][j]（原理还是很好理解的）。
 * 输入样例：
3 3
1 2 3
1 3 6
2 3 5
 *1.节点个数 边个数
 *2.输入边和权重 1-->2 权重3
 * @author January
 */

public class floyd {
	private static int [][]distance;
	private static int x;
	private static int y;
	private static int INF = 1000000;
	
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		//x:节点个数
		x = sc.nextInt();
		//y:边个数
		y = sc.nextInt();
		distance = new int[x+1][x+1];
		for(int i = 0; i <= x; i++) {
			distance[i] = new int[x+1];
			for(int j = 0; j <= x; j++) {
				distance[i][j] = INF;
				if(i==j)
					distance[i][j] = 0;
			}
		}
		for(int i = 0; i < y; i++) {
			int num1 = sc.nextInt();
			int num2 = sc.nextInt();
			int num3 = sc.nextInt();
			distance[num1][num2] = num3;
			distance[num2][num1] = num3;
		}
		floyd();
	    for(int i=1; i<=x; i++)
	    {
	        for(int j=1;j<=x;j++)
	        	System.out.print(distance[i][j]+"  ");
	        System.out.println();
	    }
	}
	private static void floyd() {
	    for(int k=1; k <= x; k++)
	        for(int i=1; i <= x; i++)
	            for(int j=1; j <= x; j++)
	            {
	            	//以k为中间节点，判断i到j的原来的距离和经过k的距离，孰大
	                if(distance[i][j]>distance[i][k]+distance[k][j])
	                	distance[i][j]=distance[i][k]+distance[k][j];
	            }
	}
}
