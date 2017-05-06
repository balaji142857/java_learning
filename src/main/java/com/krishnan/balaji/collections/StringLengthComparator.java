package com.krishnan.balaji.collections;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {

	@Override
	public int compare(String x, String y) {
		return x.length()-y.length();
	}

}
