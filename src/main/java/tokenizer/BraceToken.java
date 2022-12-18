package tokenizer;

import visitor.TokenVisitor;

public abstract class BraceToken implements Token {
	public void visit(TokenVisitor visitor) {
		visitor.visit(this);
	}
}

