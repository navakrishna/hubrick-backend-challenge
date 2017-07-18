
package com.hubrick.challenge.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalDouble;
import java.util.Set;

import com.hubrick.challenge.model.Age;
import com.hubrick.challenge.model.AvgAge;
import com.hubrick.challenge.model.Department;
import com.hubrick.challenge.model.Employee;
import com.hubrick.challenge.parser.CSVParser;

/**
 * 
 * Median of income by department, 95th percentile of department, Average salary by age with factor of 10
 * and Median age of employee by department is calculated. 
 *
 */
public class ChallengeBusiness {
	private int count = 10;

	/**
	 * 
	 * @param fileName
	 * @throws IOException
	 * 
	 * Invokes methods from CSV parser to extract data for 
	 * establishing logical relation between Department, Employee and Age Pojos.
	 * 
	 */
	public void challengeBusiness(String... fileName) throws IOException {
		Map<Integer, Department> deptMap = CSVParser.getDeptartmentData(fileName[0]);
		Map<String, Age> ageMap = CSVParser.getAgeData(fileName[1]);
		CSVParser.setEmpeDataOnDept(deptMap, ageMap, fileName[2]);
		setmedianIncomeByDept(deptMap);
		setIncByDeptFor95Percentile(deptMap);
		setMedianAgeByDept(deptMap);
		List<AvgAge> avgAgeList = setAvgIncomeByAgeRange(deptMap);

		CSVParser.populateOutputCSVFiles(deptMap, avgAgeList);

	}

	/**
	 * @param deptMap
	 * 
	 * Median of income by department is calculated .
	 * 
	 */
	private void setmedianIncomeByDept(Map<Integer, Department> deptMap) {
		Set<Entry<Integer, Department>> deptEntries = deptMap.entrySet();
		deptEntries.stream().map(entry -> entry.getValue()).forEach(dept -> {

			List<Employee> list = dept.getListOfEmployee();
			Collections.sort(list, (c1, c2) -> c1.getSalary().compareTo(c2.getSalary()));
			int size = list.size();
			dept.setMedian(
					size % 2 == 0 ? ((list.get(size / 2).getSalary() + (list.get((size / 2) + 1).getSalary())) / 2)
							: list.get(size / 2).getSalary());
		});
		;

	}

	/**
	 * @param deptMap
	 * 
	 * 95th Percentile of the income by department is calculated.
	 */
	private void setIncByDeptFor95Percentile(Map<Integer, Department> deptMap) {
		Set<Entry<Integer, Department>> deptEntries = deptMap.entrySet();
		deptEntries.stream().map(entry -> entry.getValue()).forEach(dept -> {
			List<Employee> list = dept.getListOfEmployee();
			Collections.sort(list, (c1, c2) -> c1.getSalary().compareTo(c2.getSalary()));
			int size = list.size();
			int index = (int) Math.round((0.95 * size));
			dept.setPercentileInc(list.get(index - 1).getSalary());
		});

	}

	/**
	 * @param deptMap
	 * 
	 * Median of Age by department is calculated
	 */
	private void setMedianAgeByDept(Map<Integer, Department> deptMap) {
		Set<Entry<Integer, Department>> deptEntries = deptMap.entrySet();
		deptEntries.stream().map(entry -> entry.getValue()).forEach(dept -> {
			List<Employee> list = dept.getListOfEmployee();

			Collections.sort(list, (c1, c2) -> c1.getAge().getAge().compareTo(c2.getAge().getAge()));
			int size = list.size();
			dept.setAgeMedian(size % 2 == 0
					? ((list.get(size / 2).getAge().getAge() + (list.get((size / 2) + 1).getAge().getAge())) / 2)
					: list.get(size / 2).getAge().getAge());
		});

	}

	/**
	 * @param deptMap
	 * @return
	 * 
	 * Average Income by age range is calculated.
	 */
	private List<AvgAge> setAvgIncomeByAgeRange(Map<Integer, Department> deptMap) {

		final List<Employee> empList = new ArrayList<>();
		deptMap.entrySet().stream().map(entry -> entry.getValue().getListOfEmployee())
				.forEach(empObjList -> empList.addAll(empObjList));
		final List<AvgAge> ageAvgList = new ArrayList<>();
		int maxAge = empList.stream().mapToInt(emp -> emp.getAge().getAge()).max().getAsInt();
		while (count < maxAge) {
			OptionalDouble avgSal10_20 = empList.stream().filter(emp -> (emp.getAge().getAge() / count == 1))
					.mapToDouble(emp -> emp.getSalary()).average();
			ageAvgList.add(new AvgAge(String.format("%d%s%d", count, "", (count + 10)), (avgSal10_20.getAsDouble())));
			count += 10;
		}
		return ageAvgList;

	}

}
