import java.io.File;
import java.util.Arrays;

public class Driver {
    public static void main(String [] args) {
//		File f = new File("bruh8");
//		Polynomial p1 = new Polynomial(f);
//		System.out.println(p1.evaluate(4));
//		double [] c1 = {-4, 0, 9};
//		Polynomial p2 = new Polynomial();
//		Polynomial p3 = p1.multiply(p2);
//		p3.saveToFile("bruh9");
//		File f = new File("bruh6");
//		Polynomial p1 = new Polynomial(f);
//		Polynomial p2 = new Polynomial(c2);
//		Polynomial p3 = p1.add(p2);
//		p3.saveToFile("bruh3");
//		System.out.println(Arrays.toString("x2".split("[x]")));
//		System.out.println(p1.hasRoot(2.0));
//		p1.saveToFile("bruh7");




		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,0,0,5};
		Polynomial p1 = new Polynomial(c1);
		double [] c2 = {0,-2,0,0,-9};
		Polynomial p2 = new Polynomial(c2);
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		s.saveToFile("bruh1");
		if(s.hasRoot(1)) {
			System.out.println("1 is a root of s");
		}
		else {
			System.out.println("1 is not a root of s");
		}
		File funfile = new File("bruh5");
		Polynomial p3 = new Polynomial(funfile);
		System.out.println("p3(2) = " + p3.evaluate(2.0));
		Polynomial p4 = p1.multiply(p2);
		p4.saveToFile("bruh1");
		double [] c3 = {2, 1};
		double [] c4 = {-2, 1};
		Polynomial p5 = new Polynomial(c3);
		Polynomial p6 = new Polynomial(c4);
		Polynomial p7 = p5.multiply(p6);
		System.out.println(p7.evaluate(2.0));
		p7.saveToFile("bruh2");

	}

}
