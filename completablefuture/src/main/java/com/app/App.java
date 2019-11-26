package com.app;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    //an executor to execute the Future
    static ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void p(String msg){
        System.out.println(String.format("%s: %s", dtf.format(LocalDateTime.now()), msg));
    }
    public static void main( String[] args ) throws ExecutionException, InterruptedException
    {
        p( "main - calling callFuture..." );
        callFuture();
        p( "main - calling callCompletableFuture..." );
        callCompletableFuture();
        p( "main - sleeping for 5s..." );
        Thread.sleep(5000);
        p( "main done" );
    }

    static void callFuture() throws ExecutionException, InterruptedException
    {
        p( "calling foo().get().." );
        p(foo().get());
        p( "after foo().." );
        executor.shutdownNow();
        if(executor.isTerminated())
        p( "done" );
    }

    private static void callCompletableFuture()
    {
        p( "calling bar().thenAccept().." );
        bar().thenAccept(s->p("received " + s));
        p( "after bar().." );
    }

    // Returns a Future
    static Future<String> foo(){
        return executor.submit(() -> {
            Thread.sleep(2000L);
            return "foo";
        });
    }

    // Returns a CompletableFuture
    static CompletableFuture<String> bar() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "bar";
        });
    }
}
