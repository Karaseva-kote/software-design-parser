package tokenizer;

public class Minus extends OperationToken {
	public Minus() {
		super(1);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Minus;
	}
}
