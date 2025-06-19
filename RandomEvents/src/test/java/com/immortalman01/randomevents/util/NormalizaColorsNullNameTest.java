package com.immortalman01.randomevents.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.immortalman01.randomevents.match.Match;
import com.immortalman01.randomevents.match.WaterDropStep;
import com.immortalman01.randomevents.match.Kit;

/** Tests for UtilsRandomEvents#normalizaColors* with null names. */
public class NormalizaColorsNullNameTest {

    @Test
    public void normalizeMatchNullName() {
        Match m = new Match();
        m.setName(null);
        assertDoesNotThrow(() -> UtilsRandomEvents.normalizaColorsMatch(m));
        assertNull(m.getName());
    }

    @Test
    public void normalizeWaterDropNullName() {
        WaterDropStep step = new WaterDropStep();
        step.setName(null);
        assertDoesNotThrow(() -> UtilsRandomEvents.normalizaColorsWaterDrop(step));
        assertNull(step.getName());
    }

    @Test
    public void normalizeKitNullName() {
        Kit kit = new Kit();
        kit.setName(null);
        assertDoesNotThrow(() -> UtilsRandomEvents.normalizaColorsKit(kit));
        assertNull(kit.getName());
    }
}
