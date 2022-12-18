package visitor;

import tokenizer.*;

import java.util.List;

public class PrintVisitor implements TokenVisitor {
	private final StringBuilder stringBuilder = new StringBuilder();
	
	@Override
	public void visit(NumberToken number) {
		stringBuilder.append("NUMBER(").append(number.number).append(") ");
	}

	@Override
	public void visit(BraceToken brace) {
		if (brace instanceof OpenBrace) {
			stringBuilder.append("OPEN_BRACE");
		}
		if (brace instanceof CloseBrace) {
			stringBuilder.append("CLOSE_BRACE");
		}
		stringBuilder.append(" ");
	}

	@Override
	public void visit(OperationToken operation) {
		if (operation instanceof Plus) {
			stringBuilder.append("PLUS");
		}
		if (operation instanceof Minus) {
			stringBuilder.append("MINUS");
		}
		if (operation instanceof Multiplication) {
			stringBuilder.append("MUL");
		}
		if (operation instanceof Division) {
			stringBuilder.append("DIV");
		}
		stringBuilder.append(" ");
	}

	@Override
	public List<Token> visit(List<Token> tokens) {
		tokens.forEach(token -> token.visit(this));
		return null;
	}

	public String getResult() {
		return stringBuilder.toString();
	}
}
