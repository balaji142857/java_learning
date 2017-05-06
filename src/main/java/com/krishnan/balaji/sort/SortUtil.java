package com.krishnan.balaji.sort;

public class SortUtil {
	static int[] data = null;

	public static void main(String[] args) {
		data = new int[40];
		for (int i = 0; i < data.length; i++) {
			data[i] = (int) (100 * Math.random());
		}
		Sort[] sortingAlgorithms = loadAlogrithms();
		for (Sort algorithm : sortingAlgorithms) {
			String name = algorithm.getClass().getName();
			algorithm.display(name, algorithm.sort(name, clone(data)));
		}
	}

	private static int[] clone(int[] data) {
		int[] clone = new int[data.length];
		System.arraycopy(data, 0, clone, 0, data.length);
		return clone;
	}

	private static Sort[] loadAlogrithms() {
		Sort[] algos = new Sort[] { 
				new BubbleSort(), 
				new SelectionSort(),
				new InsertionSort(),
				new MergeSort(),
				new QuickSort()};
		return algos;
	}
}
