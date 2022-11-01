package test;

public class TestBST {
	
	private Node root;

	public static void main(String[] args) {
		TestBST bst = new TestBST();
		bst.insert(10);
		bst.insert(3);
		bst.insert(20);
		bst.insert(1);
		bst.insert(7);
		bst.insert(15);
		bst.insert(5);
		bst.insert(25);
		bst.insert(12);
		bst.insert(17);
		bst.insert(22);
		bst.insert(27);
		bst.insert(11);
		bst.inOrderTraversal();
		System.out.println(bst.inOrderSuccessor(bst.root));
		

	}
	
	private void inOrderTraversal() {
		inOrderTraversal(root);
		
	}
	
	private int inOrderSuccessor(Node node)
	{
		System.out.println("inorder successor of " + node.value);
		Node current = node.right;
		Node parent = null;
		while(current != null)
		{
			parent = current;
			current = current.left;
		}
		
		return parent !=null ? parent.value : null;
	}


	private void inOrderTraversal(Node node) {
		//return if null
		if(node == null)
			return;
		//left
		inOrderTraversal(node.left);
		//node
		System.out.println(node.value);
		//right
		inOrderTraversal(node.right);
		
	}

		private void insert(int i) {
		if(root ==null)
		{
			root = new Node(i);
			return;
		}
		Node current = root;
		Node parent;
		while(true)
		{
			parent = current;
			if(current.value >=i)
			{
				current = current.left;
				if(current == null)
				{
					current = new Node(i);
					parent.left = current;
					break;
				}
				 
			}
			else
			{
				current = current.right;
				if(current == null)
				{
					current = new Node(i);
					parent.right = current;
					break;
				}
				
			}
		}
	}

		public static class Node{
			
			public Node(int val) {
				this.value = val;
			}
			int value;
			Node left;
			Node right;
		}

}
