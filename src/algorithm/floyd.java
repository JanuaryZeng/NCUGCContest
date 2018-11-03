package algorithm;

import java.util.Scanner;
/**
 * ���ࣺ��Դ���·���㷨���㷨���Ӷ�Ϊo(n^3)
 * ���ã�1.�����·�� 2.�ж�һ��ͼ�е������Ƿ�������
 * �ŵ㣺ֻ�����ݹ�ģ��С��ʱ�ո��Ӷȶ�����ʱ�ſ���ʹ�ã�NOIP�ϴ�Ų���ų����İɣ���
 * ˼�룺3��ѭ������һ��ö���м��k���ڶ����������ö�������˵�i��j��
 * 		����dis[i][j] > dis[i][k] + dis[k][j] ���dis[i][j]
 * 		���³�dis[i][k] + dis[k][j]��ԭ���Ǻܺ����ģ���
 * ����������
3 3
1 2 3
1 3 6
2 3 5
 *1.�ڵ���� �߸���
 *2.����ߺ�Ȩ�� 1-->2 Ȩ��3
 * @author January
 */

public class floyd {
	private static int [][]distance;
	private static int x;
	private static int y;
	private static int INF = 1000000;
	
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
	            	//��kΪ�м�ڵ㣬�ж�i��j��ԭ���ľ���;���k�ľ��룬���
	                if(distance[i][j]>distance[i][k]+distance[k][j])
	                	distance[i][j]=distance[i][k]+distance[k][j];
	            }
	}
}
