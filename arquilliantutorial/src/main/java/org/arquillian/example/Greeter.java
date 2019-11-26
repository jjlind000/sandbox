package org.arquillian.example;

import javax.inject.Inject;
import java.io.PrintStream;

//http://arquillian.org/guides/getting_started/

/**
 * A component for creating personal greetings.
 */
public class Greeter {

    private PhraseBuilder phraseBuilder;

    @Inject //producer method?
    public Greeter(PhraseBuilder phraseBuilder) {
        System.out.println("Greeter: Injecting phraseBuilder...");
        this.phraseBuilder = phraseBuilder;
    }

    public void greet(PrintStream to, String name) {
        to.println(createGreeting(name));
    }

    public String createGreeting(String name) {
        return phraseBuilder.buildPhrase("hello", name);
    }
}