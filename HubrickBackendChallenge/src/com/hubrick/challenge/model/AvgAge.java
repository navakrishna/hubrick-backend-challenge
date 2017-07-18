package com.hubrick.challenge.model;

/**
 * We use this pojo to populate average salary based on pojo.
 *
 */
public class AvgAge {
	private String ageRange;
	private Double avgSalary;

	public AvgAge(String ageRange, Double avgSalary) {
		super();
		this.ageRange = ageRange;
		this.avgSalary = avgSalary;
	}

	/**
	 * @return
	 */
	public String getAgeRange() {
		return ageRange;
	}

	/**
	 * @param ageRange
	 */
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	/**
	 * @return
	 */
	public Double getAvgSalary() {
		return avgSalary;
	}

	/**
	 * @param avgSalary
	 */
	public void setAvgSalary(Double avgSalary) {
		this.avgSalary = avgSalary;
	}

}
