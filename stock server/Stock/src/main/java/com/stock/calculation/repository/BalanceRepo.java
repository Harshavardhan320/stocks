package com.stock.calculation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stock.calculation.entitys.BankAccount;

@Repository
public interface BalanceRepo extends JpaRepository<BankAccount, Long>{
	
	@Query(value="select * from Bankaccount where id= (select max(id) from Bankaccount)", nativeQuery = true)
	public BankAccount Totalbalance();
	
}
