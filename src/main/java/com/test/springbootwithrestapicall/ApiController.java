package com.test.springbootwithrestapicall;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiController {
	
	/* GET API CALL */
	@GetMapping("/employees")
	public String getEmployees() {
		System.out.println("inside get employee controller");
		
		String url = "http://dummy.restapiexample.com/api/v1/employees"; // api end point
		RestTemplate restTemplate = new RestTemplate();
		
		String response = restTemplate.getForObject(url, String.class);  // api call using rest client
		return response;
	}
	
	
	/* POST CALL USING HEADER  AND BODY */
	@GetMapping("/getCompanyData/{domainName}")
	public String getResponseFromFullContactApi(@PathVariable String domainName) {
		
		System.out.println("domainName "  +  domainName);
		
	String url = "https://api.fullcontact.com/v3/company.enrich"; // full contact country api end point
	String api_key = "***********************"; // api key of full contact

	HttpHeaders httpHeaders = new HttpHeaders();
	httpHeaders.set("Content-Type", "application/json");
	httpHeaders.set("Authorization", "Bearer " + api_key);

	ResponseEntity<String> responseEntity = null;

	RestTemplate restTemplate = new RestTemplate();

	JSONObject json = new JSONObject();

	json.put("domain", domainName);

	HttpEntity<String> request = new HttpEntity<String>(json.toString(), httpHeaders);

	responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class); // api call using header and body parameter
	String response = responseEntity.getBody();
	return response;
	
	}

}
