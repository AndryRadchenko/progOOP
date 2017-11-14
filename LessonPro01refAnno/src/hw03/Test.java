package hw03;

public class Test {

	@Save
	public static String s = "public static";
	
	@Save
	private int a = 1;
	
	@Save
	private double b = 1.0;

	public Test(int a, double b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	public Test() {
		super();
	}

	public static String getS() {
		return s;
	}

	public static void setS(String s) {
		Test.s = s;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	
	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "Test [a=" + a + ", b=" + b + "]";
	}
	
	
	
}
