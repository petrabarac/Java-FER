package hr.fer.zemris.java.custom.collections.Demo;
import hr.fer.zemris.java.custom.collections.*;

public class DemoEvenIntegerTester {
	public static void main(String[] args) {
		Tester t = new EvenIntegerTester();
		System.out.println(t.test("Ivo"));
		System.out.println(t.test(22));
		System.out.println(t.test(3));
		
	}
}
