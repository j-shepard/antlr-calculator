import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
public class Run {
  public static void main(String... args) throws Exception {
    walk("(1.2+2.+.3)*44");
    walk("1+2*3");
    walk("2 * -3.1");
    walk("1.5 - 0.5");
    walk("a + 1");
  }

  public static void walk(String expression) {
    ANTLRInputStream input = new ANTLRInputStream(expression);
    CalculatorLexer lexer = new CalculatorLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    CalculatorParser parser = new CalculatorParser(tokens);
    ParseTree tree = parser.calculator();
    ParseTreeWalker walker = new ParseTreeWalker();
    walker.walk(new CalculatorListenerDefault(), tree);
  }
}
