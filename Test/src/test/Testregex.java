package test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testregex {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		String regex =  "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*()+_]).{8,12}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher("a#A###a#A###a#A###a#A###a#A##");
		boolean flag = matcher.matches();
		System.out.println("Time taken::" + (System.currentTimeMillis() - start));
		System.out.println("Matches::" + flag);
	}

}
