package com.d.tdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class BowlingKataTest {
    private BowlingKata bowlingKata = new BowlingKata();

    @Test()
    void test_sum_of_frames(){
        int[] pinsArray = {2,2, 4,5, 4,5, 4,5, 2,5};
        final int expected =38;

        verifyGameScore(pinsArray, expected);
    }

    @Test()
    void test_sum_of_spare(){
        int[] pinsArray = {5,5, 4,6, 3,7, 6,4, 1,1};
        final int expected = 56;

        verifyGameScore(pinsArray, expected);
    }

    @Test()
    void test_sum_of_strike(){
        int[] pinsArray = { 10, 10, 10, 10, 1, 1 };
        final int expected = 95;

        verifyGameScore(pinsArray, expected);
    }

    private void verifyGameScore(int[] pinsArray, int expected) {
        for (int pins : pinsArray) {
            bowlingKata.roll(pins);
        }
        final int actual = bowlingKata.score();
        assertEquals(expected, actual);
    }

}
