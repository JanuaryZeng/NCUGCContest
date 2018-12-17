package algorithmExperiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruteForceRecently {
	/* 问题：最近对问题（使用蛮力法求平面中距离最近的两点）
	 * 思路：使用欧式距离计算，并比较每两点间的距离
	 * 标准输入输出：
	 * 1.首先输入点的个数
	 * 2.然后输入各点的坐标
	 * 3.输出最短距离
input:
3
1 2
2 3
1 1
output:
1
	 */
	public static float getDistance(Point1 p1, Point1 p2) {
		return (float) Math.sqrt(Math.abs(p1.getX()-p2.getX()) + Math.abs(p1.getY()-p2.getY()));	
	}
	
	public static void BruteForce(List<Point1> points) {
		Point1 start = null;
		Point1 end = null;
		int length = points.size();
		float min = 10000;
		
		for(int i = 0; i < length-2; i++) {
			for(int j = i+1; j < length-1; j++) {
				float d = getDistance(points.get(i),points.get(j));
				if(min > d) {
					start = points.get(i);
					end = points.get(j);
					min = d;
				}
			}
		}
		System.out.printf("点1为 : (%f,%f)\n",start.getX(),start.getY());
		System.out.printf("点2为: (%f,%f)\n",end.getX(),end.getY());
		System.out.printf("最小距离为：%f\n",min);
	}
	
	public static void main(String agrs[]) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		List<Point1> points = new ArrayList<Point1>();
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			Point1 p = new Point1(x, y);
			points.add(p);
		}
		BruteForce(points);
		sc.close();
	}
}

class Point1{
	private float x;
	private float y;
	public Point1(float x, float y) {
		this.x = x;
		this.y = y;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
}
	