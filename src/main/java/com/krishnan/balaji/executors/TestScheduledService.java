package com.krishnan.balaji.executors;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduledService {
	
	public static void main(String[] args) {
		ScheduledExecutorService es = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
		Runnable myRunnable = () -> {
			try {
				System.out.println(LocalTime.now()+" started");
				TimeUnit.SECONDS.sleep(15);
				System.out.println(LocalTime.now()+" completed");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		es.scheduleWithFixedDelay(myRunnable, 5, 10, TimeUnit.SECONDS);
		try {
			TimeUnit.SECONDS.sleep(100);
			es.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
