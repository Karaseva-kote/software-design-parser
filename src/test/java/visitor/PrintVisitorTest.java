package visitor;

import org.junit.Test;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class PrintVisitorTest {
	@Test
	public void testEmptyString() {
		String input = "";
		List<Token> tokens = new Tokenizer(input).getTokens();
		PrintVisitor visitor = new PrintVisitor();
		visitor.visit(tokens);

		assertEquals("", visitor.getResult());
	}

	@Test
	public void testSingleNumber() {
		String input = "3004";
		List<Token> tokens = new Tokenizer(input).getTokens();
		PrintVisitor visitor = new PrintVisitor();
		visitor.visit(tokens);

		assertEquals("NUMBER(3004) ", visitor.getResult());
	}

	@Test
	public void testOperation() {
		String input = "15+227";
		List<Token> tokens = new Tokenizer(input).getTokens();
		PrintVisitor visitor = new PrintVisitor();
		visitor.visit(tokens);

		assertEquals("NUMBER(15) PLUS NUMBER(227) ", visitor.getResult());
	}

	@Test
	public void testFewOperations() {
		String input = "1/15+227*4";
		List<Token> tokens = new Tokenizer(input).getTokens();
		PrintVisitor visitor = new PrintVisitor();
		visitor.visit(tokens);

		assertEquals("NUMBER(1) DIV NUMBER(15) PLUS NUMBER(227) MUL NUMBER(4) ", visitor.getResult());
	}

	@Test
	public void testFewOperationsWithBraces() {
		String input = "(2+2)*313/(4-5)";
		List<Token> tokens = new Tokenizer(input).getTokens();
		PrintVisitor visitor = new PrintVisitor();
		visitor.visit(tokens);

		String left = "OPEN_BRACE NUMBER(2) PLUS NUMBER(2) CLOSE_BRACE";
		String right = "OPEN_BRACE NUMBER(4) MINUS NUMBER(5) CLOSE_BRACE ";
		assertEquals(left + " MUL NUMBER(313) DIV " + right, visitor.getResult());
	}

	@Test
	public void testWhitespaces() {
		String input = " (  \n2   \t+ 2 \r) * 2  \n  ";
		List<Token> tokens = new Tokenizer(input).getTokens();
		PrintVisitor visitor = new PrintVisitor();
		visitor.visit(tokens);

		assertEquals("OPEN_BRACE NUMBER(2) PLUS NUMBER(2) CLOSE_BRACE MUL NUMBER(2) ", visitor.getResult());
	}
}
