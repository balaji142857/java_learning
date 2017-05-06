package com.krishnan.balaji.concurrency.synchronizers.cyclicBarrier;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		int count = Runtime.getRuntime().availableProcessors();
		final Instant[] start = new Instant[1];
		System.out.println("barrier will open when all " + count + " characters reach the stage");
		CyclicBarrier barrier = new CyclicBarrier(count, () -> {
			Instant end = Clock.systemUTC().instant();
			System.out.println("barrier opened in " + Duration.between(start[0],end) +" .");
		});
		ExecutorService es = Executors.newFixedThreadPool(count);
		String[] names = { "warrior", "king", "poet", "athelete", "programmer", "VettiOppicer", "politician", "child" };
		Clock clock = Clock.systemUTC();
		start[0] = clock.instant();
		for (int i = 0; i < count; i++)
			es.submit(new Character(names[i], barrier));
		System.out.println("End of main thread");
		es.awaitTermination(50, TimeUnit.SECONDS);
		es.shutdown();
	}
}
