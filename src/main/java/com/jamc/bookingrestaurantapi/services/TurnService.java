package com.jamc.bookingrestaurantapi.services;

import com.jamc.bookingrestaurantapi.exceptions.BookingException;
import com.jamc.bookingrestaurantapi.json.TurnRest;

public interface TurnService {
	
	public TurnRest getTurnById(Long turnId) throws BookingException;

}
