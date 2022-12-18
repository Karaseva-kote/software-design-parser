package tokenizer;

public abstract class State {
	protected final Tokenizer tokenizer;
	protected int position;

	protected State(Tokenizer tokenizer, int position) {
		this.tokenizer = tokenizer;
		this.position = position;
	}

	public abstract void readToken();

	public State nextState() {
		skipWhitespace();
		if (position == tokenizer.input.length()) {
			return new EndState(tokenizer, position);
		}
		char curChar = tokenizer.input.charAt(position);
		if (isBrace(curChar)) {
			return new ReadBraceState(tokenizer, position);
		}
		if (isOperation(curChar)) {
			return new ReadOperationState(tokenizer, position);
		}
		if (Character.isDigit(curChar)) {
			return new ReadNumberState(tokenizer, position);
		}
		throw new IllegalStateException("unknown symbol: '" + curChar + "' on position: " + position);
	}

	private void skipWhitespace() {
		while (position < tokenizer.input.length() && Character.isWhitespace(tokenizer.input.charAt(position))) {
			position++;
		}
	}

	private boolean isBrace(char ch) {
		return ch == '(' || ch == ')';
	}

	private boolean isOperation(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}
}

class StartState extends State {
	StartState(Tokenizer tokenizer, int position) {
		super(tokenizer, position);
	}

	public void readToken() {
		tokenizer.state = nextState();
	}
}

class ReadBraceState extends State {
	protected ReadBraceState(Tokenizer tokenizer, int position) {
		super(tokenizer, position);
	}

	public void readToken() {
		char curChar = tokenizer.input.charAt(position);
		if (curChar == '(') {
			tokenizer.tokens.add(new OpenBrace());
		}
		if (curChar == ')') {
			tokenizer.tokens.add(new CloseBrace());
		}
		position++;
		tokenizer.state = nextState();
	}
}

class ReadOperationState extends State {
	protected ReadOperationState(Tokenizer tokenizer, int position) {
		super(tokenizer, position);
	}

	public void readToken() {
		char curChar = tokenizer.input.charAt(position);
		switch (curChar) {
			case '+' -> tokenizer.tokens.add(new Plus());
			case '-' -> tokenizer.tokens.add(new Minus());
			case '*' -> tokenizer.tokens.add(new Multiplication());
			case '/' -> tokenizer.tokens.add(new Division());
		}
		position++;
		tokenizer.state = nextState();
	}
}

class ReadNumberState extends State {
	protected ReadNumberState(Tokenizer tokenizer, int position) {
		super(tokenizer, position);
	}

	public void readToken() {
		StringBuilder buildNumber = new StringBuilder();
		char curChar = tokenizer.input.charAt(position);
		while (Character.isDigit(curChar)) {
			buildNumber.append(curChar);
			position++;
			if (position == tokenizer.input.length()) {
				break;
			}
			curChar = tokenizer.input.charAt(position);
		}
		tokenizer.tokens.add(new NumberToken(Integer.parseInt(buildNumber.toString())));
		tokenizer.state = nextState();
	}
}

class EndState extends State {
	EndState(Tokenizer tokenizer, int position) {
		super(tokenizer, position);
	}

	public void readToken() {
		throw new IllegalStateException("End State can not read next token");
	}
}
