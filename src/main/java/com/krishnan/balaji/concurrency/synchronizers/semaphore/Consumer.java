package com.krishnan.balaji.concurrency.synchronizers.semaphore;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Consumer implements Callable<Void> {

	Queue queue;
	boolean stop = false;
	public Consumer(Queue queue) {
		this.queue = queue;
	}

	@Override
	public Void call() throws Exception {
		while(true && !stop){
			TimeUnit.SECONDS.sleep(1);
			queue.conSem.acquire();
			queue.get();
			queue.prodSem.release();	
		}
		
		return null;
	}

}
