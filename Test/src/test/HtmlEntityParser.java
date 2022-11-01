package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class HtmlEntityParser {

	public static void main(String[] args) {
		System.out.println(entityParser("leetcode.com&frasl;&gt&gt;probl&abcs;emset&amp;bcdefghijkl"));
		System.out.println(entityParser("&gt&gt;p"));

	}

    public static String entityParser(String text) {

        Map<String, Character> entityMap = new HashMap<>();
        entityMap.put(";touq&", '"');
        entityMap.put(";sopa&", '\'');
        entityMap.put(";pma&", '&');
        entityMap.put(";tg&", '>');
        entityMap.put(";tl&", '<');
        entityMap.put(";lsarf&", '/');

        StringBuffer buffer = new StringBuffer();
        boolean push = false;
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i  < text.length(); i++) {
            if (push && text.charAt(i) == ';') {
                StringBuffer tempBuffer = new StringBuffer(";");
                while (!stack.isEmpty()) {
                    tempBuffer.append(stack.pop());
                }
                if(entityMap.containsKey(tempBuffer.toString())) {
                    buffer.append(entityMap.get(tempBuffer.toString()));

                } else {
                    buffer.append(tempBuffer.reverse().toString());
                }

                push = false;
                continue;
            } 

            if(text.charAt(i) == '&') {
                StringBuffer tempBuffer = new StringBuffer("");
                while (!stack.isEmpty()) {
                    tempBuffer.append(stack.pop());
                }
                if(tempBuffer.length() > 0)
                	buffer.append(tempBuffer.reverse().toString());

                stack.push(text.charAt(i));
                push = true;
                continue;
            }
            
           if (push) {
                stack.push(text.charAt(i));
            }            
           else {
                buffer.append(text.charAt(i));
            }
        }
        if(!stack.isEmpty())
        {
            StringBuffer tempBuffer = new StringBuffer();
            while (!stack.isEmpty()) {
                tempBuffer.append(stack.pop());
            }
                buffer.append(tempBuffer.reverse().toString());
        	
        }
        return buffer.toString();
    }
}
