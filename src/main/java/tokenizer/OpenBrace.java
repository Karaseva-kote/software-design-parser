package tokenizer;

public class OpenBrace extends BraceToken {
	public OpenBrace() {
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof OpenBrace;
	}
}
