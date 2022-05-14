package com.example.springboot;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewards.pojo.RewardPoints;
import com.example.rewards.service.RewardPointsService;

@RestController
public class RewardPointsController {
	private RewardPointsService rewardPointsService;
	
	public RewardPointsController(RewardPointsService rewardPointsService) {
		this.rewardPointsService = rewardPointsService;
		
	}
	
	@GetMapping(value="/calculateRewardPoints")
	@ResponseBody
	public List<RewardPoints> calculateRewards() {
		return rewardPointsService.calculateRewardPoints();
	}

}