package com.youknowjoejoe.gmail.interpreter1.lexer;

import java.util.List;

import com.youknowjoejoe.gmail.interpreter1.Token;

public interface Lexer {
	public List<Token> lex(String input);
}
