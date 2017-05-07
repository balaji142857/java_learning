package com.krishnan.balaji.concurrency.fj;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Test extends RecursiveTask<Long> {

	static final int SEQUENTIAL_THRESHOLD = 5000;

	int low;
	int high;
	int[] array;

	Test(int[] arr, int lo, int hi) {
		array = arr;
		low = lo;
		high = hi;
	}

	@Override
	protected Long compute() {
		if (high - low <= SEQUENTIAL_THRESHOLD) {
			long sum = 0;
			for (int i = low; i < high; ++i)
				sum += array[i];
			return sum;
		} else {
			int mid = low + (high - low) / 2;
			Test left = new Test(array, low, mid);
			Test right = new Test(array, mid, high);
			left.fork();
			long rightAns = right.compute();
			long leftAns = left.join();
			return leftAns + rightAns;
		}
	}

	
	public static void main(String[] args) {
		int limit = 25000;
		int[] arr = new int[limit];
		for(int i =0; i<limit;i++){
			arr[i]=(int) (Math.random()*limit);
		}
		System.out.println("sum is "+sumArray(arr));
	}
	static long sumArray(int[] array) {
		return ForkJoinPool.commonPool().invoke(new Test(array, 0, array.length));
	}
}
