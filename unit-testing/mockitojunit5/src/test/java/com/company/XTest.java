package com.company;

//import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class XTest {

    @BeforeEach
    void setUp() {
    }

    @Test //@Ignore
    void foo() {
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void test_ValueSource(int i) {
        System.out.println(i);
    }


}