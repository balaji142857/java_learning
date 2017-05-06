package com.krishnan.balaji.sort;

public class SelectionSort implements Sort {

	@Override
	public int[] sort(String name, int[] input) {
		for (int i = 0; i < input.length; i++) {
			int minIndex = i;
			for (int j = i; j < input.length; j++) {
				if(input[j] < input[minIndex])
					minIndex= j;
			}
			if(minIndex!=i)
				swap(input,minIndex,i);
		}
		return input;
	}

}
//8 2 4 7 6 1
/*
 * 
 * iterate over the elements - find the min, keep iteration count
 * swap the min element with the element at position of iteration count
 * 
 * 
 */