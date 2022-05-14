package com.example.springboot;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.rewards.pojo.RewardPoints;
import com.example.rewards.service.RewardPointsService;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class RewardPointsControllerTest {

	private MockMvc mockMvc;

	@Mock
	private RewardPointsService rewardPointsService;

	@InjectMocks
	private RewardPointsController rewardPointsController;

	@BeforeEach
	public void beforeEach() {
		mockMvc = MockMvcBuilders.standaloneSetup(rewardPointsController).build();
	}

	@Test
	public void testCalculateRewardsIsOk() {
		List<RewardPoints> rewardPoints = new LinkedList<>();
		Mockito.when(rewardPointsService.calculateRewardPoints()).thenReturn(rewardPoints);
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/calculateRewardPoints").accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception E) {

		}
		Mockito.verify(rewardPointsService, Mockito.times(1)).calculateRewardPoints();
	}
	
	@Test
	public void testCalculateRewardsIsNotOk() {
		Mockito.when(rewardPointsService.calculateRewardPoints()).thenThrow(new RuntimeException("Failed"));
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/calculateRewardPoints").accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isNotFound());
		} catch (Exception E) {

		}
		Mockito.verify(rewardPointsService, Mockito.times(1)).calculateRewardPoints();
	}
}
