package com.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;
import static java.util.stream.Collectors.*;

/**
 * Hello world!
 */

class ToListCollector<T> implements Collector<T, List<T>, List<T>>{
    @Override public Supplier<List<T>> supplier()   { return ArrayList::new;
                                                    //OR: return () -> new ArrayList<>();
                                                    }
    @Override public BiConsumer<List<T>, T> accumulator() { return  List::add;
                                                    //OR: return (list, element) -> list.add(element);
                                                    }
    @Override public Function<List<T>, List<T>> finisher() { return Function.identity(); }
    @Override public BinaryOperator<List<T>> combiner() { return (list1, list2) -> { list1.addAll(list2); return list1; };}
    @Override public Set<Characteristics> characteristics() { return Collections.unmodifiableSet((EnumSet.of(IDENTITY_FINISH, CONCURRENT)));}
}


class X {
    int i; String s;
    public X(int i, String s){ this.i=i; this.s=s;}
    public int getI() { return i; }
    public String getS() { return s; }

    @Override
    public String toString() {
        return "X{" +
                "i=" + i +
                ", s='" + s + '\'' +
                '}';
    }
}

class Y{
    Integer i;
    public Y(){}
    public Y(Integer i){
        this.i = i;
    }

    public Integer getI() { return i; }

    @Override
    public String toString() {
        return "Y{" +
                "i=" + i +
                '}';
    }
}

public class App {

    static class Dish {
        enum Type{ FISH, MEAT, OTHER; }

        String name; Dish.Type type; int calories;
        Dish(String name){ this.name = name;}
        Dish(String name, Dish.Type type, int calories){ this.name = name; this.type = type; this.calories=calories;}
        public String getName() { return name; }
        public Type getType() {  return type;   }
        public int getCalories() {  return calories;  }

        @Override
        public String toString() {
            return "Dish{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    ", calories=" + calories +
                    '}';
        }
    }

    static List<Dish> menu = Arrays.asList(new Dish("riceandpeas", Dish.Type.OTHER, 500), new Dish("pizza", Dish.Type.OTHER, 900), new Dish("fishandchips", Dish.Type.FISH, 1000), new Dish("pork chop", Dish.Type.MEAT, 800));

    public static void main(String[] args) throws IOException {
        //testExceptionInStream();
        //testCollectors();
        //testflatmap();
        //testflatmap2();
        //testoptionalInt();
        //teststreamlines();
        //testiteratestreams();
        //testiteratestreams2();
        //testgenerate();
        //testgeneratewithstate();
        //testreduction();
        //testcollecting();
        //testpartitioning();
        testCustomCollector();
    }

    private static void testCustomCollector() {
        List<Dish> dishes = menu.stream().collect(new ToListCollector<Dish>());
        System.out.println(dishes);
    }

    private static void testpartitioning(){
        Map<Boolean, List<Integer>> booleanListMap = partitionPrimes(10);
        System.out.println(booleanListMap);

    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(
                        partitioningBy(App::isPrime));
    }


    private static void testreduction(){
        //test reduction - implementation of joining

        List<Dish> dishes = Arrays.asList(new Dish("rice"), new Dish("peas"), new Dish("toast"));
        System.out.println(dishes.stream().map(Dish::getName).collect(joining())); //ricepeastoast
        System.out.println(new ArrayList<Dish>().stream().map(Dish::getName).collect(joining())); //empty line
        System.out.println(dishes.stream().map(Dish::getName).collect(reducing((d1, d2)-> d1+d2)).get()); //ricepeastoast
        //System.out.println(new ArrayList<Dish>().stream().map(Dish::getName).collect(reducing((d1, d2)-> d1+d2)).get()); //exception - no value present
        //System.out.println(dishes.stream().collect(reducing( (d1, d2) -> d1.getName() + d2.getName())).get(); //doesn't compile as lambda is not a binop<T>
        System.out.println(dishes.stream().collect(reducing( "", Dish::getName, (s1, s2) -> s1 + s2 ))); //ricepeastoast
        System.out.println(new ArrayList<Dish>().stream().collect(reducing( "", Dish::getName, (s1, s2) -> s1 + s2 ))); //empty line
    }

