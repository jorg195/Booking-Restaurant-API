package com.jamc.bookingrestaurantapi.services;

import com.jamc.bookingrestaurantapi.exceptions.BookingException;
import com.jamc.bookingrestaurantapi.json.CreateReservationRest;
import com.jamc.bookingrestaurantapi.json.ReservationRest;

public interface ReservationService {
	
	public ReservationRest getReservation(Long reservationId) throws BookingException;

	public String createReservation(CreateReservationRest createReservationRest) throws BookingException;
	
}
