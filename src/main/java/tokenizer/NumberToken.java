package tokenizer;

import visitor.TokenVisitor;

import java.util.Objects;

public class NumberToken implements Token {
	public final int number;

	public NumberToken(int number) {
		this.number = number;
	}

	public void visit(TokenVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NumberToken that = (NumberToken) o;
		return number == that.number;
	}

	@Override
	public int hashCode() {
		return Objects.hash(number);
	}
}
