package com.youknowjoejoe.gmail.interpreter1.parser;

public class ParserBuilder {
	
	public ParserBuilder() {
		
	}
	
	private static class InnerParser implements Parser {
		
		public InnerParser() {
			
		}
	}
	
	public Parser build() {
		return new InnerParser();
	}
}
