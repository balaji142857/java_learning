package com.krishnan.balaji.sort;

/**
 * iterate over the elements, move the elements to their correct positions in
 * the iterations
 * 
 * @author balaji
 *
 */
public class InsertionSort implements Sort {

	@Override
	public int[] sort(String name, int[] input) {
		int n = input.length;
		for (int j = 1; j < n; j++) {
			int key = input[j];
			int i = j - 1;
			while ((i > -1) && (input[i] > key)) {
				input[i+1] = input[i];
				i--;
			}
			input[i+1] = key;
		}
		return input;
	}
}