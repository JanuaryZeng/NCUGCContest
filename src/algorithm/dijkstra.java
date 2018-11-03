package algorithm;

import java.util.Arrays;
import java.util.Scanner;
/**
 * 分类：单源最短路径算法。
 * 适用于：稠密图（侧重于对点的处理）
 * 时间复杂度：1.朴素：o(n^2) 2.堆优化o(n*log n)
 * 缺点：不能处理存在负边权的情况
 * 实现思路：
 * 1.在没有被访问过的点中找一个顶点temp使得minDis[u]是最小的。 
 * 2.temp标记为已确定最短路径。 
 * 3.for与temp相连的每个未确定最短路径的顶点j。
 * 输入样例：
4 4
1 2 1
1 3 2
2 4 8
3 4 3
2 4
 *1.节点个数 边个数
 *2.输入边和权重 1-->2 权重3
 *3.起始点 终止点
 * @author January
 */

public class dijkstra {
	private static int [][]distance;
	private static int []minDis;
	private static int INF = 1000000;
	private static boolean [] bool;
	private static int x;
	private static int y;
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
		bool = new boolean[x+1];
		minDis = new int[x+1];
		int start = sc.nextInt();
		int end = sc.nextInt();
		dijkstra(start);
		System.out.println(minDis[end]);
	}
	private static void dijkstra(int num) {
		int temp = num;
		Arrays.fill(bool, true);
		Arrays.fill(minDis, INF);
		bool[temp] = false;
		minDis[temp] = 0;
		for(int i = 1; i <= x; i++) {
			//1.循环一边点如果此点没有被引用，并且目标点和目标点到这个点的距离小于这个点的距离则替换。
			for(int j = 1; j <= x; j++) {
				if(bool[j] && minDis[j] > minDis[temp] + distance[temp][j])
					minDis[j] = minDis[temp] + distance[temp][j];
			}
			//2.找到最小的距离，并且将这个点设置为下一次的temp点
			int mini = INF;
			for(int j = 1; j <= x; j++) 
				if(bool[j] && minDis[j] < mini) {
					mini = minDis[j];
					temp = j;
				}
			bool[temp] = false;
		}
	}
}
