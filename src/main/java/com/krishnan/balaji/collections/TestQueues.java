package com.krishnan.balaji.collections;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TestQueues {
	public static void main(String[] args) {
		//queue
		
		Queue<Integer> queue = new LinkedList<>();
		int  i = 0;
		do {
			i++;
			queue.offer(i);
		} while (i < 10);
		queue.offer(1);
		System.out.println(queue);
		queue.clear();
		System.out.println(queue.poll());
		System.out.println(queue.peek());
		System.out.println();
		System.out.println();
		
		
		//prioroty queue
		i = 10;
		doSomeThing(i);
		System.out.println("afterIncrementing: "+i);
		PriorityQueue<String> pQueue = new PriorityQueue<>(new StringLengthComparator());
		pQueue.add("a");
		pQueue.add("ab");
		pQueue.add("abc");
		pQueue.add("jkl");
		pQueue.add("def");
		pQueue.add("d");
		pQueue.add("de");
		pQueue.add("g");
		pQueue.add("gh");
		pQueue.add("ghi");
		pQueue.add("j");
		pQueue.add("jk");
		System.out.println(pQueue.comparator());
		System.out.println(pQueue);
		while (pQueue.size() != 0)
        {
            System.out.print(pQueue.poll()+", ");
        }
		System.out.println();
		
		
	}

	private static void doSomeThing(int i) {
		i++;
	}
}
