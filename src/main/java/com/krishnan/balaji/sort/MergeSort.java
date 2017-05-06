package com.krishnan.balaji.sort;

public class MergeSort implements Sort {

	private int[] array;
	private int length;
	private int[] tempMergeArray;

	@Override
	public int[] sort(String name, int[] input) {
		this.array = input;
		this.length = input.length;
		this.tempMergeArray = new int[length];
		doMergeSort(0, length - 1);
		return array;
	}

	private void doMergeSort(int lo, int hi) {
		if (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			doMergeSort(lo, mid);
			doMergeSort(mid + 1, hi);
			mergeParts(lo, mid, hi);
		}

	}

	private void mergeParts(int lo, int mid, int hi) {
		for (int i = lo; i <= hi; i++) {
			tempMergeArray[i] = array[i];
		}
		int i = lo;
		int j = mid + 1;
		int k = lo;
		while (i <= mid && j <= hi) {
			if (tempMergeArray[i] <= tempMergeArray[j]) {
				array[k] = tempMergeArray[i];
				i++;
			} else {
				array[k] = tempMergeArray[j];
				j++;
			}
			k++;
		}
		while( i <= mid){
			array[k] = tempMergeArray[i];
			k++;
			i++;
		}
	}

}
