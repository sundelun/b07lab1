import java.io.File;

public class Driver {
    public static void main(String[] args) throws Exception {
        // Test constructor with coefficient and exponent arrays
        double[] coefficients = {6, -2, 5};
        int[] exponents = {0, 1, 3};
        Polynomial p1 = new Polynomial(coefficients, exponents);

        // Test evaluate
        System.out.println("p1 evaluated at -1: " + p1.evaluate(-1)); // Expected: 3.0

        // Test hasRoot
        System.out.println("Does p1 have root at x=1? " + p1.hasRoot(1)); // Expected: false

        // Test file-based constructor
        File file = new File("sample.txt"); // Assuming this file contains "5-3x^2+7x^8"
        Polynomial p2 = new Polynomial(file);
        double temp[]=p2.coefficients;
        for (double value : temp) {
            System.out.println(value);
        }
        int temp1[]=p2.exponents;
        for (int value : temp1) {
            System.out.println(value);
        }
        
        File file1 = new File("sample1.txt");
        Polynomial p3=new Polynomial(file1);
        double temp2[]=p3.coefficients;
        for (double value : temp2) {
            System.out.println(value);
        }
        int temp3[]=p3.exponents;
        for (int value : temp3) {
            System.out.println(value);
        }
        // Test add
        Polynomial sum = p1.add(p2);
        System.out.println("Result of adding p1 and p2: " + sum.evaluate(2)); // Evaluation at x=1 for the sake of simplicity

        Polynomial sum1=p1.multiply(p3);
        double temp4[]=sum1.coefficients;
        for (double value : temp4) {
            System.out.println(value);
        }
        int temp5[]=sum1.exponents;
        for (int value : temp5) {
            System.out.println(value);
        }

        // Test multiply
        Polynomial product = p1.multiply(p2);
        System.out.println("Result of multiplying p1 and p2: " + product.evaluate(2)); // Evaluation at x=1 for the sake of simplicity

        // Test saveToFile
        sum.saveToFile("output.txt"); // Check output.txt for the saved polynomial

        System.out.println("All tests completed!");
    }
}