    public static void testcollecting(){

        Map<Dish.Type, Optional<Dish>> map = menu.stream().collect(
                groupingBy(Dish::getType
                        , maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println(map);
        Map<Dish.Type, Dish> map2 = menu.stream().collect(
                groupingBy(Dish::getType
                        , collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(map2);

        Map<Dish.Type, Integer> totalCalsByType = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println("totalcals by type: " + totalCalsByType);

//Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
//    menu.stream()
//        .collect(groupingBy(Dish::getType,
//                            mapping(dish -> { if (dish.getCalories() <= 400) return CaloricLevel.DIET;
//                                              else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
//                                              else return CaloricLevel.FAT; }, //1st argument to mapping
//                                    //toSet() )));				//2nd argument to mapping
//                                    toCollection(HashSet::new))));
    }

    private static void testgeneratewithstate() {
        IntSupplier fib = new IntSupplier(){
            private int previous = 0;
            private int current = 1;
            public int getAsInt(){
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(x-> System.out.print(x+" "));
    }

    private static void testgenerate() {
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    private static void testiteratestreams() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    private static void testiteratestreams2() {
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0]+t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] +")"));
    }

    static void teststreamlines() throws IOException {
        //System.out.println(System.getProperty("user.dir"));
        Stream<String> lines = Files.lines(Paths.get("src/main/resources/data.txt"));
        List<String> words = lines.flatMap(x -> Arrays.stream(x.split(" "))).distinct().collect(toList());
        System.out.println(words.toString());
    }

    static void testoptionalInt(){
        List<Y> ys = List.of(new Y(2), new Y(3), new Y(3));
        OptionalInt max = ys.stream()
                .mapToInt(Y::getI)
                .max();
        System.out.println(max.getAsInt());
        List<Y> ys2 = List.of();
        OptionalInt max2 = ys2.stream()
                .mapToInt(Y::getI)
                .max();
        System.out.println(max2.orElse(0));
    }

    static void testflatmap(){
        /*note that:
            to flatten a Stream<String[]> into a Stream<String> you use
                .flatMap(x -> Arrays.stream(x))  [x is of type String[] - ie each element in the stream is a String[]
            to flatten a Stream<List<String>> into a Stream<String> you use
                .flatMap(x -> x.stream())

         */
        String[] arrayOfStrings = { "hello", "world"};

        //split => one array of strings of length 5; each element contains a string of length=1
        //System.out.println("hello".split("").length);

        Stream<String> streamOfStrings = Arrays.stream(arrayOfStrings);
        Stream<String[]> stream = streamOfStrings.map(word -> word.split(""));
        List<String[]> collect = stream.collect(toList());
        System.out.println(collect.toString());

        Stream<String[]> stream1 = Arrays.stream(arrayOfStrings).map(word -> word.split(""));
        Stream<String> stringStream = stream1.peek(x-> System.out.println("Stream<String[]> peek: " + x.length + " --> " + Arrays.toString(x))).flatMap(x -> Arrays.stream(x));
        List<String> collect1 = stringStream.distinct().collect(toList());
        System.out.println(collect1);

        Stream<String> arrayOfStrings1 = Stream.of(arrayOfStrings);
        Stream<List<String>> listStream = arrayOfStrings1.map(x -> x.split("")).map(x -> Arrays.asList(x));
        Stream<String> stringStream1 = listStream.peek(x -> System.out.println("Stream<List<String>> peek: " + x.size() + " --> " + x.toString())).flatMap(x -> x.stream());
        List<String> collect2 = stringStream1.distinct().collect(toList());
        System.out.println(collect2);

    }

    static void testflatmap2(){
        /*Q Given two lists of numbers, how would you return all pairs of numbers? For example, given a list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]. For simplicity, you can represent a pair as an array with two elements.
          Answer: You could use two maps to iterate on the two lists and generate the pairs. But this would return a Stream<Stream<Integer[]>>. What you need to do is flatten the generated streams to result in
        a Stream<Integer[]>. This is what flatMap is for: */
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(toList());
        pairs.forEach(x->System.out.println(">>>" + Arrays.toString(x)));
        /* Q  How would you extend the previous example to return only pairs whose sum is divisible by 3?
              For example, (2, 4) and (3, 3) are valid.
        Answer:  You saw earlier that filter can be used with a predicate to filter elements from a stream. Because after the flatMap operation you have a stream of int[]
         that represent a pair, you just need a predicate to check to see if the sum is divisible by 3:
         */
        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4);
        List<int[]> pairs2 = numbers3.stream()
                .flatMap(i ->
                        numbers2.stream()
                                .filter(j -> (i + j) % 3 == 0)
                                .map(j -> new int[]{i, j})
                )
                .collect(toList());
    }

    static void testCollectors(){
        /*remember BinaryOperator.maxBy expects a Comparator as argument and a Comparator has one functional interface method: compare(o1, o2):
        int compare(T o1, T o2)
        Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
        */
        Comparator<X> xComparatorByInt = Comparator.comparingInt(X::getI);
        Comparator<X> xComparatorByString = Comparator.comparing(x-> x.getS().length());
        List<X> xes = List.of(new X(2, "Mon"), new X(4, "Tue"), new X(6, "Wed"), new X(5, "Thur"));
        Optional<X> maxByInt = xes.stream().collect(Collectors.maxBy(xComparatorByInt));
        Optional<X> maxByString = xes.stream().collect(Collectors.maxBy(xComparatorByString));

        System.out.println("maxByInt: " + maxByInt.orElseThrow(()->new RuntimeException("empty stream!")));
        System.out.println("maxByString (length): " + maxByString.orElseThrow(()->new RuntimeException("empty stream!")));

        Integer sum = xes.stream().collect(reducing(0, X::getI, (i, j) -> i + j));
        System.out.println("sum:"+sum);
        Integer len1 = xes.stream().collect(reducing(0, xx -> xx.getS().length(), (i, j) -> i.compareTo(j)));
        Integer len2a = xes.stream().collect(reducing(0, xx -> xx.getS().length(), BinaryOperator.maxBy((i, j) -> i.compareTo(j))));
        Integer len2b = xes.stream().collect(reducing(0, xx -> xx.getS().length(), BinaryOperator.maxBy(Comparator.naturalOrder())));
        //following DOES NOT WORK because it's not supplying something that matches the semantic sof compare(o1,o2) in the Comparator interface!!!
        Integer len2c = xes.stream().collect(reducing(0, xx -> xx.getS().length(), BinaryOperator.maxBy((i, j) ->
                                    {
                                        System.out.println(String.format("len2c received i=%d, j=%d, returning %d", i, j, i > j ? i : j));
                                        return  i > j ? i : j;
                                     })));
        //this works - the lambda matches the semantics of the compare(o1,o2) method:
        Integer len2d = xes.stream().collect(reducing(0, xx -> xx.getS().length(), BinaryOperator.maxBy((i, j) ->
                                    {
                                        int retval;
                                        if (i>j) retval= 1;
                                        else if (i<j) retval = -1;
                                        else retval= 0;
                                        System.out.println(String.format("len2c received i=%d, j=%d, returning %d", i, j, retval));
                                        return retval;
                                     })));
        Integer len3 = xes.stream().collect(reducing(0, xx-> xx.getS().length(), (i, j) -> i>j ? i : j));
        System.out.println("len1:"+len1);
        System.out.println("len2a:"+len2a);
        System.out.println("len2b:"+len2b);
        System.out.println("len2c:"+len2c);
        System.out.println("len2d:"+len2d);
        System.out.println("len3:"+len3);

    }
    static void testExceptionInStream() {
        //shows use of a wrapper function to wrap a Function<T,R> that throws a checked exception
        List.of("2019-10-12", "2", "2019-10-13").stream()
                //.map(App::doSomething)
                .map(wrap(App::doSomething)).forEach(System.out::println);
    }

    static Date doSomething(String s) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(s);
    }

    public static <T, R> Function<T, R> wrap(CheckedFunction<T, R> checkedFunction) {
        /*note that wrap is returning here a lambda i.e.
               return t->{ .... }
          which is consistent with its signature i.e. it is defined to return a Function<T,R>
         */
        return t -> {
            try {
                return checkedFunction.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}

@FunctionalInterface
interface CheckedFunction<T, R> {
    R apply(T t) throws Exception;
}