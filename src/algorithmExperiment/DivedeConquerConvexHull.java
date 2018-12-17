package algorithmExperiment;

import java.util.Arrays;
import java.util.Random;

public class DivedeConquerConvexHull {
	public static Point3[] points;
	public static double[] s = new double[20];
	public static void hull(int l,int r,Point3 p1,Point3 p2) {
		int x=l;
        int i=l-1,j=r+1;
        /**
         * 找出距离直线p1-p2最远的点p3
         * */
        for (int k = l; k <= r; k++){
            if (s[x] - s[k] <= 0) {
                x=k;
            }
        }
        Point3 p3 = points[x];
        /**
         * p1-p3左侧的点
         * */
        for (int k = l; k <= r; k++) {
 
            s[++i] = cross(points[k], p1, p3);
            if (s[i] > 0) {
                Point3 temp = points[i];
                points[i] = points[k];
                points[k] = temp;
            } else {
                i--;
            }
        }
        /**
         * 直线p3-p2右侧的点
         * */
        for (int k=r;k>=l;k--) {
            s[--j]=cross(points[k], p3, p2);
            if (s[j] > 0) {
                Point3 temp = points[j];
                points[j] = points[k];
                points[k] = temp;
            } else {
                j++;
            }
        }
        /**
         * 分治，并中序输出
         * */
        if (l <= i) {
            hull(l, i, p1, p3);
        }
        System.out.printf("(%f,%f)\n",p3.getX(),p3.getY());
        if (j <= r) {
            hull(j, r, p3, p2);
        }
    }
    private static double cross (Point3 a, Point3 b, Point3 c) {
        return (b.getX()-a.getX())*(c.getY()-a.getY())-(b.getY()-a.getY())*(c.getX()-a.getX());
	}
	
	public static void main(String[]agrs) {
		Random rd = new Random();
		points = new Point3[20];
		for(int i = 0; i < 20; i++) {
			float x = rd.nextFloat();
			float y = rd.nextFloat();
			points[i] = new Point3(x, y);
			System.out.printf("(%f,%f)\n",x, y);
		}
		Arrays.sort(points);
		System.out.println("*************************");
		hull(1,19,points[0],points[0]);
		
	}
	
}
class Point3 implements Comparable{
	private float x;
	private float y;
	private boolean temp = false;
	public Point3(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public boolean getTemp() {
		return temp;
	}
	public void setTemp() {
		temp = true;
	}
	
	@Override
	public int compareTo(Object o) {
		Point3 point = (Point3)o;
		if(this.x == point.x) {
			return (int)(this.y - point.y);
		}
		return (int)(this.x - point.x);
	}
}
