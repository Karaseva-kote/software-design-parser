package tokenizer;

public class Division extends OperationToken {
	public Division() {
		super(2);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Division;
	}
}
