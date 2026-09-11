package com.tsg.unittesting.logic;

import org.junit.jupiter.api.Test;

import static com.tsg.unittesting.logic.LogicExerciseA.friendlyGreeting;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicExerciseATest {
    /*  Test Plan:
     * friendlyGreeting( "Goofus" , false ) ->   "hi"
     * friendlyGreeting( "Gallant" , true ) ->   "Hello, Gallant!"
     * friendlyGreeting( null , false ) ->   "..."
     * friendlyGreeting( null , true ) ->   "..."
     */

    @Test
    public void testGoofus() {
        String name = "Goofus";
        boolean isFriend = false;

        String out = friendlyGreeting(name,isFriend);

        String expectedOut = "hi.";
        assertEquals(expectedOut, out);
    }

    @Test
    public void testFriendGallant() {
        String name = "Gallant";
        boolean isFriend = true;

        String out = friendlyGreeting(name,isFriend);

        String expectedOut = "Hello, Gallant!";
        assertEquals(expectedOut, out);
    }

    @Test
    public void testNone() {
        String name = null;
        boolean isFriend = false;

        String out = friendlyGreeting(name,isFriend);

        String expectedOut = "...";
        assertEquals(expectedOut, out);
    }

    @Test
    public void testNoneFriend() {
        String name = null;
        boolean isFriend = true;

        String out = friendlyGreeting(name,isFriend);

        String expectedOut = "...";
        assertEquals(expectedOut, out);
    }

}
