package com.test.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.test.domain.Board;
import com.test.domain.Restaurant;

@Repository
public class RestaurantDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("restaurant_test");

	}

	public Integer getMaxNum() {
		try {
			Integer maxNum = jdbcTemplate.queryForObject("SELECT MAX(num) FROM restaurant_test", Integer.class);

			return maxNum;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void addRestaurant(Restaurant restaurant) {
		simpleJdbcInsert.execute(new BeanPropertySqlParameterSource(restaurant));
	}

	public int count() {
		int count = jdbcTemplate.queryForObject("select count(*) from restaurant_test", Integer.class);
		return count;
	}

	public List<Restaurant> getRestaurantList(int startRow, int endRow) {
		
		System.out.println("dao호출 getlist1");
	
		/*List<Restaurant> restaurants = jdbcTemplate.query("select * from restaurant_test",
				new BeanPropertyRowMapper<Restaurant>(Restaurant.class), startRow, endRow);*/
		List<Restaurant> restaurants = jdbcTemplate.query("select * from restaurant_test", 
				new BeanPropertyRowMapper<Restaurant>(Restaurant.class));
		System.out.println("dao호출 getlist");
		
		return restaurants;
	}
	
	public Restaurant getRestaurant(int num) {
		Restaurant restaurant = jdbcTemplate.queryForObject("select * from  restaurant_test where num=?", 
				new BeanPropertyRowMapper<Restaurant>(Restaurant.class),num);
		return restaurant;
	}

}
