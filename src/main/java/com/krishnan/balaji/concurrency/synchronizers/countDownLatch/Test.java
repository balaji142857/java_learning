package com.krishnan.balaji.concurrency.synchronizers.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl = new CountDownLatch(5);
		ExecutorService es = Executors.newFixedThreadPool(4);
		System.out.println("submitting a thread to initiate countdown");
		es.submit(() -> {
			int i=1;
			while(i < 6)
			try {
				TimeUnit.SECONDS.sleep(3);
				cdl.countDown();
				System.out.println("countdown: "+i);
				i++;
			} catch (InterruptedException e) {}
		});
		System.out.println("calling await in main thread");
		cdl.await();
		es.shutdown();
		System.out.println("latch opened, end of main thread");
	}
}