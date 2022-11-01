package test;

import java.util.Arrays;
import java.util.Stack;

public class TestDailyTemp {

	public static void main(String[] args) {
		int [] T = {73, 74, 75, 71, 69, 72, 76, 73};
		System.out.println(Arrays.toString(dailyTemperatures(T)));

	}
	
	public static int[] dailyTemperatures(int[] T) {

        Stack<Integer> stack = new Stack<>();

        int[] res = new int[T.length];

        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]){
                int temp =  stack.pop();
                res[temp] = i - temp;
            }
            stack.push(i);
        }

        return res;
    }	

}
