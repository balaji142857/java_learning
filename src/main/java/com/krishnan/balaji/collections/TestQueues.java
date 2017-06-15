package com.krishnan.balaji.collections;

import java.time.LocalTime;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class TestQueues {
	public static void main(String[] args) throws InterruptedException {

		//remove poll 
		//add offer
		//element peek
		System.out.println("<=========== PriorityQueue ===========>");
		testPriorityQueue();
		System.out.println();
		System.out.println("<=========== BlockingQueue ===========>");
		testBlockingQueue();
		System.out.println();
		System.out.println("<=========== DeQueue ===========>");
		testDeQueue();
		System.out.println();
		System.out.println("<=========== TransferQueue ===========>");
		testTransferQueue();
		System.out.println();
		System.out.println("<=========== SynchronousQueue ===========>");
		testSynchronousQueue();
	}

	private static void testSynchronousQueue() {
		SynchronousQueue<String> sQueue = new SynchronousQueue<>();
		ExecutorService es = Executors.newFixedThreadPool(4);

		//adding three objects with a 10 sec interval:
		es.submit(()-> {
			System.out.println("Thread to offer objects started");
			int[] index = {0};
			for(int i =1;i<3;i++){
				try {
					index[0]++;
					System.out.println(LocalTime.now() + " offering Object some"+index[0]);
					es.submit(() -> {sQueue.offer("some"+index[0]);});
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		es.submit(()-> {
			System.out.println("Thread to poll the queue started");
			for(int i =1;i<3;i++){
				es.submit(() -> {
					System.out.println(LocalTime.now()+" : Attempting to poll queue");
					String resp = sQueue.poll();
					LocalTime respTime = LocalTime.now();
					System.out.println(respTime+" poll retured at "+respTime+ " with value of "+resp);
					System.out.println();
				});
			}
		});
		
		try {
			es.awaitTermination(40, TimeUnit.SECONDS);
			es.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void testTransferQueue() {
		System.out.println("Transfer Queue demo");
		TransferQueue<String> tQueue = new LinkedTransferQueue<>();
		//offer and poll
		tQueue.offer("hello");
		System.out.println(tQueue.poll());
		ExecutorService es  = Executors.newSingleThreadExecutor();
		es.submit(() -> {
			try {
				System.out.println("Thread used to poll from queue entering 10sec sleep.");
				TimeUnit.SECONDS.sleep(10);
				System.out.println("Sleep done, polling");
				System.out.println(tQueue.poll()+" is the transferred String");;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		try {
			System.out.println("Starting a transfer....");
			tQueue.transfer("sampleString");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		es.shutdown();
	}

	private static void testDeQueue() {
		Deque<String> dQueue = new LinkedList<>();
		
	}

	private static void testBlockingQueue() throws InterruptedException {
		BlockingQueue<String> bQueue = new ArrayBlockingQueue<>(30);
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(10);
				bQueue.put("hello");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println("attempting to poll without element");
		String result = bQueue.poll(20,TimeUnit.SECONDS);
		System.out.println("polled value is "+result);
		es.shutdown();
	}

	private static void testPriorityQueue() {
		PriorityQueue<String> pQueue = new PriorityQueue<>(new StringLengthComparator());
		pQueue.add("a");pQueue.add("ab");pQueue.add("abc");
		pQueue.add("jkl");pQueue.add("def");pQueue.add("d");
		pQueue.add("de");pQueue.add("g");pQueue.add("gh");
		pQueue.add("ghi");pQueue.add("j");pQueue.add("jk");
		System.out.println(pQueue.comparator());
		System.out.println(pQueue);
		while (pQueue.size() != 0) 
			System.out.print(pQueue.poll() + ", ");
	}
	
}