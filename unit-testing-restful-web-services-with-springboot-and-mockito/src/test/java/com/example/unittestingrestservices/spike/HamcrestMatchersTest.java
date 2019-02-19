package com.example.unittestingrestservices.spike;

import org.assertj.core.data.Percentage;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyString;

public class HamcrestMatchersTest {

    @Test
    public void foo(){
        List<Integer> numbers = Arrays.asList(12,13,14);
        assertThat(numbers, hasSize(3));
        assertThat(numbers, hasItems(12,13));
        assertThat(numbers, everyItem(greaterThan(11)));
        assertThat("", isEmptyString());
        assertThat("ABCDE", containsString("BCD"));
        assertThat("ABCDE", startsWith("AB"));

        assertThat(numbers).hasSize(3).contains(13,14).containsOnlyOnce(12);
        assertThat(numbers).allMatch(x->x>10);
        assertThat(numbers).allSatisfy(number-> {
            assertThat(number).isGreaterThan(10);
            assertThat(number).isCloseTo(11,Percentage.withPercentage(30.0));
        });



    }

}
