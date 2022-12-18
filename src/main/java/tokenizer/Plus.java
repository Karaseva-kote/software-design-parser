package tokenizer;

public class Plus extends OperationToken {
	public Plus() {
		super(1);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Plus;
	}
}
