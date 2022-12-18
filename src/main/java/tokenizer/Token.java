package tokenizer;

import visitor.TokenVisitor;

public interface Token {
	void visit(TokenVisitor visitor);
}
