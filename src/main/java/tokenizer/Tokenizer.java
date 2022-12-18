package tokenizer;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
	public final String input;
	public final List<Token> tokens = new ArrayList<Token>();
	public State state;

	public Tokenizer(String input) {
		this.input = input;
		this.state = new StartState(this, 0);
	}

	public List<Token> getTokens() {
		while (true) {
			state.readToken();
			if (state.getClass() == EndState.class) {
				return tokens;
			}
		}
	}
}
