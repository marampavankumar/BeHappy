package test;

import java.util.Stack;

public class SimplifyPath {

	public static void main(String[] args) {
		simplifyPath("/a/b//c/d/e/f/../g/../../h/i/j/../../k/./");

	}

	private static void simplifyPath(String path) {
		String[] parts = path.split("/");
		Stack<String> stk = new Stack<String>();
		for(int i=0; i<parts.length; i++)
		{
			if(parts[i].equals("") || parts[i].equals(".") || parts[i].equals("/"))
				continue;
			if(parts[i].equals("..")) 
			{
				if(!stk.isEmpty())
					stk.pop();
				continue;
			}
			stk.push(parts[i]);
		}
		
		Stack<String> reverseStk = new Stack<String>();
		while(!stk.isEmpty())
			reverseStk.push(stk.pop());
		
		StringBuilder output = new StringBuilder("/");
		while(!reverseStk.isEmpty())
		{
			output.append(reverseStk.pop());
			output.append("/");
		}
		if(output.length() > 1)
			output.deleteCharAt(output.length() -1 );
		
		System.out.println(output.toString());
	}

}
