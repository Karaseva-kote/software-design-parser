package visitor;

import org.junit.Test;
import tokenizer.Token;
import tokenizer.Tokenizer;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CalcVisitorTest {
	@Test
	public void testEmptyString() {
		String input = "";
		List<Token> tokens = new Tokenizer(input).getTokens();
		CalcVisitor visitor = new CalcVisitor();
		visitor.visit(tokens);

		assertEquals(0, visitor.getResult());
	}

	@Test
	public void testSingleNumber() {
		String input = "3004";
		List<Token> tokens = new Tokenizer(input).getTokens();
		CalcVisitor visitor = new CalcVisitor();
		visitor.visit(tokens);

		assertEquals(3004, visitor.getResult());
	}

	@Test
	public void testOperation() {
		String input = "15 227 +";
		List<Token> tokens = new Tokenizer(input).getTokens();
		CalcVisitor visitor = new CalcVisitor();
		visitor.visit(tokens);

		assertEquals(242, visitor.getResult());
	}

	@Test
	public void testFewOperations() {
		String input = "1 15 / 227 4 * +";
		List<Token> tokens = new Tokenizer(input).getTokens();
		CalcVisitor visitor = new CalcVisitor();
		visitor.visit(tokens);

		assertEquals(908, visitor.getResult());
	}

	@Test
	public void testFewOperationsWithBraces() {
		String input = "2 2 + 313 4 5 - / *";
		List<Token> tokens = new Tokenizer(input).getTokens();
		CalcVisitor visitor = new CalcVisitor();
		visitor.visit(tokens);

		assertEquals(-1252, visitor.getResult());
	}

	@Test
	public void testWhitespaces() {
		String input = "2 2 + 2 *";
		List<Token> tokens = new Tokenizer(input).getTokens();
		CalcVisitor visitor = new CalcVisitor();
		visitor.visit(tokens);

		assertEquals(8, visitor.getResult());
	}
}
