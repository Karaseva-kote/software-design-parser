package visitor;

import tokenizer.*;

import java.util.*;

public class ParseVisitor implements TokenVisitor {
	private final List<Token> postfixTokens = new ArrayList<>();
	private final Stack<Token> stack = new Stack<>();

	@Override
	public void visit(NumberToken number) {
		postfixTokens.add(number);
	}

	@Override
	public void visit(BraceToken brace) {
		if (brace instanceof OpenBrace) {
			stack.push(brace);
			return;
		}
		while (true) {
			if (stack.empty()) {
				throw new IllegalStateException("unexpected close brace");
			}
			Token token = stack.pop();
			if (token instanceof OpenBrace) {
				break;
			}
			postfixTokens.add(token);
		}
	}

	@Override
	public void visit(OperationToken operation) {
		while (!stack.empty()) {
			Token token = stack.peek();
			if (token instanceof OpenBrace) {
				break;
			}
			if (token instanceof OperationToken) {
				OperationToken operationToken = (OperationToken) token;
				if (operationToken.priority < operation.priority) {
					break;
				}
			}
			stack.pop();
			postfixTokens.add(token);
		}
		stack.push(operation);
	}

	@Override
	public List<Token> visit(List<Token> tokens) {
		tokens.forEach(token -> token.visit(this));
		while (!stack.empty()) {
			Token token = stack.pop();
			if (token instanceof OpenBrace) {
				throw new IllegalStateException("expression have unclosed opening brace");
			}
			postfixTokens.add(token);
		}
		return postfixTokens;
	}
}
