package com.hubrick.challenge.model;

import java.util.List;

/**
 * It stores department data from csv file.
 *
 */
public class Department {
	private static int id = 1;
	private int deptId;
	private List<Employee> listOfEmployee;
	private String deptName;
	private Double median;
	private Integer ageMedian;
	private Double percentileInc;

	public Department(String deptName) {
		this.deptName = deptName;
		this.deptId = id;
		id++;
	}

	/**
	 * @return
	 */
	public int getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId
	 */
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return
	 */
	public List<Employee> getListOfEmployee() {
		return listOfEmployee;
	}

	/**
	 * @param listOfEmployee
	 */
	public void setListOfEmployee(List<Employee> listOfEmployee) {
		this.listOfEmployee = listOfEmployee;
	}

	/**
	 * @return
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return
	 */
	public Double getMedian() {
		return median;
	}

	/**
	 * @param median
	 */
	public void setMedian(Double median) {
		this.median = median;
	}

	/**
	 * @return
	 */
	public Double getPercentileInc() {
		return percentileInc;
	}

	/**
	 * @param percentileInc
	 */
	public void setPercentileInc(Double percentileInc) {
		this.percentileInc = percentileInc;
	}

	/**
	 * @return
	 */
	public Integer getAgeMedian() {
		return ageMedian;
	}

	/**
	 * @param ageMedian
	 */
	public void setAgeMedian(Integer ageMedian) {
		this.ageMedian = ageMedian;
	}

}
