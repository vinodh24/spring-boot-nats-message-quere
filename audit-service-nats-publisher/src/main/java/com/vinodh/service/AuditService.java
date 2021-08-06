package com.vinodh.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.vinodh.config.NatsInilizer;
import com.vinodh.controller.AuditController;
import com.vinodh.model.AuditLog;
import com.vinodh.util.JsonMapperUtil;

@Service
public class AuditService {
	
	private static final Logger log = LoggerFactory.getLogger(AuditController.class); 
	
	public AuditLog saveUser(AuditLog auditLog) {
		publishMessage("com.velankani", auditLog);
		return auditLog;	
	}

	public List<AuditLog> findByUsername(String username) {
		return null;
	}
	
	public void publishMessage(String topic, AuditLog auditLog) {
        try {
        	NatsInilizer.natsConnection.publish(topic,JsonMapperUtil.mapToJson(auditLog).getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioe) {
            log.error("Error publishing message: {} to {} ", auditLog, topic, ioe);
        }
    }

}
