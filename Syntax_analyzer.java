import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Syntax_analyzer {
		
		Stack<Token>tokenStack = new Stack<>();
		Token token ;
		String totype;
		TokenType tokenType ;
		String source;
		String[] sourceLine;
		int tokenNum = 0;
		
		Syntax_analyzer(String source){
			this.source = source;
			this.sourceLine = this.source.split("\n");
		}
	
		//There is Token class to make Token instance
		//This class has tokentype , line , position, value(content) value
		
		
		
		
		void synAnalyzer() {
			getNextToken();
			tokenStack.push(this.token);
		}
		
		Token getNextToken() {
			String[] token = this.sourceLine[this.tokenNum].split("\t");
			this.tokenNum++;
			switch(token[0])
			{
			case "\u0000":
				this.tokenType = TokenType.End_of_input;
			case "comp":
				this.tokenType = TokenType.comp;
			case "multdiv":
				this.tokenType = TokenType.multdiv;
			case "addsub":
				this.tokenType = TokenType.addsub;
			case "vtype":
				this.tokenType = TokenType.vtype;
			case "semi":
				this.tokenType = TokenType.Semi;
			case "comma":
				this.tokenType = TokenType.comma;
			case "id":
				this.tokenType = TokenType.id;
			case "num":
				this.tokenType = TokenType.num;
			case "Float":
				this.tokenType = TokenType.Float;
			case "literal":
				this.tokenType = TokenType.literal;
			case "assign":
				this.tokenType = TokenType.assign;
			case "If":
				this.tokenType = TokenType.If;
			case "Else":
				this.tokenType = TokenType.Else;
			case "While":
				this.tokenType = TokenType.While;
			case "For":
				this.tokenType = TokenType.For;
			case "Return":
				this.tokenType = TokenType.Return;
			case "Lparen":
				this.tokenType = TokenType.Lparen;
			case "Rparen":
				this.tokenType = TokenType.Rparen;
			case "Lbrace":
				this.tokenType = TokenType.Lbrace;
			case "Rbrace":
				this.tokenType = TokenType.Rbrace;
			default:
				error(1,"There is undeclaration Tokens in:",Integer.parseInt(token[1]),Integer.parseInt(token[2]));	
			}
		return this.token = new Token(this.tokenType,token[3],Integer.parseInt(token[1]),Integer.parseInt(token[2]));	
		}
		
		public static void main (String args[]) {
			if (args.length > 0) {
	            try {
	                File file = new File(args[0]);		//read file//
	                Scanner scan = new Scanner(file);
	                String source = "";
	                while (scan.hasNext()) {
	                    source += scan.nextLine() + "\n";	//make source for making token comfortably//
	                }
	                Syntax_analyzer sa = new Syntax_analyzer(source);
	                sa.synAnalyzer();
	                scan.close();
	            } catch(FileNotFoundException e) {
	                error(-1, "Exception: " + e.getMessage(),-1,-1);	//print error massage FileNotFound Exception//
	            }
	        } 
	        else {
	            error(-1, "There is no file",-1,-1); //print error massage: no input file//
	        }
		}
		
	
		
		
		static void error(int check, String str, int line, int position) {
			if (check > 0) {
	            System.out.printf("%s\n in line:%d position:%d", str,line,position);
	            System.exit(1);
	        } else {
	            System.out.println(str);
	            System.exit(1);
	        }
		}
}

class Token {
	public TokenType tokentype;
	public int line;
	public int position;
	public String value;
	
	Token (TokenType token,String value, int line, int position)
	{
		this.tokentype = token;
		this.value = value;
		this.line = line;
		this.position = position;
	}
	
	public String toString()
	{
		String result = String.format("%-15s\t%-4d\t%-8d\t%s\n", this.tokentype,this.line,this.position,this.value);
		
		return result;
	}
	
	
}

//To take control of Token's type, use enum class
//change some of Tokentype name because of Term project#2 /05.30

enum TokenType{
	End_of_input,  multdiv, addsub, vtype,
     comp, assign, Op_and, Op_or,Op_leftbit,Op_Rightbit,Op_bitwiseOr,Op_bitwiseAnd,If,
    Else, While, For, Return,Lparen, Rparen,
    Lbrace, Rbrace, Semi, Colon, comma, id, num, Float, literal,  True, False, Linefeed, Tab

}

class Terminal extends Token{

	Terminal(TokenType token, String value, int line, int position) {
		super(token, value, line, position);
	}
	
}
