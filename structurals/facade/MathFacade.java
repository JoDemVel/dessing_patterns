package structurals.facade;

public class MathFacade {
    private ExternalCalculator calculator;

    public MathFacade() {
        calculator = new ExternalCalculator();
    }

    public double calculateExpression(String expression) {
        return calculator.evaluate(expression);
    }

    public static void main(String[] args) {
        MathFacade mathFacade = new MathFacade();
        String expression = "7 + 3*2*(1/2) + 3*8";
        double result = mathFacade.calculateExpression(expression);
        System.out.println("Result of the expression '" + expression + "': " + result);
    }
}

