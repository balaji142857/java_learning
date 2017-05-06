package com.krishnan.balaji.lambdas;

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}