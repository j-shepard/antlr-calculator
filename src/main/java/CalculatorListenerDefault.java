import java.util.Stack;

public class CalculatorListenerDefault extends CalculatorBaseListener {
  private Stack<Double> stack = new Stack<>();
  private Double result;

  public Double getResult() {
    return result;
  }

  @Override
  public void exitMultiplyDivide(CalculatorParser.MultiplyDivideContext context) {
    Double number1 = stack.pop();
    Double number2 = stack.pop();
    Double result;

    if (context.operator.getType() == CalculatorParser.MULTIPLY) {
      result = number2 * number1;
    } else if (context.operator.getType() == CalculatorParser.DIVIDE) {
      result = number2 / number1;
    } else {
      throw new AssertionError("Unhandled Token " + context.operator);
    }

    stack.push(result);
  }

  @Override
  public void enterNumber(CalculatorParser.NumberContext context) {
    stack.push(Double.valueOf(context.Number().getText()));
  }

  @Override public void exitCalculator(CalculatorParser.CalculatorContext context) {
    result = stack.peek();
    System.out.println("Result:" + result);
  }

  @Override public void exitAddSubtract(CalculatorParser.AddSubtractContext context) {
    Double number1 = stack.pop();
    Double number2 = stack.pop();
    Double result;

    if (context.operator.getType() == CalculatorParser.ADD) {
      result = number2 + number1;
    } else if (context.operator.getType() == CalculatorParser.SUBTRACT) {
      result = number2 - number1;
    } else {
      throw new AssertionError("Unhandled Token " + context.operator);
    }
    stack.push(result);
  }

  @Override public void exitNegate(CalculatorParser.NegateContext context) {
    Double number1 = stack.pop();
    number1 = -number1;
    stack.push(number1);
  }
}
