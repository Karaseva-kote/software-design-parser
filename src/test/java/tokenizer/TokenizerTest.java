package tokenizer;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TokenizerTest {
	@Test
	public void testEmptyString() {
		String input = "";
		Tokenizer tokenizer = new Tokenizer(input);

		assertEquals(List.of(), tokenizer.getTokens());
	}

	@Test
	public void testSingleNumber() {
		String input = "34";
		Tokenizer tokenizer = new Tokenizer(input);

		assertEquals(
				List.of(new NumberToken(34)),
				tokenizer.getTokens()
		);
	}

	@Test
	public void testOperation() {
		String input = "14 + 47";
		Tokenizer tokenizer = new Tokenizer(input);

		assertEquals(
				List.of(
						new NumberToken(14),
						new Plus(),
						new NumberToken(47)
						),
				tokenizer.getTokens()
		);
	}

	@Test
	public void testFewOperations() {
		String input = "2 / 15 + 27 * 4";
		Tokenizer tokenizer = new Tokenizer(input);

		assertEquals(
				List.of(
						new NumberToken(2),
						new Division(),
						new NumberToken(15),
						new Plus(),
						new NumberToken(27),
						new Multiplication(),
						new NumberToken(4)
						),
				tokenizer.getTokens()
		);
	}

	@Test
	public void testFewOperationsWithBraces() {
		String input = "(2 * 3) / 33 * (3 - 15)";
		Tokenizer tokenizer = new Tokenizer(input);

		assertEquals(
				List.of(
						new OpenBrace(),
						new NumberToken(2),
						new Multiplication(),
						new NumberToken(3),
						new CloseBrace(),
						new Division(),
						new NumberToken(33),
						new Multiplication(),
						new OpenBrace(),
						new NumberToken(3),
						new Minus(),
						new NumberToken(15),
						new CloseBrace()
						),
				tokenizer.getTokens()
		);
	}

	@Test
	public void testWhitespaces() {
		String input = " (     \n2   \t+ 2 \r) * 2  \n  ";
		Tokenizer tokenizer = new Tokenizer(input);

		assertEquals(
				List.of(
						new OpenBrace(),
						new NumberToken(2),
						new Plus(),
						new NumberToken(2),
						new CloseBrace(),
						new Multiplication(),
						new NumberToken(2)
						),
				tokenizer.getTokens()
		);
	}

	@Test
	public void testUnexpectedSymbol() {
		String input = "meow";
		Tokenizer tokenizer = new Tokenizer(input);

		try {
			tokenizer.getTokens();
		} catch (IllegalStateException e) {
			return;
		}
		assert(false);
	}
}
