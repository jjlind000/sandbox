package org.test;

import org.apache.log4j.Logger;

public class Log4jRollingExample {

    private static Logger logger = Logger.getLogger(Log4jRollingExample.class);

    public static void main(String[] args) throws InterruptedException {

        for(int i = 0; i < 20000; i++) {
            if(i%10==0)
            {
                System.out.println("log -info " + i);
                logger.info("This is the " + i + " time I say 'Hello World'.");
            }
            logger.trace("This is the " + i + " time I say 'Hello World'.");
            Thread.sleep(10);
        }
    }
}