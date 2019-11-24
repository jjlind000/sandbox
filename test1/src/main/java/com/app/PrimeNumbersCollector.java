package com.app;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>,  Map<Boolean, List<Integer>>> {

    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>() {{   //start the collection process with a Map
            put(true, new ArrayList<>());                       //containing two empty Lists
            put(false, new ArrayList<>());
        }};
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> accum, Integer candidate) -> {
            accum.get(isPrime(accum.get(true), candidate)).add(candidate); //Get from the Map the current list of prime
        };                    //pass to the isprime method the current     //or nonprime numbers, according to what the
    }                         //list of already found primes               //isprime method returns, and add it to the
                                                                           //current candidate

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true)); map1.get(false).addAll(map2.get(false)); //merge map2 into map1
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity(); //No xformation is necessary at the end ofthe collection process, so terminate w/
    }                               //the identity function

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH)); //the collector is IDENTITY_FINISH but neither
    }                                                                    //UNORDERED nor CONCURRENT bc it relies on the
                                                                         //fact that prime numbers are discovered in
                                                                         //sequence

    public static boolean isPrime(List<Integer> primes, int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        System.out.println(String.format("isPrime, candidate=%d, candidateRoot=%d, list of primes=%s", candidate, candidateRoot, primes.toString()));
        return takeWhile(primes, i -> {
            System.out.println(String.format("...lambda: testing if %d <= %d ", i, candidateRoot));
         return i <= candidateRoot; })
                .stream()
                .noneMatch(p -> {
                    boolean b = candidate % p == 0;
                    System.out.println(String.format("nonematch - %d %% %d == 0 returns %b", candidate, p, b));
                    return b;
                });
    }

    //what we want to do is stop once we find a prime that's greater than a candidate's root, so we'll create a method called
    //takeWhile, which, given a sorted list and a predicate, returns the longest prefix of this list whose elements
    //satisfy the predicate
    public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
        System.out.println(String.format("....in takeWhile for list of primes %s ... ", list.toString()));
        int i=0;
        for (A item : list) {
            System.out.println(String.format("......p.test(%s)... ", item.toString()));
            if (!p.test(item)){//check if the current item in the list satisfies the predicate; if it doesn't, return the
                               //sublist prefix until the item before the tested one
                List<A> sublist = list.subList(0, i);
                System.out.println(String.format("......p.test(%s) returned false; returning list.subList(0,%d) - %s",
                        item.toString(), i, sublist));
                return sublist;
            }
            i++;
        }
        System.out.println(String.format("......i = %s, returning whole list %s", i, list.toString()));
        return list; //all the items in the list satisfy the predicate so return the list itself
    }

    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    public static void main(String... args){
        System.out.println(partitionPrimesWithCustomCollector(20));
    }

}
