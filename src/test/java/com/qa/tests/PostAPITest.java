package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {
	
	TestBase tb;
	RestClient client;
	String serviceURL;
	String apiURL;
	String URL;
	CloseableHttpResponse response;
	
	@BeforeMethod
	public void setup() {
		tb=new TestBase();
		serviceURL=prop.getProperty("URL");
		apiURL=prop.getProperty("serviceURL");
		
		URL=serviceURL+apiURL;
		
		}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		client=new RestClient();
		
		HashMap<String,String> headerMap=new HashMap<String,String>();
		
		headerMap.put("Content-Type", "application/json");
		
		//jacksonAPI
		
		ObjectMapper mapper=new ObjectMapper();
		Users users=new Users("Nikhil","Engineer");
		
		//object to JSON file
		
		mapper.writeValue(new File("C:\\Users\\50024558\\eclipse-workspace\\restapitest\\src\\main\\java\\com\\qa\\data\\users.json"),users);
		
		// object to JSON in String(Marshalling)
		
		String UsersJsonString=mapper.writeValueAsString(users);
		//System.out.println(UsersJsonString);
		
		response=client.post(URL,UsersJsonString,headerMap);
		
		
		//Validate response from API
		//1. status code
		int statusCode=response.getStatusLine().getStatusCode();
		
		Assert.assertEquals(statusCode, 201);
		
		//2. JSON String
		
		String responseString=EntityUtils.toString(response.getEntity(),"UTF-8");
		
		JSONObject responseJson=new JSONObject(responseString);
		
		System.out.println("Response from API is-->"+responseJson);
		
		//json to java object(Unmarshalling)
		
		Users userResObj=mapper.readValue(responseString, Users.class);
		System.out.println(userResObj);
		
		Assert.assertTrue(users.getName().equals(userResObj.getName()));
		
		Assert.assertTrue(users.getJob().equals(userResObj.getJob()));
		
		System.out.println(userResObj.getId());
		System.out.println(userResObj.getCreatedAt());
		
		
		
	}
	
	
	
	
	

}
