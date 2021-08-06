package com.vinodh.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinodh.config.NatsInilizer;
import com.vinodh.model.AuditLog;
import com.vinodh.repository.AuditRepository;
import com.vinodh.util.JsonMapperUtil;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.Subscription;
import io.nats.client.SyncSubscription;

@Service
public class AuditService {

	private static final Logger log = LoggerFactory.getLogger(AuditService.class); 

	@Autowired
	private AuditRepository auditRepository;

	public AuditLog saveUser(AuditLog auditLog) {
		log.info("auditLog saveUser : " + auditLog);
		return auditRepository.save(auditLog);	
	}

	public AuditLog saveAuditLogBySubscriber() {
		log.info("auditLog saveAuditLogBySubscriber Service : ");
		return getSynchronousMessage();	
	}

	public List<AuditLog> findByUsername(String username) {
		getSynchronousMessage();
		return auditRepository.findByUsername(username);
	}

	public AuditLog getSynchronousMessage() {
		SyncSubscription syncSub = NatsInilizer.natsConnection.subscribeSync("com.velankani");
		Message message;
		try {
			message = syncSub.nextMessage();
			String auditString = new String(message.getData(), StandardCharsets.UTF_8);
			AuditLog auditLog=JsonMapperUtil.mapFromJson(auditString, AuditLog.class);
			log.info("Received message on {}", auditString);
			return auditRepository.save(auditLog);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getASynchronousMessage() {
		NatsInilizer.natsConnection.subscribe("com.velankani",new MessageHandler() {
			@Override
			public void onMessage(Message message) {
				String auditString = new String(message.getData(), StandardCharsets.UTF_8);
				try {
					AuditLog auditLog=JsonMapperUtil.mapFromJson(auditString, AuditLog.class);
					auditLog=auditRepository.save(auditLog);
					log.info("auditLOg db object"+auditLog);
				} catch (IOException e) {
					e.printStackTrace();
				}
				log.info("Received message on {}", auditString);
			}
		});
	}

	public void publish(Message msg) {
		try {
			NatsInilizer.natsConnection.publish(msg);
		} catch (Exception ex) {
			log.error("Exception publishing: " + ex.getMessage());
			log.debug("Exception: " + ex);
		}
	}

	public void unSubscribe(String topic) {
		try {
			Subscription sub = NatsInilizer.natsConnection.subscribe(topic);
			sub.unsubscribe();
		} catch (Exception ex) {
			log.error("Exception unSubscribe: " + ex.getMessage());
			log.debug("Exception: " + ex);
		}
	}


}
