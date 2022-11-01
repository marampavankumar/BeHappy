package test;

public class TestMethodLocal {

	public static void main(String[] args) {
		new TestMethodLocal().test();

	}

	private void test() {
		Integer i = 6;
		act(i);
		System.out.println(i);
		i=null;

		
	}

	private void act(Integer i) {
		System.out.println(i);
		System.out.println(i);
		
	}

}
