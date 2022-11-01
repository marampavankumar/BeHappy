package test;

import java.util.Stack;

public class TestSimplifyPath {

	public static void main(String[] args) {
		//String str  = "/a/b/c/d/./../..//e/";
		//String str  = "/a/../../b/../c//.//";
		//String str = "/c/v//b/n/../j/k/../.../i/o/f///";
		String str = "/a/../../b/../c//.//";
		//String str = "/home/";
		printSimplifiedPath(str);

	}

	private static void printSimplifiedPath(String str) {
		Stack<String> stk = new Stack<String>();
		if(str !=null && !str.trim().isEmpty())
		{
			String[] tokens = str.split("/");
			for(String token : tokens)
			{
				//System.out.println(token);
				if(token !=null && !token.trim().isEmpty())
				{
					if(token.equals("."))
						continue;
					if(token.equals(".."))
					{
						if(!stk.isEmpty())
							stk.pop();
						continue;
					}
					stk.push(token);
				}
			}
			if(!stk.isEmpty())
			{
				Stack<String> reverseStk = new Stack<String>();
				while(!stk.isEmpty())
				{
					reverseStk.push(stk.pop());

				}
				StringBuilder s = new StringBuilder();
				while(!reverseStk.isEmpty())
				{
					s.append("/");
					s.append(reverseStk.pop());
				}
				System.out.println(s.toString());
				
			}
		}
		
	}

}
