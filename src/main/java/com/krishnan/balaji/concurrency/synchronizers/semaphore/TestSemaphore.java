package com.krishnan.balaji.concurrency.synchronizers.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestSemaphore {

	public static void main(String[] args) throws InterruptedException {
		Queue queue = new Queue();
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		es.submit(producer);
		es.submit(consumer);
		TimeUnit.SECONDS.sleep(25);
		producer.stop=true;
		consumer.stop=true;
		es.shutdown();
	}
}
