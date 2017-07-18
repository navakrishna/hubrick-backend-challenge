package com.hubrick.challenge.model;

/**
 * It stores Age data from csv file.
 *
 */
public class Age {
	private String employeeName;
	private Integer age;
	
	
	public Age(String employeeName, Integer age) {
		this.employeeName = employeeName;
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
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

}
