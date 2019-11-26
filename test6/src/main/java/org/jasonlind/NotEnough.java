package org.jasonlind;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jasonl on 29/08/19
 */
public class NotEnough
{

    private final static Logger log = LoggerFactory.getLogger(NotEnough.class);

    public static void main(String...args){

        f1();

    }

    static void f1(){
        String s = "Cokanasiga (Japan)";
        System.out.println(">>" + s.substring(0,s.indexOf(" (")-1) + "<<");

    }




    String x(){ return "x"; }
    String y(){ return "y"; }
    String z(){ return "z"; }


    static void foo(){
        System.out.println(log.isDebugEnabled());
        NotEnough obj = new NotEnough();
        log.trace(String.format("Calling xxxxx for account: %s, evidence type: %s, comment: %s"), obj.x(), obj.y(), obj.z());
    }

}
