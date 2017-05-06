package com.krishnan.balaji.streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
/**
 * Source code is from:
 * http://winterbe.com/posts/2014/03/16/java-8-tutorial/
 * 
 *
 */
public class Test {
	public static void main(String[] args) {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		// Collections in Java 8 are extended so you can simply create streams
		// either by calling Collection.stream() or Collection.parallelStream()

		// Filter
		// Filter accepts a predicate to filter all elements of the stream. This
		// operation is intermediate which enables us to call another stream
		// operation (forEach) on the result. ForEach accepts a consumer to be
		// executed for each element in the filtered stream. ForEach is a
		// terminal operation. It's void, so we cannot call another stream
		// operation.

		stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
		// "aaa2", "aaa1"

		// Sorted
		// Sorted is an intermediate operation which returns a sorted view of
		// the stream. The elements are sorted in natural order unless you pass
		// a custom Comparator.

		stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
		// "aaa1", "aaa2"
		// Keep in mind that sorted does only create a sorted view of the stream
		// without manipulating the ordering of the backed collection. The
		// ordering of stringCollection is untouched:
		System.out.println(stringCollection);
		// ddd2, aaa2, bbb1, aaa1, bbb3, ccc, bbb2, ddd1

		// Map

		// The intermediate operation map converts each element into another
		// object via the given function. The following example converts each
		// string into an upper-cased string. But you can also use map to
		// transform each object into another type. The generic type of the
		// resulting stream depends on the generic type of the function you pass
		// to map.

		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);
		// "DDD2", "DDD1", "CCC", "BBB3", "BBB2", "AAA2", "AAA1"

		// Match
		// Various matching operations can be used to check whether a certain
		// predicate matches the stream. All of those operations are terminal
		// and return a boolean result.

		boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

		System.out.println(anyStartsWithA); // true

		boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ); // true

		// Count
		// Count is a terminal operation returning the number of elements in the
		// stream as a long.

		long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();

		System.out.println(startsWithB); // 3

		// Reduce
		// This terminal operation performs a reduction on the elements of the
		// stream with the given function. The result is an Optional holding the
		// reduced value.

		Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
		// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"

		// Parallel Streams
		/*
		 * As mentioned above streams can be either sequential or parallel.
		 * Operations on sequential streams are performed on a single thread
		 * while operations on parallel streams are performed concurrent on
		 * multiple threads. The following example demonstrates how easy it is
		 * to increase the performance by using parallel streams. First we
		 * create a large list of unique elements:
		 */

		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}

		// Now we measure the time it takes to sort a stream of this collection.
		// Sequential Sort

		long t0 = System.nanoTime();

		long count = values.stream().sorted().count();
		System.out.println(count);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));
		// sequential sort took: 899 ms

		// Parallel Sort

		t0 = System.nanoTime();

		count = values.parallelStream().sorted().count();
		System.out.println(count);
		t1 = System.nanoTime();
		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("parallel sort took: %d ms", millis));
		// parallel sort took: 472 ms
		// As you can see both code snippets are almost identical but the
		// parallel sort is roughly 50% faster. All you have to do is change
		// stream() to parallelStream().

		// Map
		// As already mentioned maps don't support streams. Instead maps now
		// support various new and useful methods for doing common tasks.

		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val" + i);
		}

		map.forEach((id, val) -> System.out.println(val));
		// The above code should be self-explaining: putIfAbsent prevents us
		// from writing additional if null checks; forEach accepts a consumer to
		// perform operations for each value of the map.

		// This example shows how to compute code on the map by utilizing
		// functions:

		map.computeIfPresent(3, (num, val) -> val + num);
		map.get(3); // val33

		map.computeIfPresent(9, (num, val) -> null);
		map.containsKey(9); // false

		map.computeIfAbsent(23, num -> "val" + num);
		map.containsKey(23); // true

		map.computeIfAbsent(3, num -> "bam");
		map.get(3); // val33
		// Next, we learn how to remove entries for a a given key, only if it's
		// currently mapped to a given value:

		map.remove(3, "val3");
		map.get(3); // val33

		map.remove(3, "val33");
		map.get(3); // null
		// Another helpful method:

		map.getOrDefault(42, "not found"); // not found
		// Merging entries of a map is quite easy:

		map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
		map.get(9); // val9

		map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
		map.get(9); // val9concat
	}
}
