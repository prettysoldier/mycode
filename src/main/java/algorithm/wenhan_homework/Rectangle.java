package algorithm.wenhan_homework;

public class Rectangle  {
	private int length, width;
	public Rectangle () {
		this.length = 1;
		this.width = 1;
	}
	public Rectangle(int a, int b) {
		this.length = a;
		this.width = b;
	}
	public int getLength() {
		return width;
	}
	public int getWidth() {
		return length;
	}
	public void setLength(int l) {
		length = l;
	}
	public void setWidth(int w) {
		width = w;
	}
	public int Area(Rectangle r) {
		int area = (r.getLength())*(r.getWidth());
		for (int i = 1; i < 10; i++) {
			System.out.println("The area is: "+ area);
		}
		return area;
	}
	public boolean isSquare(Rectangle r) {
		if (r.getLength()==r.getWidth()) {
			return true;
		}else {
			return false;
		}
	}
}
