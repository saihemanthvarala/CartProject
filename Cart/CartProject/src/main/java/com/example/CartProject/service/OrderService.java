package com.example.CartProject.service;

import java.sql.Date;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.CartProject.model.Orders;
import com.example.CartProject.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public Orders order(Orders orders)
	{
		return repository.save(orders);
	}
	
	@Transactional
	public void cancel(Orders orders)
	{
		repository.cancel(orders.getStatus(),orders.getOrder_id());
	}
	
	public Collection<Orders> getByStatus(String status)
	{
		return repository.getByStatus(status);
	}
	
	public Collection<Orders> getByDate(Date fromdate, Date todate)
	{
		return repository.getByDate(fromdate, todate);
	}


}
