package com.stock.calculation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stock.calculation.entitys.Alert;

@Repository
@Transactional
public interface AlertRepo extends JpaRepository<Alert, Long> {
	
	List<Alert> findByAlertname(String alertname);
	
	@Modifying
	@Query(value="update alert set seen='seen' where seen='unseen'",nativeQuery = true)
	public void updatestatus();
}
