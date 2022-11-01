package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class TestBuildOrderNew {
	Module rootModule;

	public static void main(String[] args) {
		TestBuildOrderNew bo = new TestBuildOrderNew();
		Map<String, List<String>> moduleMap = new HashMap<>();
		moduleMap.put("M1", Arrays.asList("M2", "M3"));
		moduleMap.put("M2", Arrays.asList("M3", "M4"));
		moduleMap.put("M3", Arrays.asList("M7", "M8"));
		moduleMap.put("M4", Arrays.asList("M8", "M10"));
		moduleMap.put("M7", Arrays.asList("M8", "M12"));
		moduleMap.put("M12", Arrays.asList("M14", "M15"));
		bo.addModuleDependencies(moduleMap);
		System.out.println("build Order::" + bo.calculateBuildOrder());
		bo.calculatePostOrder();
		bo.calculatePreOrder();
		
		testDFS();
	}

	private void calculatePreOrder() {
		List<String> visitedNodes = new ArrayList<>();
		List<String> order = new ArrayList<>();
		calculatePreOrder(rootModule, visitedNodes, order);
		System.out.println("Pre Order::" + order);
		
	}

	private void calculatePreOrder(Module module, List<String> visitedNodes, List<String> order) {
		if(visitedNodes.contains(module.getModuleId()))
			return;
		order.add(module.getModuleId());
		visitedNodes.add(module.getModuleId());
		
		if(module.getChildren() != null && !module.getChildren().isEmpty())
		{
			for(Module child : module.getChildren())
			{
				if(!visitedNodes.contains(child.getModuleId()))

					calculatePreOrder(child, visitedNodes, order);
			}
		}
		
	}

	private void calculatePostOrder() {
		List<String> visitedNodes = new ArrayList<>();
		List<String> order = new ArrayList<>();
		calculatePostOrder(rootModule, visitedNodes, order);
		System.out.println("Post Order::" + order);
		
	}

	private void calculatePostOrder(Module module, List<String> visitedNodes, List<String> order) {
		if(visitedNodes.contains(module.getModuleId()))
			return;
		if(module.getChildren() != null && !module.getChildren().isEmpty())
		{
			for(Module child : module.getChildren())
			{
				if(!visitedNodes.contains(child.getModuleId()))

					calculatePostOrder(child, visitedNodes, order);
			}
		}
		order.add(module.getModuleId());
		visitedNodes.add(module.getModuleId());
		
	}

	private static void testDFS() {
		TestBuildOrderNew b11 = new TestBuildOrderNew();
		Map<String, List<String>> moduleMap = new HashMap<>();
		moduleMap.put("M1", Arrays.asList("M2", "M3"));
		moduleMap.put("M2", Arrays.asList("M4", "M5"));
		moduleMap.put("M3", Arrays.asList("M6", "M7"));
		moduleMap.put("M4", Arrays.asList("M8", "M9"));
		moduleMap.put("M5", Arrays.asList("M12", "M13"));
		moduleMap.put("M6", Arrays.asList("M10", "M11"));
		moduleMap.put("M7", Arrays.asList("M14", "M15"));
		moduleMap.put("M8", Arrays.asList("M16", "M17"));
		b11.addModuleDependencies(moduleMap);
        b11.DFS();
		
	}

	private void DFS() {
		
		Stack<Module> stk = new Stack<>();
		stk.push(rootModule);
		List<String> order = new ArrayList<>();
		while(!stk.isEmpty())
		{
			Module module = stk.pop();
			order.add(module.getModuleId());
			if(!module.getChildren().isEmpty())
			{
				for(Module childModule: module.getChildren())
				{
					stk.push(childModule);
				}
			}
		}
		System.out.println("DFS::" + order);
	}

	private void addModuleDependencies(Map<String, List<String>> moduleMap) {
		Map<String, Module> moduleIdVsModuleMap = new HashMap<>();
		for(Entry<String, List<String>> entry :moduleMap.entrySet())
		{
			String moduleId  = entry.getKey();
			Module parentModule = null;
			if(moduleIdVsModuleMap.containsKey(moduleId))
			{
				parentModule = moduleIdVsModuleMap.get(moduleId);
			}
			else {
				parentModule = new Module(moduleId);
				moduleIdVsModuleMap.put(moduleId, parentModule);
			}
			List<Module> children = parentModule.getChildren();
			for(String child : entry.getValue())
			{
				Module childModule;
				if(moduleIdVsModuleMap.containsKey(child))
				{
					childModule = moduleIdVsModuleMap.get(child);
				}
				else {
					childModule = new Module(child);
					moduleIdVsModuleMap.put(child, childModule);
				}
				children.add(childModule);
			}
		}
		
		String root = findRoot(moduleMap);
		rootModule = moduleIdVsModuleMap.get(root);
		
	}

	private String findRoot(Map<String, List<String>> moduleMap) {
		Set<String> parents = moduleMap.keySet();
		Set<String> children = new HashSet<>();
		for(List<String> valueList : moduleMap.values())
		{
			children.addAll(valueList);
		}
		
		parents.removeAll(children);
		String root = parents.iterator().next();
		System.out.println("root::" + root);
		return root;
		
	}

	private List<String> calculateBuildOrder() {
		Stack<Module> stk = new Stack<>();
		Set<String> visitedModuleIds = new HashSet<>();
		Set<String> visitedModuleIdsForChildren = new HashSet<>();
		stk.push(rootModule);
		List<String> order = new ArrayList<>();
		while(!stk.isEmpty())
		{
			Module module = stk.pop();
			if(visitedModuleIds.contains(module.getModuleId()))
				continue;
			if(visitedModuleIdsForChildren.contains(module.getModuleId()))
			{
				System.out.println(module.getModuleId());
				order.add(module.getModuleId());
				visitedModuleIds.add(module.getModuleId());
				continue;
			}
			if(!module.getChildren().isEmpty())
			{
				stk.push(module);
				for(Module childModule: module.getChildren())
				{
					if(!visitedModuleIds.contains(childModule.getModuleId()))
						stk.push(childModule);
				}
				visitedModuleIdsForChildren.add(module.getModuleId());
			}
			else
			{
				System.out.println(module.getModuleId());
				order.add(module.getModuleId());
				visitedModuleIds.add(module.getModuleId());
			}
		}
		
		return order;
		
	}

}

class Module
{
	String moduleId;
	List<Module> children;
	public Module(String moduleId) {
		this.moduleId =  moduleId;
		children = new ArrayList<>();
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public List<Module> getChildren() {
		return children;
	}
}
