package com.example.CartProject.repository;

import java.sql.Date;
import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.CartProject.model.Orders;

public interface OrderRepository extends CrudRepository<Orders, Integer>
{
		
	@Query("UPDATE Orders set status = ?1 where order_id = ?2")
	@Transactional
	@Modifying
	int cancel(String status,int order_id);
	
	@Query("from Orders where status = ?1")
	Collection<Orders> getByStatus(String status);
	
	@Query("from Orders where date between ?1 and ?2 ")
	Collection<Orders> getByDate(Date fromdate, Date todate);
	
	
}
