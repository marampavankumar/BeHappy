package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class TestTopologicalOrder {
	
	Stack<GraphNode> nodeStack = new Stack<>();
	List<GraphNode> nodeList = new ArrayList<>();
	List<GraphNode> visitedList =  new ArrayList<>();
	

	public static void main(String[] args) {
		GraphNode node40 = new GraphNode(40);
		GraphNode node20 = new GraphNode(20);
		GraphNode node50 = new GraphNode(50);
		GraphNode node10 = new GraphNode(10);
		GraphNode node30 = new GraphNode(30);
		GraphNode node60 = new GraphNode(60);
		GraphNode node70 = new GraphNode(70);
		node40.addNeighbour(node10);
		node40.addNeighbour(node20);
		node20.addNeighbour(node10);
		node20.addNeighbour(node30);
		node20.addNeighbour(node50);
		node20.addNeighbour(node60);
		node50.addNeighbour(node70);
		node10.addNeighbour(node30);
		node30.addNeighbour(node60);
		node60.addNeighbour(node70);
		TestTopologicalOrder testOrder = new TestTopologicalOrder();
		testOrder.nodeList.add(node40);
		testOrder.nodeList.add(node20);
		testOrder.nodeList.add(node50);
		testOrder.nodeList.add(node10);
		testOrder.nodeList.add(node30);
		testOrder.nodeList.add(node60);
		testOrder.nodeList.add(node70);
		testOrder.topologicalSort();

	}


	private void topologicalSort() {
		for(GraphNode node : nodeList)
		{
			topologicalSort(node);
		}
		while(!nodeStack.isEmpty())
		{
			GraphNode node = nodeStack.pop();
			node.print();
		}
	}


	private void topologicalSort(GraphNode node) {
		if(visitedList.contains(node))
			return;
		for(GraphNode neighbourNode: node.getNeighbours())
		{
			topologicalSort(neighbourNode);
		}
		visitedList.add(node);
		nodeStack.push(node);
		
		
	}

}

class GraphNode 
{
	int value;
	List<GraphNode> neighbours = new ArrayList<>();
	GraphNode(int value)
	{
		this.value = value;
	}
	public List<GraphNode> getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(List<GraphNode> neighbours) {
		this.neighbours = neighbours;
	}
	
	public void addNeighbour(GraphNode node)
	{
		neighbours.add(node);
	}
	
	public void print()
	{
		System.out.print(value + " ");
	}
}
