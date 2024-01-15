package com.car.parts.shop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.car.parts.shop.configuration.OrderRegistration;

public interface OrderRepo extends CrudRepository<OrderRegistration, Integer>
{
	List<OrderRegistration> findAll();
	
	public List<OrderRegistration> findByBusername(String busername);

}