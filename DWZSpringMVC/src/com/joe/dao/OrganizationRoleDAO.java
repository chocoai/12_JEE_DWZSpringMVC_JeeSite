package com.joe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.entity.main.OrganizationRole;

/** 
 * 	
 */

public interface OrganizationRoleDAO extends JpaRepository<OrganizationRole, Long> {
	List<OrganizationRole> findByOrganizationId(Long organizationId);
}
