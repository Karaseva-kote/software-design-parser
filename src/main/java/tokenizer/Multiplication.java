package tokenizer;

public class Multiplication extends OperationToken {
	public Multiplication() {
		super(2);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Multiplication;
	}
}
