package com.youknowjoejoe.gmail.interpreter1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.youknowjoejoe.gmail.interpreter1.lexer.Lexer;
import com.youknowjoejoe.gmail.interpreter1.lexer.LexerBuilder;
import com.youknowjoejoe.gmail.interpreter1.parser.Parser;
import com.youknowjoejoe.gmail.interpreter1.parser.ParserBuilder;

public class Main {
	
	public static void main(String[] args) throws IOException {
		Lexer lexer = new LexerBuilder()
				.with("keyword", "for|new|bool|do|if|break|double|else|struct|return|int|char|void|const|float|while")
				.with("literal", "true|false|((\".*\")|(\".*))|((\\d+(\\.\\d+)?)|(\\d*\\.\\d+))") //\".*\"
				.with("identifier", "[a-zA-Z0-9_\\-+*\\/%]+")
				.with("separator", "[\\{\\}\\(\\),\\.;]")
				.with("operator", "==|&&|\\|\\||(:=)|[!=<>&|^]")
				.with("comment", "\\/{2}(.*)([\\n\\r]|(\\r\\n))")
				.build();
		
		Parser parser = new ParserBuilder().build();
		
		String code = readFile(new File("sample.txt"));
		System.out.println(code);
		System.out.println(lexer.lex(code));
	}
	
	public static String readFile(File f) throws IOException {
		String str = "";
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		String line = "";
		while(line!=null) {
			str+=line+"\n";
			line = reader.readLine();
		}
		reader.close();
		
		return str;
	}
}
