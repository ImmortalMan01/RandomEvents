package com.immortalman01.randomevents.match;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.immortalman01.randomevents.match.enums.MinigameType;

public class MatchCompareToTest {

    @Test
    public void compareHandlesBothNull() {
        Match m1 = new Match();
        Match m2 = new Match();
        assertEquals(0, m1.compareTo(m2));
    }

    @Test
    public void compareHandlesLeftNull() {
        Match m1 = new Match();
        Match m2 = new Match();
        m2.setMinigame(MinigameType.TNT_RUN);
        assertEquals(-1, m1.compareTo(m2));
    }

    @Test
    public void compareHandlesRightNull() {
        Match m1 = new Match();
        m1.setMinigame(MinigameType.TNT_RUN);
        Match m2 = new Match();
        assertEquals(1, m1.compareTo(m2));
    }

    @Test
    public void compareHandlesBothNonNull() {
        Match m1 = new Match();
        Match m2 = new Match();
        m1.setMinigame(MinigameType.BATTLE_ROYALE);
        m2.setMinigame(MinigameType.TNT_RUN);
        int expected = Integer.compare(MinigameType.BATTLE_ROYALE.ordinal(), MinigameType.TNT_RUN.ordinal());
        assertEquals(expected, m1.compareTo(m2));
    }

    @Test
    public void compareNullArgument() {
        Match m1 = new Match();
        assertThrows(NullPointerException.class, () -> m1.compareTo(null));
    }
}
