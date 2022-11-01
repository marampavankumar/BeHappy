package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TestBuildOrder {
	
	ArrayList<Node> nodes = new ArrayList<>();

	public static void main(String[] args) {
		TestBuildOrder buildOrder = new TestBuildOrder();
		buildOrder.buildTree("M1", Arrays.asList("M2", "M3", "M4"));
		buildOrder.buildTree("M2", Arrays.asList("M11", "M12", "M8"));
		buildOrder.buildTree("M3", Arrays.asList("M7", "M8", "M2"));
		buildOrder.buildTree("M4", Arrays.asList("M5", "M6", "M3"));
		buildOrder.buildTree("M12", Arrays.asList("M8"));

		buildOrder.printSequenceOrder();

	}

	
  class Node
  {
	  private String value;
	  public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	private ArrayList<Node> children = new ArrayList<>();
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getEnclosingInstance().hashCode();
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	private TestBuildOrder getEnclosingInstance() {
		return TestBuildOrder.this;
	}
	
	
  }
  
  void buildTree(String nodeValue, List<String> childrenValues)
  {
	  Node node=  new Node();
	  node.setValue(nodeValue);
	  if(nodes.contains(node)) 
	  {
		node = nodes.get(nodes.indexOf(node));  
	  }
	  else {
		  nodes.add(node);
	  }
	  ArrayList<Node> childList  = new ArrayList<>();
	  if(childrenValues != null && !childrenValues.isEmpty())
	  {
		  for(String childVal: childrenValues)
		  {
			  Node childNode=  new Node();
			  childNode.setValue(childVal);
			  if(nodes.contains(childNode)) 
			  {
				  childNode = nodes.get(nodes.indexOf(childNode));  
			  }
			  else {
				  nodes.add(childNode);
			  }
			  
			  childList.add(childNode);
		  }
	  }
	  node.setChildren(childList);
  }
  
  void printSequenceOrder()
  {
	  Node root = getRoot();
	  System.out.println(root.getValue());
	  ArrayList<Node> nodesVisitedForChildren = new ArrayList<>();
	  ArrayList<Node> nodesVisited = new ArrayList<>();
	  Stack<Node> stack = new Stack<>();
	  stack.add(root);
	  while(!stack.isEmpty())
	  {
		  Node node= stack.pop();
		  if(nodesVisited.contains(node))
		  {
			  continue;
		  }
		  if(nodesVisitedForChildren.contains(node))
		  {
			  System.out.println("sequence::" + node.getValue());
			  nodesVisited.add(node);
			  continue;
		  }
		  if(node.getChildren() != null && !node.getChildren().isEmpty())
		  {
			  stack.push(node);
			  for(Node child : node.getChildren())
			  {
				stack.push(child);  
			  }
			  nodesVisitedForChildren.add(node);
		  }
		  else 
		  {
			  System.out.println("sequence::" + node.getValue());
			  nodesVisited.add(node);			  
		  }
	  }
  }

private Node getRoot() 
{
	ArrayList<Node> childNodes = new ArrayList<>();
	for(Node node: nodes)
	{
		if(node.getChildren() != null && !node.getChildren().isEmpty())
		{
			for(Node childNode: node.getChildren())
			{
				if(!childNodes.contains(childNode))
				{
					childNodes.add(childNode);
				}
			}
		}
	}
	for(Node node: nodes)
	{
		if(!childNodes.contains(node))
			return node;
	}
	return null;
}
	
}
