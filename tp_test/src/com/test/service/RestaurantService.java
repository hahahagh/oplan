package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.RestaurantDao;
import com.test.domain.Board;
import com.test.domain.Restaurant;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantDao restaurantDao;
	
	public void add(Restaurant restaurant) {
		
		System.out.println("res service:======");
		// 글번호num 구하기
		Integer maxNum = restaurantDao.getMaxNum();
		System.out.println(maxNum);
		if (maxNum == null) {
			restaurant.setNum(1);
		} else {
			restaurant.setNum(maxNum + 1);
		}

		
		System.out.println(restaurant.toString());
		restaurantDao.addRestaurant(restaurant);
	} // add
	
	public int getRestaurantCount() {
		return restaurantDao.count();
	}
	
	public List<Restaurant> getRestaurantList(int startRow, int endRow){
		return restaurantDao.getRestaurantList(startRow, endRow);	
	}
	
	public Restaurant getRestaurant(int num) {
		return restaurantDao.getRestaurant(num);
	}
}
