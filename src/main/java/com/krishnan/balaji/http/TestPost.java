package com.krishnan.balaji.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestPost {
	public static void main(String[] args) {
		
		CloseableHttpResponse response;
		HttpPost postRequest = null;
		CloseableHttpClient httpclient = null;
		URI uri;
		try {
			uri = new URIBuilder().setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath("/web/http/basic")
					.build();
			
			postRequest = new HttpPost(uri);
			postRequest.addHeader("accept-encoding","deflate");
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			urlParameters.add(new BasicNameValuePair("username", "balaji"));
			urlParameters.add(new BasicNameValuePair("passwd", "krishnan"));
			postRequest.setEntity(new UrlEncodedFormEntity(urlParameters));
			
			httpclient = HttpClients.createDefault();
			response = httpclient.execute(postRequest);
			System.out.println("Response Code : "+ response.getStatusLine().getStatusCode());

			HttpEntity entity = response.getEntity();
			System.out.println(">START OF RESPONSE");
			String responseTxt = EntityUtils.toString(entity,"UTF-8");
			System.out.println(responseTxt);
			System.out.println("<END OF RESPONSE");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
