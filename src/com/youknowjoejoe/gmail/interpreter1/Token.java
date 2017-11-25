package com.youknowjoejoe.gmail.interpreter1;

public class Token {
	private String tokenType;
	private String token;
	
	public Token(String tokenType, String token) {
		this.tokenType = tokenType;
		this.token = token;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
	public String getToken() {
		return token;
	}
	
	@Override
	public String toString() {
		return "("+tokenType+" "+token+")";
	}
}
