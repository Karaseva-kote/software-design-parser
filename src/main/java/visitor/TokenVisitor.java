package visitor;

import tokenizer.BraceToken;
import tokenizer.NumberToken;
import tokenizer.OperationToken;
import tokenizer.Token;

import java.util.List;

public interface TokenVisitor {
	void visit(NumberToken number);
	void visit(BraceToken brace);
	void visit(OperationToken operation);
	List<Token> visit(List<Token> tokens);
}
