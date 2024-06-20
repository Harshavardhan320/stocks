package com.stock.calculation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stock.calculation.entitys.YearAndMonth;


@Repository
public interface YearAndMonthRepo extends JpaRepository<YearAndMonth, Long> {
	
	List<YearAndMonth> findByYearMonthName(String yearMonthName);
}
