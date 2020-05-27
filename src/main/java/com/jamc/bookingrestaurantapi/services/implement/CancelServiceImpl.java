package com.jamc.bookingrestaurantapi.services.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jamc.bookingrestaurantapi.exceptions.BookingException;
import com.jamc.bookingrestaurantapi.exceptions.InternalServerException;
import com.jamc.bookingrestaurantapi.exceptions.NotFoundException;
import com.jamc.bookingrestaurantapi.models.dao.IReservationDao;
import com.jamc.bookingrestaurantapi.services.CancelReservationService;

@Service
public class CancelServiceImpl implements CancelReservationService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CancelServiceImpl.class);

	@Autowired
	private IReservationDao reservationDao;
	
	@Override
	public String deleteReservation(String locator) throws BookingException {
		reservationDao.findByLocator(locator)
				.orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));
		
		try {
			reservationDao.deleteByLocator(locator);
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			throw new InternalServerException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		
		return "LOCATOR_DELETED";
	}

}
