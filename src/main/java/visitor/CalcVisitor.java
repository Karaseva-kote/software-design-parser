package visitor;

import tokenizer.*;

import java.util.List;
import java.util.Stack;
import java.util.function.BinaryOperator;

public class CalcVisitor implements TokenVisitor {
	private final Stack<Integer> stack = new Stack<>();

	@Override
	public void visit(NumberToken number) {
		stack.push(number.number);
	}

	@Override
	public void visit(BraceToken brace) {
		throw new IllegalStateException("unexpected brace during calculating expression");
	}

	@Override
	public void visit(OperationToken operation) {
		if (operation instanceof Plus) {
			calcBinaryOperation(Integer::sum);
		}
		if (operation instanceof Minus) {
			calcBinaryOperation((left, right) -> left - right);
		}
		if (operation instanceof Multiplication) {
			calcBinaryOperation((left, right) -> left * right);
		}
		if (operation instanceof Division) {
			calcBinaryOperation((left, right) -> left / right);
		}
	}

	private void calcBinaryOperation(BinaryOperator<Integer> operator) {
		if (stack.empty()) {
			throw new IllegalStateException("expect number");
		}
		int right = stack.pop();
		if (stack.empty()) {
			throw new IllegalStateException("expect number");
		}
		int left = stack.pop();
		stack.push(operator.apply(left, right));
	}

	@Override
	public List<Token> visit(List<Token> tokens) {
		stack.clear();
		tokens.forEach(token -> token.visit(this));
		if (stack.size() > 1) {
			throw new IllegalStateException("unexpected state after calculating");
		}
		return null;
	}

	public int getResult() {
		if (stack.empty()) {
			return 0;
		}
		return stack.pop();
	}
}
