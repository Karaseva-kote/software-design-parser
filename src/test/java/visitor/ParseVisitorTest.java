package visitor;

import org.junit.Test;
import tokenizer.*;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class ParseVisitorTest {
	@Test
	public void testEmpty() {
		String input = "";
		List<Token> tokens = new Tokenizer(input).getTokens();
		List<Token> postfixNotation = new ParseVisitor().visit(tokens);

		assertEquals(List.of(), postfixNotation);
	}

	@Test
	public void testNumber() {
		String input = "457";
		List<Token> tokens = new Tokenizer(input).getTokens();
		List<Token> postfixNotation = new ParseVisitor().visit(tokens);

		assertEquals(
				List.of(
						new NumberToken(457)
				),
				postfixNotation
		);
	}

	@Test
	public void testOperation() {
		String input = "8*7";
		List<Token> tokens = new Tokenizer(input).getTokens();
		List<Token> postfixNotation = new ParseVisitor().visit(tokens);

		assertEquals(
				List.of(
						new NumberToken(8),
						new NumberToken(7),
						new Multiplication()
						),
				postfixNotation
		);
	}

	@Test
	public void testOperationsWithDifferentPriority() {
		String input = "2+2*2";
		List<Token> tokens = new Tokenizer(input).getTokens();
		List<Token> postfixNotation = new ParseVisitor().visit(tokens);

		assertEquals(
				List.of(
						new NumberToken(2),
						new NumberToken(2),
						new NumberToken(2),
						new Multiplication(),
						new Plus()
				),
				postfixNotation
		);
	}

	@Test
	public void testOperationsWithDifferentPriority2() {
		String input = "2*2+2";
		List<Token> tokens = new Tokenizer(input).getTokens();
		List<Token> postfixNotation = new ParseVisitor().visit(tokens);

		assertEquals(
				List.of(
						new NumberToken(2),
						new NumberToken(2),
						new Multiplication(),
						new NumberToken(2),
						new Plus()
				),
				postfixNotation
		);
	}

	@Test
	public void testBraces() {
		String input = "(3+3)*3";
		List<Token> tokens = new Tokenizer(input).getTokens();
		List<Token> postfixNotation = new ParseVisitor().visit(tokens);

		assertEquals(
				List.of(
						new NumberToken(3),
						new NumberToken(3),
						new Plus(),
						new NumberToken(3),
						new Multiplication()
				),
				postfixNotation
		);
	}

	@Test
	public void testBigSequence() {
		String input = "3+4*2/(1-5)-12";
		List<Token> tokens = new Tokenizer(input).getTokens();
		List<Token> postfixNotation = new ParseVisitor().visit(tokens);

		assertEquals(
				List.of(
						new NumberToken(3),
						new NumberToken(4),
						new NumberToken(2),
						new Multiplication(),
						new NumberToken(1),
						new NumberToken(5),
						new Minus(),
						new Division(),
						new Plus(),
						new NumberToken(12),
						new Minus()
				),
				postfixNotation
		);
	}


	@Test
	public void testNoOpenBrace() {
		String input = "4+)4";
		List<Token> tokens = new Tokenizer(input).getTokens();

		try {
			new ParseVisitor().visit(tokens);
		} catch (IllegalStateException e) {
			return;
		}
		assert(false);
	}

	@Test
	public void testNoCloseBrace() {
		String input = "5-(4+3";
		List<Token> tokens = new Tokenizer(input).getTokens();

		try {
			new ParseVisitor().visit(tokens);
		} catch (IllegalStateException e) {
			return;
		}
		assert(false);
	}
}
