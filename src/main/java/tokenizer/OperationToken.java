package tokenizer;

import visitor.TokenVisitor;

public abstract class OperationToken implements Token {
	public int priority;

	protected OperationToken(int priority) {
		this.priority = priority;
	}

	public void visit(TokenVisitor visitor) {
		visitor.visit(this);
	}
}

