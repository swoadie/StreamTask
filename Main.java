package org.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.testng.AssertJUnit.assertEquals;

public class Main {
    //The method takes a list of names. Returns a string of the form "1. Ivan, 3. Peter ...", only with names on odd indices, respectively.
    public class OddIndices  {
        public static String formatNames(List<String> names) {
        return IntStream.range(0, names.size()).filter((i) -> i % 2 != 0).mapToObj((i) -> i + 1 + ". " + names.get(i)).collect(Collectors.joining(", "));
    }
    }

    @Test
    public void testFormatNamesWithEmptyList() {
        List<String> names = List.of();
        String expected = "";

        String result = OddIndices.formatNames(names);

        assertEquals(expected, result);
    }
    @Test
    public void testFormatNames() {
        List<String> names = List.of("Margo", "Ksani", "Eva", "Anna", "Julia");
        String expected = "2. Ksani, 4. Anna";

        String result = OddIndices.formatNames(names);

        assertEquals(expected, result);
    }
   @Test
    public void testFormatWithOneName() {
        List<String> names = List.of("Eva");
        String expected = "";

        String result = OddIndices.formatNames(names);


        assertEquals(expected, result);
    }

    //The method map list of string to uppercase and then sort descending.

    public class ToUppercase  {
        public static String sortedUppercase(List<String> string) {
            List<String> uppercaseString = string.stream().map(String::toUpperCase).collect(Collectors.toList());
            Collections.sort(uppercaseString, Collections.reverseOrder());
            return uppercaseString.toString();
        }
    }

    @Test
    public void testStringToUppercase() {
        List<String> string = List.of("Margo", "Ksani", "Eva", "Anna", "Julia");
        String expected = "[MARGO, KSANI, JULIA, EVA, ANNA]";

        String result = ToUppercase.sortedUppercase(string);

        assertEquals(expected, result);
    }
    @Test
    public void testStringToUppercaseWithEmptyList() {
        List<String> string = List.of();
        String expected = "[]";

        String result = ToUppercase.sortedUppercase(string);

        assertEquals(expected, result);
    }

    @Test
    public void testStringToUppercaseWithSameSign() {
        List<String> string = List.of("micro", "move", "margo", "mesh");
        String expected = "[MOVE, MICRO, MESH, MARGO]";

        String result = ToUppercase.sortedUppercase(string);

        assertEquals(expected, result);
    }

    //Given and collection = Arrays.asList ("1, 2, 0", "4, 5") From the collection get all the numbers listed, separated by commas from all the elements
    public class CollectionNumbers  {
        public static String collectionNum (List<String> numbers) {
            String separatedNum = numbers.stream().flatMap(r -> Arrays.stream(r.split(", "))).collect(Collectors.joining(", "));
            return separatedNum;
        }
    }

    @Test
    public void testSeparatedNumbers() {
        List<String> string = Arrays.asList("1, 2, 0", "4, 5");
        String expected = "1, 2, 0, 4, 5";

        String result = CollectionNumbers.collectionNum(string);

        assertEquals(expected, result);

    }
    @Test
    public void testStringNumbersWithoutCommas() {
        List<String> string = Arrays.asList("1. 2. 0", "4. 5");
        String expected = "1. 2. 0, 4. 5";

        String result = CollectionNumbers.collectionNum(string);

        assertEquals(expected, result);

    }

    @Test
    public void testSeparatedNumnersInEmptyList() {
        List<String> string = Arrays.asList();
        String expected = "";

        String result = CollectionNumbers.collectionNum(string);

        assertEquals(expected, result);
    }


    /*Using Stream.iterate, make an infinite stream of random numbers — not by calling Math.random but by directly
     implementing a linear congruential generator. In such a generator, you start with x[0] = seed and then produce
     x[n + 1] = 1 (a x[n] + c) % m, for appropriate values of a, c, and m. You should implement a method with parameters
     a, c, m, and seed that yields a Stream. Try out a = 25214903917, c = 11, and m = 2^48. */

    public static class CongruentialGenerator {
        public long a = 25214903917L;
        public long c = 11;
        public  long m = (long) Math.pow(2, 48);
        private long seed;

        public CongruentialGenerator(long seed) {
            this.seed = seed;
        }
        public Stream<Long> randomInfiniteStream() {
            return Stream.iterate(seed, s->(a * s + c) % m );
        }

    }

   /* @Test
    public void testCongruentialGenerator {
        long seed = 4;
        CongruentialGenerator generator = new CongruentialGenerator(seed);
        Stream<Long> stream = generator.randomInfiniteStream();
        Long expected = "4 +
                100859615679 +
                -21151008026530 +
                -179171934770159";

    }*/
    /* Write a method public static Stream zip(Stream first, Stream second) that alternates elements from the stream
    first and second, stopping when one of them runs out of elements.*/

    public static class StreamZip {
        public static <T> Stream<T> zipV1(Stream<T> first, Stream<T> second) {
            Iterator<T> i1 = first.iterator();
            Iterator<T> i2 = second.iterator();

            Stream.Builder<T> builder = Stream.builder();

            while (i1.hasNext() & i2.hasNext()) {
                builder.accept(i1.next());
                builder.accept(i2.next());
            }

            return builder.build();
        }}
    @Test
    public void testStreamZip() {
       Stream<Integer> first = Stream.of(1, 2, 3, 4);
       Stream<Integer> second = Stream.of(5, 6, 7, 8);
       Stream<Integer> result = StreamZip.zipV1(first, second);

       List<Integer> expected = List.of(1, 5, 2, 6, 3, 7, 4, 8);

       assertEquals(expected, result.collect(Collectors.toList()));

    }

    public static void main(String[] args) {
        /* List<String> names = List.of("Margo", "Ksani", "Eva", "Anna", "Julia");
        String formattedNames = OddIndices.formatNames(names);
        System.out.println(formattedNames); */
        /*List<String> string = List.of("Margo", "Ksani", "Eva", "Anna", "Julia");
        String uppercaseWords = ToUppercase.sortedUppercase(string);
        System.out.println(uppercaseWords);*/
        /*List<String> numbers = Arrays.asList("1, 2, 0", "4, 5");
        String collectNumbers = CollectionNumbers.collectionNum(numbers);
        System.out.println(collectNumbers);*/
        /*long seed = 4;
        CongruentialGenerator generator = new CongruentialGenerator(seed);
        Stream<Long> stream = generator.randomInfiniteStream();
        stream.limit(4).forEach(System.out::println);*/
        Stream<Integer> first = Stream.of(1, 2, 3, 4);
        Stream<Integer> second = Stream.of(5, 6, 7, 8, 9);

        Stream<Integer> zippedStream = StreamZip.zipV1(first, second);

        zippedStream.forEach(System.out::println);
    }
    }
