package com.krishnan.balaji.concurrency.synchronizers.semaphore;

import java.util.concurrent.Semaphore;

public class Queue {

	Semaphore prodSem, conSem;
	int value;

	public Queue() {
		prodSem = new Semaphore(1);
		conSem = new Semaphore(0);
		value = 0;
	}

	public void get() {
		System.out.println("getting value "+value);
	}

	public void put() {
		value++;
		System.out.println("putting value "+value);
	}
}