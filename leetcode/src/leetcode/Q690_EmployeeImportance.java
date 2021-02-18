package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q690. Employee inportance.
 * DFS
 * first, make a map (id, employee)
 * while subordinate is null, add the value
 * 
 * @author jeesublee
 */
public class Q690_EmployeeImportance {

	public int getImportance(List<Employee> employees, int id) {
		Map<Integer, Employee> map = init(employees);
		return dfs(map, map.get(id));
	}

	private Map<Integer, Employee> init(List<Employee> employees) {
		Map<Integer, Employee> map = new HashMap<>();
		for (Employee employee : employees) {
			map.put(employee.id, employee);
		}
		return map;
	}

	private int dfs(Map<Integer, Employee> map, Employee curr) {
		if (curr.subordinates.size() == 0) {
			return curr.importance;
		}
		int sum = curr.importance;
		for (int subordinateId : curr.subordinates) {
			sum += dfs(map, map.get(subordinateId));
		}
		return sum;
	}

	private class Employee {
		public int id;
		public int importance;
		public List<Integer> subordinates;
	}
}
