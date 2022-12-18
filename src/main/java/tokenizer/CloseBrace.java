package tokenizer;

public class CloseBrace extends BraceToken {
	public CloseBrace() {
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof CloseBrace;
	}
}
