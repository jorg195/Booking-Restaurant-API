package com.jamc.bookingrestaurantapi.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamc.bookingrestaurantapi.exceptions.BookingException;
import com.jamc.bookingrestaurantapi.exceptions.NotFoundException;
import com.jamc.bookingrestaurantapi.json.RestaurantRest;
import com.jamc.bookingrestaurantapi.models.dao.IRestaurantDao;
import com.jamc.bookingrestaurantapi.models.entity.Restaurant;
import com.jamc.bookingrestaurantapi.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private IRestaurantDao restaurantDao;
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	/* MAPPER */
	
	@Override
	public RestaurantRest getRestaurantById(Long restaurantId) throws BookingException {
		
		return modelMapper.map(getRestaurantEntity(restaurantId), RestaurantRest.class);
	
	}
	
	@Override
	public List<RestaurantRest> getRestaurants() throws BookingException {
		
		final List<Restaurant> restaurantsEntity = restaurantDao.findAll();
		
		return restaurantsEntity.stream().map(service -> modelMapper.map(service,  RestaurantRest.class))
				.collect(Collectors.toList());
		
	}
	
	/*ENTITY */
	
	private Restaurant getRestaurantEntity(Long restaurantId) throws BookingException{
		return restaurantDao.findById(restaurantId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1","RESTAURANT NOT FOUND"));
	}
	
	

	
}
