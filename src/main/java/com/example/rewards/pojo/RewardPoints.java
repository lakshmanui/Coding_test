package com.example.rewards.pojo;

import java.util.List;

public class RewardPoints {

	private String customer;
	private List<RewardPointsByMonth> rewardsPointsByMonth;
	private Long totalPoints;

	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public List<RewardPointsByMonth> getRewardsPointsByMonth() {
		return rewardsPointsByMonth;
	}
	public void setRewardsPointsByMonth(List<RewardPointsByMonth> rewardsPointsByMonth) {
		this.rewardsPointsByMonth = rewardsPointsByMonth;
	}
	public Long getTotalPoints() {
		return totalPoints;
	}
	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}
}
