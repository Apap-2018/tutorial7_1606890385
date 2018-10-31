package com.apap.tutorial7.controller;

import java.util.Arrays;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import com.apap.tutorial7.rest.Setting;

@RestController
@RequestMapping("/model")
public class ModelController {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping()
	private ResponseEntity<String> modelCar(@RequestParam("factory") String factory) throws Exception{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML like Gecko) Chrome/69.0.3497.100 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String path = Setting.modelUrl +"/?cmd=getModels&make="+ factory+"&year="+year;
		//String path = Setting.modelUrl + "/?cmd=getModels&make="+ factory+"&year=2018";
		ResponseEntity<String> response = restTemplate.exchange(path,HttpMethod.GET, entity, String.class);
		return response;
	}
	
/*	@GetMapping(value = "/full")
	private ModelDetail postStatus(@RequestParam ("factory") String factory) throws Exception{
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String path = Setting.modelUrl +"/?cmd=getModels&make="+ factory+"&year="+year;
		
		Object response = restTemplate.exchange(path,HttpMethod.GET, entity, Object.class);
		return response;
	}*/
	
	

}
