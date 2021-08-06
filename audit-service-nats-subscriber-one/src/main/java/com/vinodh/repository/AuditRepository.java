package com.vinodh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinodh.model.AuditLog;

public interface AuditRepository extends JpaRepository<AuditLog, Integer> {

	public List<AuditLog> findByUsername(String username);

}
