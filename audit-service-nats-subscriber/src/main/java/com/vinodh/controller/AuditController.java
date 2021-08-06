package com.vinodh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinodh.model.AuditLog;
import com.vinodh.service.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController {
	
	private static final Logger log = LoggerFactory.getLogger(AuditController.class);

	@Value("application.version")
	private String apIVersion;
	
	@Autowired
	private AuditService auditService;

	@PostMapping(path= "/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<AuditLog> addUser( @RequestBody AuditLog auditLog)
	{
		log.info("addUser save in verison :"+apIVersion);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(auditService.saveUser(auditLog));
	}
	
	@GetMapping(path= "/findByUsername/{username}")
	public List<AuditLog> findByUsername( @PathVariable(name = "username") String username)
	{
		log.info("findByUsername in verison :"+apIVersion);
		return auditService.findByUsername(username);
	}
	
	@GetMapping(path= "/saveAuditLogBySubscriber")
	public AuditLog saveAuditLogBySubscriber()
	{
		log.info("saveAuditLogBySubscriber");
		return auditService.saveAuditLogBySubscriber();
	}

}
