package com.krishnan.balaji.sort;

public class BubbleSort implements Sort {

	@Override
	public int[] sort(String name, int[] input) {
		if (input == null)
			return null;
		int n = input.length;
		int iterationCount = 0;
		while (iterationCount < n) {
			for (int i = 0; i < n; i++) {
				int j = i + 1;
				if (j < n && input[i] > input[j])
					swap(input, i, j);
			}
			iterationCount++;
		}
		return input;
	}

}
