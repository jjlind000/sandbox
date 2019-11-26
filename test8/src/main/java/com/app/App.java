package com.app;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.assertEquals;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main(String[] args){
        iteratortest();

    }

    static void iteratortest(){
        final List<String> strings = Arrays.asList("one", "two", "three");
        final ListIterator<String> stringListIterator = strings.listIterator();
        while(stringListIterator.hasNext()){
            System.out.println(String.format("index: %d, value: %s | %s", stringListIterator.previousIndex()+1, strings.get(stringListIterator.previousIndex()+1), stringListIterator.next()));
        }
    }

    static void multimaptest()
    {
        String k1 = "k1";
        String k2 = "k2";
        String k3 = "k3";
        Multimap<String, String> map = ArrayListMultimap.create();

        //no need to create an empty collection before adding the first value for a key:
        map.put(k1, "firstValue");
        map.put(k1, "secondValue");
        map.put(k1, "thirdValue");
        map.put(k2, "thirdValue");
        map.put(k2, "firstValue");
        map.put(k3, "thirdValue");

        System.out.println("map.size(): " + map.size()); //6
        System.out.println("map.keySet().size(): " + map.keySet().size()); //3
        System.out.println("map.values().size(): " + map.values().size()); //6
        System.out.println("map.entries().size(): " + map.entries().size()); //6
        System.out.println("map: " + map);
        System.out.println("map.asMap(): " + map.asMap());
        //values() flattens the values for all keys into one collection:
        System.out.println("map.values(): " + map.values());                 //[firstValue, secondValue, thirdValue, thirdValue, firstValue, thirdValue]
        //asMap().values() preserves the values as separately belonging to their respective key
        System.out.println("map.asMap().values(): " + map.asMap().values()); //[[firstValue, secondValue, thirdValue], [thirdValue, firstValue], [thirdValue]]
        //map.get(key) for a nonexistent key returns an empty collection, rather than null:
        System.out.println("map.get(\"k4\"):" + map.get("k4")); //[]


    }
}
