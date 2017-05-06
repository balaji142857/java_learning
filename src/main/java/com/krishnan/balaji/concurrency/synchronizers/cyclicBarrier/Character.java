package com.krishnan.balaji.concurrency.synchronizers.cyclicBarrier;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Character implements Callable<Void>{

	String name;
	CyclicBarrier cb;

	public Character(String name, CyclicBarrier barrier) {
		this.name=name;
		this.cb = barrier;
	}

	@Override
	public Void call() throws Exception {
		long sleepTime = (long) (Math.random()*40);
		System.out.println(name +" will reach stage after "+sleepTime+" seconds");
		TimeUnit.SECONDS.sleep(sleepTime);
		System.out.println(name +" has reached the stage");
		cb.await();
		return null;
	}

}
