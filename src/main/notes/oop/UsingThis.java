package main.notes.oop;

public class UsingThis {

	public static void main(String[] args) {
		B b = new B();
		b.setX(-100);
		b.setY(-200);
		System.out.println("sum="+b.getXYSum());

	}
}
	class B {
		int x = 100, y;										//x=100, y=0
		
		public void setX(int x) {
			System.out.println("this.x="+this.x);			//100
			this.x+=x;
			System.out.println("x="+x);						//-100
			System.out.println("this.x="+this.x);			//0
		}
		
		public void setY(int y) {
			System.out.println("this.y="+this.y);			//0
			System.out.println("y="+y);						//-200
			y += y;
			System.out.println("y="+y);						//-400
			System.out.println("this.y="+this.y);			//0
		}
		
		public int getXYSum() {
			return x+y;
		}
	}

