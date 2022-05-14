package com.example.rewards.service;

import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.json.simple.parser.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.rewards.pojo.RewardPoints;
import com.example.rewards.pojo.RewardPointsByMonth;

@Service
public class RewardPointsService {

	public List<RewardPoints> calculateRewardPoints() {
		List<RewardPoints> rewardPointsOfAllCustomers = new LinkedList<>();
		try {
	        JSONParser parser = new JSONParser();
	        //Read customers data file
	        JSONObject data = (JSONObject) parser.parse(new FileReader("./static/data.json"));
	        JSONArray customersData = (JSONArray) data.get("customer");
	        
	        if (customersData.size() > 0) {
	        	// calculate rewards for each customer
	        	for (int i=0; i < customersData.size(); i++) {
	        		RewardPoints rewardPoints = new RewardPoints();
	        		
	        		JSONObject customer =  (JSONObject) customersData.get(i);
	        		rewardPoints.setCustomer((String) customer.get("name"));
	        		
	        		JSONArray customerOrders = (JSONArray) customer.get("orderHistory");
	        		List<RewardPointsByMonth> rmList = new LinkedList<>();
	        		
	        		// calculate rewards for each transaction
	        		setRewardsForEachOrder(customerOrders, rmList);
	        		rewardPoints.setRewardsPointsByMonth(rmList);

	        		// calculate total reward points of all transactions of a customer
	        		rewardPoints.setTotalPoints(getTotalPoints(rmList));
	        		rewardPointsOfAllCustomers.add(rewardPoints);
	        	}
	        }
	        
	    } catch (IOException | ParseException | java.text.ParseException e) {
	        e.printStackTrace();
	    }
		return rewardPointsOfAllCustomers;
	}

	private void setRewardsForEachOrder(JSONArray customerOrders, List<RewardPointsByMonth> rmList)
			throws java.text.ParseException {
		if (customerOrders.size() > 0) {
			for (int j=0; j < customerOrders.size(); j++) {
				JSONObject order = (JSONObject) customerOrders.get(j);
				Long orderValue = (order.get("price")).getClass().getSimpleName().equalsIgnoreCase("Long") ? 
						Math.round((Long) order.get("price")) : Math.round((Double) order.get("price"));
				
				String month = getMonthName(order);
				RewardPointsByMonth exists = checkIfMonthAlreadyExists(month, rmList);
				
				boolean multipleOrdersInTheSameMonth = false;
				if (exists.getMonth() == null) {	        					
					exists.setMonth(month);
				} else {
					multipleOrdersInTheSameMonth = true;
				}
				
				if (orderValue > 50) {
					exists.setPoints((exists.getPoints() > 0 ? exists.getPoints() : 0)  + (orderValue - 50) * 1 + (orderValue > 100 ? ((orderValue - 100) * 1) : 0));
				} else {
					exists.setPoints((exists.getPoints() > 0 ? exists.getPoints() : 0) + 0L);
				}
				if (!multipleOrdersInTheSameMonth) rmList.add(exists);
			}
		}
	}
	
	private Long getTotalPoints(List<RewardPointsByMonth> rmList) {
		Long total = 0L;
		if (!CollectionUtils.isEmpty(rmList)) {
			for (RewardPointsByMonth r: rmList) {
				total = total + r.getPoints();
			}
		}
		return total;
	}

	private RewardPointsByMonth checkIfMonthAlreadyExists(String month, List<RewardPointsByMonth> rmList) {
		RewardPointsByMonth rm = new RewardPointsByMonth();
		if (!CollectionUtils.isEmpty(rmList)) {
			rm = rmList.stream().filter(i -> month.equalsIgnoreCase(i.getMonth())).findFirst().orElse(null);
			if (rm != null) {
				return rm;
			}
		}
		return new RewardPointsByMonth();
	}

	private String getMonthName(JSONObject order) throws java.text.ParseException {
		String date = (String) order.get("purchaseDate");
		Date orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(date.split("T")[0]);  
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(orderDate);
		Month month = Month.of(calendar.get(Calendar.MONTH) + 1);       
		Locale locale = Locale.getDefault();
		return month.getDisplayName(TextStyle.FULL, locale);
	}
	
	
}
