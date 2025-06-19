package com.immortalman01.randomevents.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.immortalman01.randomevents.RandomEvents;

public class TerminaCreacionKitNullParamsTest {
    @Test
    public void doesNotThrowWithNullPlayerAndKit() {
        RandomEvents plugin = Mockito.mock(RandomEvents.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(plugin.getLoggerP()).thenReturn(Logger.getLogger("test"));
        assertDoesNotThrow(() -> UtilsRandomEvents.terminaCreacionKit(plugin, null, null));
    }
}
