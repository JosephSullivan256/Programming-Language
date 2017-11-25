package com.youknowjoejoe.gmail.interpreter1.lexer;

import java.util.ArrayList;
import java.util.List;

import com.youknowjoejoe.gmail.interpreter1.Token;
import com.youknowjoejoe.gmail.interpreter1.util.Pair;

public class LexerBuilder {
	
	private List<Pair<String,String>> tokenTypes;
	
	public LexerBuilder() {
		this.tokenTypes = new ArrayList<Pair<String,String>>();
	}
	
	public LexerBuilder with(String tokenType, String regex) {
		tokenTypes.add(new Pair<String,String>(tokenType, regex));
		return this;
	}
	
	private static class InnerLexer implements Lexer{
		
		private List<Pair<String,String>> tokenTypes;
		
		public InnerLexer(List<Pair<String,String>> tokenTypes) {
			this.tokenTypes = tokenTypes;
		}
		
		@Override
		public List<Token> lex(String input) {
			List<Token> tokens = new ArrayList<Token>();
			
			int xi = 0;
			int xf = 1;
			int len = input.length();
			
			String previousType = "";
			
			while(xf <= len) {
				Pair<Boolean,String> temp = match(input.substring(xi,xf));
				if(!temp.getA()) {
					if(!previousType.equals("")) {
						tokens.add(new Token(previousType,input.substring(xi,xf-1)));
					}
					xi = xf;
					xf++;
					previousType = "";
				} else {
					previousType = temp.getB();
					xf++;
				}
			}
			String substr = input.substring(xi,xf-1);
			Pair<Boolean,String> finalTemp = match(substr);
			if(finalTemp.getA()) {
				tokens.add(new Token(finalTemp.getB(),substr));
			}
			
			return tokens;
		}
		
		private Pair<Boolean,String> match(String str) {
			for(Pair<String,String> entry : tokenTypes) {
				if(str.matches(entry.getB())) {
					return new Pair<Boolean,String>(true,entry.getA());
				}
			}
			return new Pair<Boolean,String>(false,"");
		}
		
	}
	
	public Lexer build() {
		return new InnerLexer(tokenTypes);
	}
}
