package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TestBuildOrder {

  Map<String, Node> nodes = new HashMap<>();

  public static void main(String[] args) {
    TestBuildOrder buildOrder = new TestBuildOrder();
    buildOrder.buildTree("fiserver", Arrays.asList("common", "logging", "utils", "security", "platform", "external"));
    buildOrder.buildTree("isagent", Arrays.asList("common", "logging", "utils", "jpos"));
    buildOrder.buildTree("logging", List.of("common"));
    buildOrder.buildTree("utils", Arrays.asList("common", "external"));
    buildOrder.buildTree("security", List.of("common"));
    buildOrder.buildTree("mgateway", Arrays.asList("common", "external", "platform", "utils", "logging", "security"));
    buildOrder.buildTree("common", List.of("external"));
    buildOrder.buildTree("setup", Arrays.asList("fiserver", "isagent", "mgateway"));
    buildOrder.buildTree("tools", List.of("common"));



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
    public List<Node> getParentModules() {
      return parentModules;
    }
    public void setParentModules(List<Node> parentModules) {
      this.parentModules = parentModules;
    }
    private List<Node> parentModules = new ArrayList<>();
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
        return other.value == null;
      } else return value.equals(other.value);
    }
    private TestBuildOrder getEnclosingInstance() {
      return TestBuildOrder.this;
    }


  }

  void buildTree(String nodeValue, List<String> parentValues)
  {
    Node node;
    if(nodes.containsKey(nodeValue))
    {
      node = nodes.get(nodeValue);
    }
    else {
      node=  new Node();
      node.setValue(nodeValue);
      nodes.put(nodeValue, node);
    }
    ArrayList<Node> parentList  = new ArrayList<>();
    if(parentValues != null && !parentValues.isEmpty())
    {
      for(String parentVal: parentValues)
      {
        Node parentNode;
        if(nodes.containsKey(parentVal))
        {
          parentNode = nodes.get(parentVal);
        }
        else {
          parentNode = new Node();
          parentNode.setValue(parentVal);
          nodes.put(parentVal, parentNode);
        }

        parentList.add(parentNode);
      }
    }
    node.setParentModules(parentList);
  }

  void printSequenceOrder()
  {
    List<Node> leafNodes = getLeafNodes();
    ArrayList<Node> nodesVisitedForParents = new ArrayList<>();
    ArrayList<Node> nodesVisited = new ArrayList<>();
    Stack<Node> stack = new Stack<>();
    stack.addAll(leafNodes);
    while(!stack.isEmpty())
    {
      Node node= stack.pop();
      if(nodesVisited.contains(node))
      {
        continue;
      }
      if(nodesVisitedForParents.contains(node))
      {
        System.out.println("sequence::" + node.getValue());
        nodesVisited.add(node);
        continue;
      }
      if(node.getParentModules() != null && !node.getParentModules().isEmpty())
      {
        stack.push(node);
        for(Node parent : node.getParentModules())
        {
          stack.push(parent);
        }
        nodesVisitedForParents.add(node);
      }
      else
      {
        System.out.println("sequence::" + node.getValue());
        nodesVisited.add(node);
      }
    }
  }

  private List<Node> getLeafNodes()
  {
    List<Node> leafNodes = new ArrayList<>();
    ArrayList<Node> parentNodes = new ArrayList<>();
    for(Node node: nodes.values())
    {
      if(node.getParentModules() != null && !node.getParentModules().isEmpty())
      {
        for(Node parentNode: node.getParentModules())
        {
          if(!parentNodes.contains(parentNode))
          {
            parentNodes.add(parentNode);
          }
        }
      }
    }
    for(Node node: nodes.values())
    {
      if(!parentNodes.contains(node))
        leafNodes.add(node);
    }
    return leafNodes;
  }

}
