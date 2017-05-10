package com.krishnan.balaji.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class TestGet {
	public static void main(String[] args) {
		
		CloseableHttpResponse response;
		HttpGet httpget = null;
		CloseableHttpClient httpclient = null;
		// alternate way to construct the request is using the URI
		URI uri;
		try {
			uri = new URIBuilder().setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath("/web/http/basic")
					.setParameter("param1", "first Parameter")
					.setParameter("param2", "second Parameter")
					.build();
			httpget = new HttpGet(uri);
			httpget.addHeader("accept-encoding","deflate");
			//setting up proxy 
			/*HttpHost proxy = new HttpHost("19.12.16.64", 83, HttpHost.DEFAULT_SCHEME_NAME);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			httpclient = HttpClients.custom()
             .setRoutePlanner(routePlanner)
             .build();*/
			
			httpclient = HttpClients.createDefault();

			response = httpclient.execute(httpget);
			//HttpEntity entity = response.getEntity();
			System.out.println(">START OF RESPONSE");
			/*String responseTxt = EntityUtils.toString(entity,"UTF-8");
			System.out.println(responseTxt);*/
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));
				String line = "";
				while ((line = rd.readLine()) != null) {
					System.out.println(line);
				}
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
