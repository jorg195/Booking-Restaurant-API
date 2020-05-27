package com.jamc.bookingrestaurantapi.services.implement;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamc.bookingrestaurantapi.exceptions.BookingException;
import com.jamc.bookingrestaurantapi.exceptions.InternalServerException;
import com.jamc.bookingrestaurantapi.exceptions.NotFoundException;
import com.jamc.bookingrestaurantapi.json.CreateReservationRest;
import com.jamc.bookingrestaurantapi.json.ReservationRest;
import com.jamc.bookingrestaurantapi.models.dao.IReservationDao;
import com.jamc.bookingrestaurantapi.models.dao.IRestaurantDao;
import com.jamc.bookingrestaurantapi.models.dao.ITurnDao;
import com.jamc.bookingrestaurantapi.models.entity.Reservation;
import com.jamc.bookingrestaurantapi.models.entity.Restaurant;
import com.jamc.bookingrestaurantapi.models.entity.Turn;
import com.jamc.bookingrestaurantapi.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private IReservationDao reservationDao;
	
	@Autowired
	private IRestaurantDao restaurantDao;
	
	@Autowired 
	private ITurnDao turnDao;
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	/* MAPPER */
	
	@Override
	public ReservationRest getReservation(Long reservationId) throws BookingException {

		return modelMapper.map(getReservationEntity(reservationId), ReservationRest.class);
		
	}

	@Override
	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {
		
		final Restaurant restaurantId = restaurantDao.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND"));
		
		final Turn turn = turnDao.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFoundException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));
		
		
		String locator = generateLocator(restaurantId, createReservationRest);
		
		final Reservation reservation = new Reservation();
		
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getName());
		
		try {
			reservationDao.save(reservation);
		}catch(final Exception e){
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		
		return locator;
	}
	
	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservationRest) throws BookingException{
		
		return restaurantId.getName() + createReservationRest.getTurnId();
	}
	
	
	
	/* ENTITY */
	
	private Reservation getReservationEntity(Long reservationId) throws BookingException{
		return reservationDao.findById(reservationId)
				.orElseThrow(() -> new NotFoundException("SNOT-404-1","RESERVATION NOT FOUND"));
	}

}
