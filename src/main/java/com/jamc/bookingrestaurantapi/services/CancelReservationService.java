package com.jamc.bookingrestaurantapi.services;

import com.jamc.bookingrestaurantapi.exceptions.BookingException;

public interface CancelReservationService {

	public String deleteReservation(String locator) throws BookingException;
	
}
