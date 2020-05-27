package com.jamc.bookingrestaurantapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jamc.bookingrestaurantapi.exceptions.BookingException;
import com.jamc.bookingrestaurantapi.json.CreateReservationRest;
import com.jamc.bookingrestaurantapi.json.ReservationRest;
import com.jamc.bookingrestaurantapi.responses.BookingResponse;
import com.jamc.bookingrestaurantapi.services.ReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking-restaurant" + "/v1")
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/reservation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<String> createReservation(@RequestBody CreateReservationRest createReservationRest) throws BookingException{
		return new BookingResponse<>("success", String.valueOf(HttpStatus.OK), "OK", reservationService.createReservation(createReservationRest));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/reservation" + "/{" + "reservationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<ReservationRest> getReservationById(@PathVariable Long reservationId) throws BookingException{
		return new BookingResponse<>("success", String.valueOf(HttpStatus.OK), "OK", reservationService.getReservation(reservationId));
	}

}
