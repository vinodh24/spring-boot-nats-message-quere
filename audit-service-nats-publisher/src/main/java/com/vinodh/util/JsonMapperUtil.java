package com.vinodh.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonMapperUtil {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(JsonMapperUtil.class); 

	public static String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}

	public static <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		return mapper.readValue(json, clazz);
	}

	public static Map<String, Object> convertObjectToSingleMap(String object) {	
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		Map<String, Object> data = null;
		try {
			data = mapper.readValue(object, new TypeReference<HashMap<String, Object>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return data;		
	}

	public static String inputPutFileToJsonString(String fileName)throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new FileReader(new FileSystemResource(fileName).getFile()));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		line = null;br=null;
		return sb.toString();
	}

}
