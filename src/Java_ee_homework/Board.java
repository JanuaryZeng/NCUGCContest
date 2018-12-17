package Java_ee_homework;

import java.util.ArrayList;
import java.util.List;

/**
 * 学号：1607094240
 * 姓名： 曾祥玖
 */

public class Board {
	List<Shape> shapes;
	public Board() {
		shapes = new ArrayList<Shape>();
	}
	public void add(Shape shape) {
		shapes.add(shape);
	}
	
	public void Refresh() {
		for(Shape shape:shapes) {
			shape.Draw();
		}
	}
	
	public static void main(String []args) {
		Board board = new Board();
		board.add(new Rect(10,10,100,100));
		board.add(new Rect(20,20,50,50));
		board.add(new Circle(5,10,10));
		board.add(new Circle(6,15,15));
		board.add(new Rect(15,15,35,35));
		board.add(new Circle(6,5,8));
		
		board.Refresh();
	}
}

interface Shape {
	void Draw();
}

class Circle implements Shape{
	private float r;
	private float x;
	private float y;
	
	public Circle(float r, float x, float y) {
		this.r = r;
		this.x = x;
		this.y = y;
	}
	public float getR() {
		return r;
	}
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	@Override
	public void Draw() {
		System.out.print("This is a Circle.\n");
		System.out.printf("半径为:%f,圆心坐标为:(%f,%f)\n\n",r,x,y);
	}
}

class Rect implements Shape{
	private float left;
	private float top;
	private float width;
	private float height;
	
	public Rect(float left, float top, float width, float height) {
		this.setLeft(left);
		this.setTop(top);
		this.setWidth(width);
		this.setHeight(height);
	}

	@Override
	public void Draw() {
		System.out.print("This is a Rect.\n");
		System.out.printf("左上角坐标为:(%f,%f),宽为:%f,长为：%f\n\n",left,top,width,height);
	}

	public float getLeft() {
		return left;
	}

	public void setLeft(float left) {
		this.left = left;
	}

	public float getTop() {
		return top;
	}

	public void setTop(float top) {
		this.top = top;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}
}