package com.krishnan.balaji.concurrency.synchronizers.semaphore;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Producer implements Callable<Integer> {
	
	Queue queue;
	boolean stop=false;
	public Producer(Queue queue){
		this.queue = queue;
	}
	
	@Override
	public Integer call() throws Exception {
		while(true && !stop){
			TimeUnit.SECONDS.sleep(1);
			queue.prodSem.acquire();
			queue.put();
			queue.conSem.release();	
		}
		return null;
	}
}