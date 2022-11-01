package test;

public class TestTowersOfHanoi {

	public static void main(String[] args) {
		int n = 4;
		char source ='A';
		char intermediary ='B';
		char destination ='C';
		move(n,source, destination,intermediary);

	}

	private static void move(int n, char source, char destination, char intermediary) {
		if(n==1)
		{
			System.out.println("Moving disk 1 from " + source + " to " + destination);
			return;
		}
		move(n-1,source, intermediary, destination);
        System.out.println("Moving disk " + n + " from " +  source + " to " + destination);
		move(n-1,intermediary, destination, source);
		
	}

}
