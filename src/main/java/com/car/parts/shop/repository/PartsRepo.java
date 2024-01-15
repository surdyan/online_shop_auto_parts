package com.car.parts.shop.repository;

import java.util.List;

import com.car.parts.shop.configuration.PartsRegistration;
import org.springframework.data.repository.CrudRepository;

public interface PartsRepo extends CrudRepository<PartsRegistration, String>
{
	List<PartsRegistration> findAll();

}
