package com.krishnan.balaji.concurrency.synchronizers.exchanger;

import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class StringUnloader implements Callable<Void> {

	Exchanger<Integer> ex;
	boolean stop = false;

	public StringUnloader(Exchanger<Integer> ex) {
		this.ex = ex;
	}

	@Override
	public Void call() throws Exception {
		while (true && !stop) {
			TimeUnit.SECONDS.sleep(2);
			int value = (int) (Math.random()*100);
			System.out.println("unloader exchanging "+ value);
			value = ex.exchange(value);
			System.out.println("unloader got "+value +" from loader ");
		}
		return null;
	}
}