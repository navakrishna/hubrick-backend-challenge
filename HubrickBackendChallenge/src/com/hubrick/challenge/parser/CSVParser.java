package com.hubrick.challenge.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hubrick.challenge.constants.ChallengeConstants;
import com.hubrick.challenge.model.Age;
import com.hubrick.challenge.model.AvgAge;
import com.hubrick.challenge.model.Department;
import com.hubrick.challenge.model.Employee;

/**
 * Department.csv, Ages.csv and Employees.csv files are given as input to the methods.
 * Extracts data from csv files and set onto the pojos. and pojos are logically connected.
 *
 */
public class CSVParser {

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 * 
	 * Map is populated with department id and department object. 
	 * Data will be extracted from csv file and populates Department pojo.
	 */
	public static Map<Integer, Department> getDeptartmentData(String file) throws IOException {
		Map<Integer, Department> departMentMap = new ConcurrentHashMap<Integer, Department>();

		Files.lines(Paths.get(file)).map(line -> line.split(",")).forEach(deptLines -> {
			Department dept = new Department(deptLines[0]);
			dept.setListOfEmployee(new ArrayList<Employee>());
			departMentMap.put(dept.getDeptId(), dept);
		});
		return departMentMap;
	}
	
	/**
	 * @param ageFileName
	 * @return
	 * @throws IOException
	 * 
	 * Map is populated with age(Number) and age object. 
	 * Data will be extracted from csv file and populates age pojo.
	 */

	public static Map<String, Age> getAgeData(String ageFileName) throws IOException {
		Map<String, Age> agetMap = new ConcurrentHashMap<String, Age>();

		Files.lines(Paths.get(ageFileName)).map(line -> line.split(",")).forEach(ageLines -> {
			Age age = new Age(ageLines[0], Integer.parseInt(ageLines[1]));
			agetMap.put(ageLines[0], age);
		});
		return agetMap;
	}

	/**
	 * @param deptMap
	 * @param ageMap
	 * @param emplFile
	 * @throws IOException
	 * 
	 * The logical relation between age, department and Employee is established.
	 * One department object will have list of employee objects and each employee will have age object.
	 */
	public static void setEmpeDataOnDept(Map<Integer, Department> deptMap, Map<String, Age> ageMap, String emplFile)
			throws IOException {
		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(emplFile)))) {
			String eachLine = "";
			while ((eachLine = reader.readLine()) != null) {

				String[] eachValue = eachLine.split(",");
				final Age age = ageMap.get(eachValue[1].trim());

				Employee emp = new Employee(eachValue[1], Double.parseDouble(eachValue[3]),
						Integer.parseInt(eachValue[0]), age);
				if (deptMap.get(Integer.parseInt(eachValue[0])) != null) {
					deptMap.get(Integer.parseInt(eachValue[0])).getListOfEmployee().add(emp);
				}

			}
		} 

	}

	/**
	 * @param deptMap
	 * @param avgAgeList
	 * @throws IOException
	 * 
	 * It populates output CSV files for median of income by department, median of age by department
	 * Average salary by age range and 95th Percentile of income by department.
	 * 
	 */
	public static void populateOutputCSVFiles(Map<Integer, Department> deptMap, List<AvgAge> avgAgeList)
			throws IOException {

		populateData(ChallengeConstants.MEDIAN_INCOME_BY_DEPT_CSV_FILE, deptMap.values(),
				new String[] { ChallengeConstants.MEDIAN_INCOME_BY_DET }, ChallengeConstants.MEDIAN);

		populateData(ChallengeConstants.EMPLOYEE_AGE_BY_DEPARTMENT_CSV_FILE, deptMap.values(),
				new String[] { ChallengeConstants.EMPLOYEE_AGE_BY_DEPARTMENT }, ChallengeConstants.AGE_MEDIAN);

		populateData(ChallengeConstants.INCOME_BY_PERCENTILE_CSV_FILE, deptMap.values(),
				new String[] { ChallengeConstants.PERCENTILE_INCOME_BY_DEPT }, ChallengeConstants.PERCENTILE_INCOME);

		populateData(ChallengeConstants.AVERAGE_SALARY_AGE_CSV_FILE, avgAgeList,
				new String[] { ChallengeConstants.AVG_INCOME_BY_AGE_RANGE, ChallengeConstants.AVG_RANGE_ },
				ChallengeConstants.AVG_SALARY_BY_AGE_RANGE, ChallengeConstants.AVG_RANGE);

	}

	/**
	 * @param incByDeptCSVFile
	 * @param deptValues
	 * @param columnNames
	 * @param operation
	 * @throws IOException
	 * 
	 * Creates csv file and populate data.
	 * 
	 */
	private static <T> void populateData(String incByDeptCSVFile, Collection<T> deptValues, String[] columnNames,
			String... operation) throws IOException {

		Path path = Paths.get(incByDeptCSVFile);
		List<T> deptList = null;
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			for (String columnName : columnNames) {
				writer.write(columnName);
				writer.write(ChallengeConstants.COMMA_SEPERATOR);
				deptList = new ArrayList<>(deptValues);
			}
			writer.write(ChallengeConstants.NEXT_LINE);
			for (T t : deptList) {
				Arrays.stream(operation).forEach(eachField -> {
					try {
						Field field = t.getClass().getDeclaredField(eachField);
						field.setAccessible(true);
						Object obj = field.get(t);
						writer.write(String.valueOf(obj));
						writer.write(ChallengeConstants.COMMA_SEPERATOR);
					} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
							| IllegalAccessException | IOException e) {
						e.getMessage();
					}
				});
				writer.write(ChallengeConstants.NEXT_LINE);
			}

			writer.flush();
		}
	}

}
