package algorithmExperiment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * 问题:凸包
 * 方法：蛮力法
 * 标准输入输出：
 * 1.输入点的个数
 * 2.输入点的坐标
 * 3.输出凸包的边界点
input:
10
1 1
5 6
1 -1
2 -6
2 3
6 5
8 9
1 5
9 -8
-7 9
output:

 */
public class BruteForceConvexHull {
	
	public static void ConvexHull(List<Point2> points) {
		int length = points.size();
		float a, b, c;
		int sign1, sign2;
		for(int i = 0; i < length-1; i++) 
			for(int j = i+1;j < length;j++) {
				a = points.get(j).getY() - points.get(i).getY();
				b = points.get(i).getX() - points.get(j).getX();
				c = points.get(i).getX()*points.get(j).getY() - 
						points.get(i).getY()*points.get(j).getX();
				sign1 = 0;
				sign2 = 0;
				for(int k = 0; k < length; k++) {

					float x = points.get(k).getX();
					float y = points.get(k).getY();
					if(k == i || k == j) {
						continue;
					}
					if((a*x + b*y) == c) {
						sign1++;
						sign2++;
					}
					else if((a*x + b*y - c) < 0) {
						sign1++;
					}
					else
						sign2++;
				}
				if((sign1 == length-2) || (sign2 == length-2)) {
					System.out.println();
					points.get(i).setSigmal();
					points.get(j).setSigmal();
				}
			}	
		for(int i = 0; i < length; i++) {
			if(points.get(i).getSigmal())
				System.out.printf("点%d的坐标为：(%f,%f)\n",
						i+1,points.get(i).getX(),points.get(i).getY());
		}
	}
	
	public static void main(String[] agrs) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		List<Point2> points = new ArrayList<Point2>();
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			Point2 p = new Point2(x, y);
			points.add(p);
		}
		ConvexHull(points);
		sc.close();		
	}
}
class Point2{
	private float x;
	private float y;
	private boolean sigmal = false;
	public Point2(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setSigmal() {
		sigmal = true;
	}
	
	public boolean getSigmal() {
		return sigmal;
	}
}