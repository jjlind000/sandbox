package org.jasonlind;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        calc(10000, 3, 1000, 104);
    }


    static void calc(int begin, int w_pc, int m_add, int weeks){
        List<Integer> m_w = Arrays.asList(4, 8, 13, 17, 22, 26, 30, 34, 39, 43, 48, 52);
         double total=begin;
         for(int w=1; w<=weeks; w++){
             double make = w_pc*total/100.0;
             total+=make;
             System.out.println(String.format("w: %d, make:%.0f, total: %.0f",w, make, total));
             int w_of_y = w > 52 ? w%52 : w;

             if(m_w.contains(w_of_y)){
                 total += m_add;
                 System.out.println(String.format(" -- after m%d: add %d, total: %.0f", m_w.indexOf(w_of_y)+1, m_add, total));
             }
         }

    }

}
