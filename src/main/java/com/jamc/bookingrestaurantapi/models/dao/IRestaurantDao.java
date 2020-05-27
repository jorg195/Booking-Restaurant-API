package com.jamc.bookingrestaurantapi.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jamc.bookingrestaurantapi.models.entity.Restaurant;

//DAO

@Repository
public interface IRestaurantDao extends JpaRepository<Restaurant, Long>{

	Optional<Restaurant> findById(Long id);
	
	Optional<Restaurant> findByName(String nameRestaurant);
	
	@Query("SELECT REST FROM Restaurant REST")
	public List<Restaurant> findRestaurants();
	
}
