package com.hubrick.challenge.model;

/**
 * It stores Employee data from CSV file.
 *
 */
public class Employee {
	private String employeeName;
	private Double salary;
	private Integer depId;
	private String gender;
	private Age age;

	public Employee(String employeeName, Double salary, Integer depId, Age age) {
		this.employeeName = employeeName;
		this.salary = salary;
		this.depId = depId;
		this.age = age;
	}

	/**
	 * @return
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * @param employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	/**
	 * @return
	 */
	public Double getSalary() {
		return salary;
	}

	/**
	 * @param salary
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}

	/**
	 * @return
	 */
	public Integer getDepId() {
		return depId;
	}

	/**
	 * @param depId
	 */
	public void setDepId(Integer depId) {
		this.depId = depId;
	}

	/**
	 * @return
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return
	 */
	public Age getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(Age age) {
		this.age = age;
	}

}
