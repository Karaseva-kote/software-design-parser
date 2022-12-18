import tokenizer.Token;
import tokenizer.Tokenizer;
import visitor.CalcVisitor;
import visitor.ParseVisitor;
import visitor.PrintVisitor;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		try {
			List<Token> parsedTokens = new ParseVisitor().visit(new Tokenizer(input).getTokens());
			PrintVisitor printVisitor = new PrintVisitor();
			printVisitor.visit(parsedTokens);
			System.out.println(printVisitor.getResult());
			CalcVisitor calcVisitor = new CalcVisitor();
			calcVisitor.visit(parsedTokens);
			System.out.println("result: " + calcVisitor.getResult());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
