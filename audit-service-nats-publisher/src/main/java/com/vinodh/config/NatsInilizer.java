package com.vinodh.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;

@Configuration
public class NatsInilizer implements InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(NatsInilizer.class);
	
	public static Connection natsConnection;
	
	private String serverURI;
	
	private void initConnection(String uri) {
		try {
			Options options = new Options.Builder()
					.errorCb(ex -> log.error("Connection Exception: ", ex))
					.disconnectedCb(event -> log.error("Channel disconnected: {}", event.getConnection()))
					.reconnectedCb(event -> log.error("Reconnected to server: {}", event.getConnection()))
					.build();
			natsConnection=Nats.connect(uri, options);
			log.info("Connected Nats Server... "+natsConnection);
		} catch (IOException ioe) {
			log.error("Error connecting to NATs! ", ioe);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.serverURI = "jnats://10.0.16.76:4222";
		initConnection(serverURI);
	}

}
