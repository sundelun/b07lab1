import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    double[] coefficients;
    int[] exponents;

    // Default constructor
    public Polynomial() {
        this.coefficients = new double[0];
        this.exponents = new int[0];
    }
    public Polynomial(double[] coefficients,int[] exponents){
        this.coefficients=coefficients;
        this.exponents=exponents;
    }
    public Polynomial(File file) throws Exception {
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        scanner.close();

        ArrayList<Double> coeffList = new ArrayList<>();
        ArrayList<Integer> expList = new ArrayList<>();

        // Pattern for identifying each term
        Pattern termPattern = Pattern.compile("([-+]?\\d*x(\\d+)?|[-+]?\\d+)");
        Matcher matcher = termPattern.matcher(line);

        while (matcher.find()) {
            String term = matcher.group();

            boolean isNegative = term.startsWith("-");
        
            if (term.contains("x")) {
                String[] parts = term.split("x");

                // Coefficient
                if (parts[0].equals("-") || parts[0].isEmpty()) {
                    coeffList.add(isNegative ? -1.0 : 1.0);
                } else {
                    coeffList.add(Double.parseDouble(parts[0]));
                }

                // Exponent
                if (parts.length > 1) {
                    expList.add(Integer.parseInt(parts[1]));
                } else {
                    expList.add(1);
                }
            } else {
                coeffList.add(Double.parseDouble(term));
                expList.add(0);
            }
        }

        this.coefficients = new double[coeffList.size()];
        this.exponents = new int[expList.size()];
        for (int i = 0; i < coeffList.size(); i++) {
            this.coefficients[i] = coeffList.get(i);
            this.exponents[i] = expList.get(i);
        }
    }

    public Polynomial add(Polynomial other) {
        ArrayList<Double> newCoeffList = new ArrayList<>();
        ArrayList<Integer> newExpList = new ArrayList<>();

        for (int i = 0; i < this.coefficients.length; i++) {
            newCoeffList.add(this.coefficients[i]);
            newExpList.add(this.exponents[i]);
        }

        for (int j = 0; j < other.coefficients.length; j++) {
            boolean found = false;
            for (int k = 0; k < newExpList.size(); k++) {
                if (newExpList.get(k) == other.exponents[j]) {
                    newCoeffList.set(k, newCoeffList.get(k) + other.coefficients[j]);
                    found = true;
                    break;
                }
            }
            if (!found) {
                newCoeffList.add(other.coefficients[j]);
                newExpList.add(other.exponents[j]);
            }
        }

        double[] resultCoefficients = new double[newCoeffList.size()];
        int[] resultExponents = new int[newExpList.size()];
        for (int i = 0; i < newCoeffList.size(); i++) {
            resultCoefficients[i] = newCoeffList.get(i);
            resultExponents[i] = newExpList.get(i);
        }

        Polynomial result = new Polynomial();
        result.coefficients = resultCoefficients;
        result.exponents = resultExponents;

        return result;
    }

    public Polynomial multiply(Polynomial other) {
        ArrayList<Double> newCoeffList = new ArrayList<>();
        ArrayList<Integer> newExpList = new ArrayList<>();

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < other.coefficients.length; j++) {
                double multipliedCoeff = this.coefficients[i] * other.coefficients[j];
                int addedExp = this.exponents[i] + other.exponents[j];

                boolean found = false;
                for (int k = 0; k < newExpList.size(); k++) {
                    if (newExpList.get(k) == addedExp) {
                        newCoeffList.set(k, newCoeffList.get(k) + multipliedCoeff);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    newCoeffList.add(multipliedCoeff);
                    newExpList.add(addedExp);
                }
            }
        }

        double[] resultCoefficients = new double[newCoeffList.size()];
        int[] resultExponents = new int[newExpList.size()];
        for (int i = 0; i < newCoeffList.size(); i++) {
            resultCoefficients[i] = newCoeffList.get(i);
            resultExponents[i] = newExpList.get(i);
        }

        Polynomial result = new Polynomial();
        result.coefficients = resultCoefficients;
        result.exponents = resultExponents;

        return result;
    }

    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return result;
    }

    public boolean hasRoot(double x) {
        return Math.abs(this.evaluate(x)) < 1e-10;
    }

    public void saveToFile(String filename) throws IOException {
        FileWriter fw = new FileWriter(filename);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] > 0 && sb.length() > 0) {
                sb.append("+");
            }
            if (exponents[i] == 0) {
                sb.append(coefficients[i]);
            } else if (exponents[i] == 1) {
                sb.append(coefficients[i] + "x");
            } else {
                sb.append(coefficients[i] + "x" + exponents[i]);
            }
        }

        fw.write(sb.toString());
        fw.close();
    }
}
