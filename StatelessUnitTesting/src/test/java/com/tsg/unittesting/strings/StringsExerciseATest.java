package com.tsg.unittesting.strings;

import org.junit.jupiter.api.Test;

import static com.tsg.unittesting.strings.StringsExerciseA.yell;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringsExerciseATest {
    /*  Test Plan:
     * yell( Hello there." ) ->  "HELLO THERE."
     * yell( "shhhhhhhhhhhh" ) ->  "SHHHHHHHHHHHH"
     * yell( "AAaAAAaAAAaaAAHHHH" ) ->  "AAAAAAAAAAAAAAHHHH"
     */

    public StringsExerciseATest() {}

    @Test
    public void testHelloThere() {
        String in = "Hello there.";

        String out = yell(in);

        String expectedOut = "HELLO THERE.";
        assertEquals(expectedOut, out);
    }

    @Test
    public void testShhhhhhhhhhhh() {
        String in = "shhhhhhhhhhhh";

        String out = yell(in);

        String expectedOut = "SHHHHHHHHHHHH";
        assertEquals(expectedOut, out);
    }

    @Test
    public void testAAaAAAaAAAaaAAHHHH() {
        String in = "AAaAAAaAAAaaAAHHHH";

        String out = yell(in);

        String expectedOut = "AAAAAAAAAAAAAAHHHH";
        assertEquals(expectedOut, out);
    }
}

