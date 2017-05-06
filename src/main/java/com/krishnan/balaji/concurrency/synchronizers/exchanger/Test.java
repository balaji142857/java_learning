package com.krishnan.balaji.concurrency.synchronizers.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {


	public static void main(String[] args) throws InterruptedException {
		Exchanger<Integer> ex = new Exchanger<>();
		ExecutorService es = Executors.newFixedThreadPool(2);
		StringUnloader unLoader = new StringUnloader(ex);
		StringLoader loader = new StringLoader(ex);
		es.submit(unLoader);
		es.submit(loader);
		es.awaitTermination(120, TimeUnit.SECONDS);
		loader.stop=true;
		unLoader.stop=true;
		es.shutdown();
	}
	
}
