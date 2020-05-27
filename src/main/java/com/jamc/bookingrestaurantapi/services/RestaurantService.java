package com.jamc.bookingrestaurantapi.services;

import java.util.List;

import com.jamc.bookingrestaurantapi.exceptions.BookingException;
import com.jamc.bookingrestaurantapi.json.RestaurantRest;

public interface RestaurantService {
	
	public RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;

	public List<RestaurantRest> getRestaurants() throws BookingException;
}
