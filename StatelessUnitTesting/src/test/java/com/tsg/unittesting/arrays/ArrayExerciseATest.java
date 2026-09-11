package com.tsg.unittesting.arrays;

import org.junit.jupiter.api.Test;

import static com.tsg.unittesting.arrays.ArrayExerciseA.maxOfArray;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayExerciseATest {
    /*  Test Plan:
     * maxOfArray( {1}  ) ->  1
     * maxOfArray( {3,4,5}  ) ->  5
     * maxOfArray( {-9000, -700, -50, -3}  ) ->  -3
     */

    @Test
    public void testArray1() {
        int[] in = {1};

        int out = maxOfArray(in);

        int expectedOut = 1;
        assertEquals(expectedOut, out);
    }

    @Test
    public void testArrayNormal() {
        int[] in = {3,4,5};

        int out = maxOfArray(in);

        int expectedOut = 5;
        assertEquals(expectedOut, out);
    }

    @Test
    public void testArrayNegative() {
        int[] in = {-9000, -700, -50, -3};

        int out = maxOfArray(in);

        int expectedOut = -3;
        assertEquals(expectedOut, out);
    }
}
