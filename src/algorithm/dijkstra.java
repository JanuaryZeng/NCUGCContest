package algorithm;

import java.util.Arrays;
import java.util.Scanner;
/**
 * ���ࣺ��Դ���·���㷨��
 * �����ڣ�����ͼ�������ڶԵ�Ĵ���
 * ʱ�临�Ӷȣ�1.���أ�o(n^2) 2.���Ż�o(n*log n)
 * ȱ�㣺���ܴ�����ڸ���Ȩ�����
 * ʵ��˼·��
 * 1.��û�б����ʹ��ĵ�����һ������tempʹ��minDis[u]����С�ġ� 
 * 2.temp���Ϊ��ȷ�����·���� 
 * 3.for��temp������ÿ��δȷ�����·���Ķ���j��
 * ����������
4 4
1 2 1
1 3 2
2 4 8
3 4 3
2 4
 *1.�ڵ���� �߸���
 *2.����ߺ�Ȩ�� 1-->2 Ȩ��3
 *3.��ʼ�� ��ֹ��
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
		//x:�ڵ����
		x = sc.nextInt();
		//y:�߸���
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
			//1.ѭ��һ�ߵ�����˵�û�б����ã�����Ŀ����Ŀ��㵽�����ľ���С�������ľ������滻��
			for(int j = 1; j <= x; j++) {
				if(bool[j] && minDis[j] > minDis[temp] + distance[temp][j])
					minDis[j] = minDis[temp] + distance[temp][j];
			}
			//2.�ҵ���С�ľ��룬���ҽ����������Ϊ��һ�ε�temp��
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
