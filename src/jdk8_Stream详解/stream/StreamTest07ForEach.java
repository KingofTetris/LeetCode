package jdk8_Stream详解.stream;

import java.util.stream.Stream;

public class StreamTest07ForEach {

    public static void main(String[] args) {
        Stream.of("a1", "a2", "a3").forEach(System.out::println);;
    }
}
