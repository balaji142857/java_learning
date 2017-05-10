package com.krishnan.balaji.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;

public class Test {
	public static void main(String[] args) {
		
		/*
		 * HttpGet httpget = new HttpGet(
		 * "http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq="
		 * );
		 */
		CloseableHttpResponse response;
		HttpGet httpget = null;
		CloseableHttpClient httpclient = null;
		// alternate way to construct the request is using the URI
		URI uri;
		try {
			uri = new URIBuilder().setScheme("http")
					.setHost("www.google.com")
					.setPath("/search")
					.setParameter("hl", "en")
					.setParameter("q", "httpclient")
					.setParameter("btnG", "Google Search")
					.setParameter("aq", "f")
					.setParameter("oq", "")
					.build();
			httpget = new HttpGet(uri);
			
			//setting up proxy 
			HttpHost proxy = new HttpHost("19.12.16.64", 83, HttpHost.DEFAULT_SCHEME_NAME);
			DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
			httpclient = HttpClients.custom()
             .setRoutePlanner(routePlanner)
             .build();
			//httpclient = HttpClients.createDefault();

			response = httpclient.execute(httpget);
			response.close();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				BufferedReader reader = null ;
				try {
					reader = new BufferedReader(new InputStreamReader(instream));
					String line = reader.readLine();
					while(line != null){
						System.out.println(line);
						line=reader.readLine();
					}
				} finally {
					reader.close();
				}

			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
