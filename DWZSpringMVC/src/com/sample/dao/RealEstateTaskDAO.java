package com.sample.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sample.entity.RealEstateTask;

public interface RealEstateTaskDAO extends JpaRepository<RealEstateTask, Long>{
	Page<RealEstateTask> findByTaskNameContaining(String taskName, Pageable pageable);
}
