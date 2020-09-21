package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	
	TestBase tb;
	RestClient client;
	String serviceURL;
	String apiURL;
	String URL;
	CloseableHttpResponse response;
	
	@BeforeMethod
	public void setUp(){
		
		tb=new TestBase();
		serviceURL=prop.getProperty("URL");
		apiURL=prop.getProperty("serviceURL");
		
		URL=serviceURL+apiURL;
		
	
	}
	
	@Test
	public void getTestWithoutHeaders() throws ClientProtocolException, IOException {
		client=new RestClient();
		response=client.get(URL);
		//a. Status Code:
		int statuscode=response.getStatusLine().getStatusCode();//get the status code
		System.out.println("Status code-->"+statuscode);
		
		Assert.assertEquals(statuscode,200,"Status code is not 200" );
		
		//b.JSON String
		String responseString=EntityUtils.toString(response.getEntity(),"UTF-8");
		
		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("Response JSON from API -->"+responseJson);
		
		//per_page
		String perPageValue=TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is-->" +perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//total
		
		String total=TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of total is -->"+total);
		Assert.assertEquals(Integer.parseInt(total), 12);
		
		//Get value from JSON Array
		
		String lastname=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstname=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastname);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstname);
		
			
		
		//c.All Headers
		Header[] headersArray= response.getAllHeaders();
		
		HashMap<String, String> allHeaders=new HashMap<String,String>();
		
		for(Header header:headersArray) {
		
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--> "+allHeaders);
	}
	
	@Test
	public void getTestWithHeaders() throws ClientProtocolException, IOException {
		client=new RestClient();
		
		HashMap<String, String> headerMap=new HashMap<String,String>();
		
		headerMap.put("Content-Type", "application/json");
		
		
		
		response=client.get(URL);
		//a. Status Code:
		int statuscode=response.getStatusLine().getStatusCode();//get the status code
		System.out.println("Status code-->"+statuscode);
		
		Assert.assertEquals(statuscode,200,"Status code is not 200" );
		
		//b.JSON String
		String responseString=EntityUtils.toString(response.getEntity(),"UTF-8");
		
		JSONObject responseJson=new JSONObject(responseString);
		System.out.println("Response JSON from API -->"+responseJson);
		
		//per_page
		String perPageValue=TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("value of per page is-->" +perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//total
		
		String total=TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("value of total is -->"+total);
		Assert.assertEquals(Integer.parseInt(total), 12);
		
		//Get value from JSON Array
		
		String lastname=TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstname=TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println(lastname);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(firstname);
		
			
		
		//c.All Headers
		Header[] headersArray= response.getAllHeaders();
		
		HashMap<String, String> allHeaders=new HashMap<String,String>();
		
		for(Header header:headersArray) {
		
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array--> "+allHeaders);
	}
	
	

}
