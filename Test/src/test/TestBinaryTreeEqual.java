package test;

public class TestBinaryTreeEqual {
	
	private Node root1;
	private Node root2;

	public static void main(String[] args) {
		TestBinaryTreeEqual bt = new TestBinaryTreeEqual();
		bt.formTrees();
		bt.testEqual();

	}

	private void testEqual() {
		System.out.println(testEqual(root1, root2));
		
	}

	private boolean testEqual(Node node1, Node node2) {
		if(node1 == null && node2 == null)
			return true;
		if(node1 == null && node2 != null)
			return false;		
		if(node1 != null && node2 == null)
			return false;
		if(node1.getNodeVal().equals(node2.getNodeVal()) && testEqual(node1.getLeft(), node2.getLeft()) && testEqual(node1.getRight(), node2.getRight()))
			return true;
		return false;
	}

	private void formTrees() {
		formTree1();
		formTree2();
		
		
	}

	private void formTree2() {
		root2 = new Node("M1");
		root2.setLeft(new Node("M2"));
		root2.setRight(new Node("M3"));
		Node M2 = root2.getLeft();
		Node M3 = root2.getRight();
		M2.setLeft(new Node("M4"));
		M2.setRight(new Node("M5"));
		Node M5 = M2.getRight();
		M5.setLeft(new Node("M8"));
		M3.setLeft(new Node("M6"));
		M3.setRight(new Node("M7"));
	}

	private void formTree1() {
		root1 = new Node("M1");
		root1.setLeft(new Node("M2"));
		root1.setRight(new Node("M3"));
		Node M2 = root1.getLeft();
		Node M3 = root1.getRight();
		M2.setLeft(new Node("M4"));
		M2.setRight(new Node("M5"));
		Node M5 = M2.getRight();
		M5.setLeft(new Node("M8"));
		M3.setLeft(new Node("M6"));
		M3.setRight(new Node("M7"));
	}
	

}

class Node{
	private String nodeVal;
	
	public Node(String nodeVal) {
		this.nodeVal = nodeVal;
	}
	public String getNodeVal() {
		return nodeVal;
	}
	public void setNodeVal(String nodeVal) {
		this.nodeVal = nodeVal;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	private Node left;
	private Node right;
}

