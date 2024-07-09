package com.stock.calculation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stock.calculation.entitys.Holdings;
import com.stock.calculation.entitys.YearAndMonth;


@Repository
@Transactional
public interface HoldingesRepository extends JpaRepository<Holdings, Long> {
	
	@Query(value="select * from holdings where stock_name= ?1 and status= 'UNSOLD' and yearmonth_id=?2 "
			+ "and id=(select max(id) from holdings where stock_name=?1)", nativeQuery = true)
	public Optional<Holdings> findByStockNameAndStatusAndMonth(String stockName, long yearMonth);
	
	@Query(value="select * from holdings where id=?1 and stock_name= ?2 and status= 'UNSOLD'", nativeQuery = true)
	public Optional<Holdings> findByIdandName(long id, String name);
	
	@Query(value="select ifnull(sum((total_stocks * current_prise)-invested_amount),0.0) as profit from holdings where yearmonth_id =?1", nativeQuery = true)
	public double findProfitByMonth(long monthid);
	
	@Query(value="select * from holdings where id=?1 and stock_name= ?2 and status= 'UNSOLD' and yearmonth_id=?3", nativeQuery = true)
	public Optional<Holdings> findByIdStockNameAndStatusAndMonth(long id, String stockName, long yearMonth);
	
	
	@Query(value="select ifnull(sum(invested_amount),0.0) from holdings where yearmonth_id =?1", nativeQuery = true)
	public Double totalInvestmentsBymonth(long monthname_id);
	
	@Query(value="select * from holdings where yearmonth_id=?1", nativeQuery = true)
	public List<Holdings> findByYearMonth(long id);
	
	@Modifying
	@Query(value="UPDATE holdings SET duration = ?1 WHERE status='UNSOLD' and stock_name=?2", nativeQuery = true)
	public int changingDuration(String Stockduration, String stockName);
	
	
	@Query(value="select * from holdings where stock_name= ?1 and yearmonth_id=?2 "
			+ "and id=(select max(id) from holdings where stock_name=?1)", nativeQuery = true)
	public List<Holdings> findByStockNameAndMonth(String stockName, long yearMonth);
	
	
	
	public List<Holdings> findByYearMonth(YearAndMonth yearMonth);
	
	public List<Holdings> findByStockName(String stockName);
}
