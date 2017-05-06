package com.krishnan.balaji.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

	public static void main(String[] args) {

		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (a, b) -> b.compareTo(a));

		Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
		Integer converted = converter.convert("123");
		System.out.println(converted);

		// method reference
		converter = Integer::valueOf;
		converted = converter.convert("123");
		System.out.println(converted); // 123

		//
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");

		// We can read final local variables from the outer scope of lambda
		// expressions
		final int num = 1;
		Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
		stringConverter.convert(2);

		// num1 does not have to be declared final
		int num1 = 1;
		stringConverter = (from) -> String.valueOf(from + num1);
		stringConverter.convert(2); // 3

		// However num1 must be implicitly final for the code to compile.
		// The following code does not compile:
		/*
		 * int num2 = 1; stringConverter =(from) -> String.valueOf(from + num2);
		 * num2 = 3;
		 */

		// Predicates are boolean-valued functions of one argument. The
		// interface contains various default methods for composing predicates
		// to complex logical terms (and, or, negate)
		Predicate<String> predicate = (s) -> s.length() > 0;
		predicate.test("foo"); // true
		predicate.negate().test("foo"); // false

		Predicate<Boolean> nonNull = Objects::nonNull;
		Predicate<Boolean> isNull = Objects::isNull;

		Predicate<String> isEmpty = String::isEmpty;
		Predicate<String> isNotEmpty = isEmpty.negate();

		// Functions accept one argument and produce a result. Default methods
		// can be used to chain multiple functions together (compose, andThen).
		Function<String, Integer> toInteger = Integer::valueOf;
		Function<String, String> backToString = toInteger.andThen(String::valueOf);
		backToString.apply("123"); // "123"

		// Suppliers produce a result of a given generic type. Unlike Functions,
		// Suppliers don't accept arguments.
		Supplier<Person> personSupplier = Person::new;
		personSupplier.get(); // new Person

		// Consumers represents operations to be performed on a single input
		// argument.

		Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
		greeter.accept(new Person("Luke", "Skywalker"));

		// Comparators are well known from older versions of Java. Java 8 adds
		// various default methods to the interface.
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");

		comparator.compare(p1, p2); // > 0
		comparator.reversed().compare(p1, p2); // < 0

		/**
		 * Optionals are not functional interfaces, instead it's a nifty utility
		 * to prevent NullPointerException. It's an important concept for the
		 * next section, so let's have a quick look at how Optionals work.
		 * 
		 * Optional is a simple container for a value which may be null or
		 * non-null. Think of a method which may return a non-null result but
		 * sometimes return nothing. Instead of returning null you return an
		 * Optional in Java 8.
		 */
		
		Optional<String> optional = Optional.of("bam");
		optional.isPresent();           // true
		optional.get();                 // "bam"
		optional.orElse("fallback");    // "bam"
		optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"
		
	}

	static int outerStaticNum;
	int outerNum;

	// In constrast to local variables we have both read and write access to
	// instance fields and static variables
	// from within lambda expressions
	void testScopes() {
		Converter<Integer, String> stringConverter1 = (from) -> {
			outerNum = 23;
			return String.valueOf(from);
		};

		Converter<Integer, String> stringConverter2 = (from) -> {
			outerStaticNum = 72;
			return String.valueOf(from);
		};
	}

}
