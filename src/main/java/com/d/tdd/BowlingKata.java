package com.d.tdd;

import java.util.ArrayList;
import java.util.List;

public class BowlingKata {
    private List<Integer> rolls = new ArrayList<>();

    public void roll(int pin) {
        rolls.add(pin);
    }

    public int score() {
        final int MAX_PER_FRAME = 10;
        final int MAX_FRAME_ROLLS = 2;
        int totalScore =  0;
        boolean hasSpare = false;
        int frameScore = 0;
        int frameRolls = 0;
        int rollIndex = 0;

        for (Integer pin : rolls) {
            final boolean hasStrikeBonus = pin == MAX_PER_FRAME;
            frameScore += pin;
            frameRolls ++;

            if(hasSpare) {
                totalScore += pin*2;
                hasSpare = false;
            } else if (hasStrikeBonus) {
                final int strikeScoreRange = Math.min(rollIndex + 3, rolls.size());
                final int strikeScore = rolls.subList(rollIndex, strikeScoreRange).stream()
                                        .reduce(0, Integer::sum);
                totalScore += strikeScore;
                frameScore = 0;
                frameRolls = 0;
            } else {
                totalScore += pin;
            }

            if(frameRolls == MAX_FRAME_ROLLS) {
                if(frameScore == MAX_PER_FRAME) {
                    hasSpare = true;
                }
                frameScore = 0;
                frameRolls = 0;
            }

            rollIndex ++;
        }

        return totalScore;
    }
}
