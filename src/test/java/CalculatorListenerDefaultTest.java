
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorListenerDefaultTest {
  private static final double EPSILON = 0.0000000001d;

  @Test
  public void testAdd() {
    assertEquals(2.0d, getResult("1.5 + 0.5"), EPSILON);
  }

  @Test
  public void testSubtract() {
    assertEquals(1.0d, getResult("1.5 - 0.5"), EPSILON);
  }

  @Test
  public void testMultiply() {
    assertEquals(6.0d, getResult("2 * 3"), EPSILON);
  }

  @Test
  public void testDivide() {
    assertEquals(3.0d, getResult("9 / 3"), EPSILON);
  }

  @Test
  public void testParenthesis() {
    assertEquals(4.5d, getResult("3 * (1 + .5)"), EPSILON);
  }

  @Test
  public void testNegate() {
    assertEquals(1.5d, getResult("3 * (1 + -.5)"), EPSILON);
  }

  @Test
  public void testTwoParenthesis() {
    assertEquals(4.5d, getResult("3 * ((9.0 - 7) / 2 + .5)"), EPSILON);
  }

  private Double getResult(String expression) {
    ANTLRInputStream input = new ANTLRInputStream(expression);
    CalculatorLexer lexer = new CalculatorLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    CalculatorParser parser = new CalculatorParser(tokens);
    ParseTree tree = parser.calculator();
    ParseTreeWalker walker = new ParseTreeWalker();
    CalculatorListenerDefault listener = new CalculatorListenerDefault();
    walker.walk(listener, tree);
    return listener.getResult();
  }
}
