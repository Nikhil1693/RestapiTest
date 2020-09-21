package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//GET Method Without Headers:

	public CloseableHttpResponse get(String URL) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient=HttpClients.createDefault();
		HttpGet get = new HttpGet(URL);
		
	CloseableHttpResponse response=httpClient.execute(get);//hit the get URL
	
	return response;
	
	}
	
	
	//GET Method With Headers:

		public CloseableHttpResponse get(String URL,HashMap<String,String> hashmap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient=HttpClients.createDefault();
			HttpGet get = new HttpGet(URL);
			
			for(Map.Entry<String,String> entry:hashmap.entrySet()) {
				
				get.addHeader(entry.getKey(),entry.getValue());
			}
			
			
		CloseableHttpResponse response=httpClient.execute(get);//hit the get URL
		
		return response;
		
		}
		
	//POST Method
		
		public CloseableHttpResponse post(String URL,String entityString,HashMap<String,String> hashmap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient=HttpClients.createDefault();
			
			HttpPost post=new HttpPost(URL);// http post request
			post.setEntity(new StringEntity(entityString)); //for payload
			
			
			//for header
			for(Map.Entry<String, String> entry: hashmap.entrySet()) {
				
				post.addHeader(entry.getKey(),entry.getValue());
			}
			
			CloseableHttpResponse response=httpClient.execute(post);
			
			return response;
		}

}
