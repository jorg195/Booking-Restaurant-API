package com.jamc.bookingrestaurantapi.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamc.bookingrestaurantapi.models.entity.Turn;

public interface ITurnDao extends JpaRepository<Turn, Long>{

	Optional<Turn> findById(String id);
	
}
