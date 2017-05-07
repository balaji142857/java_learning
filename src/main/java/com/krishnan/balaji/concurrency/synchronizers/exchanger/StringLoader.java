package com.krishnan.balaji.concurrency.synchronizers.exchanger;

import java.util.concurrent.Callable;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

public class StringLoader implements Callable<Void> {

	Exchanger<Integer> ex;
	boolean stop = false;

	public StringLoader(Exchanger<Integer> ex) {
		this.ex = ex;
	}

	@Override
	public Void call() throws Exception {
		while (true && !stop) {
			//sleep for a few seconds so output does not get generated very fast
			TimeUnit.SECONDS.sleep(2);
			int value = (int) (Math.random()*100);
			System.out.println("loader exchanging "+ value);
			value = ex.exchange(value);
			System.out.println("loader got "+value +" from unloader ");
		}
		return null;
	}

}
