package com.immortalman01.randomevents.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.match.utils.InventoryPers;

/** Basic tests for UtilidadesJson.fromInventoryToJSON. */
public class InventorySerializationTest {
    @Test
    public void simpleInventorySerializes() {
        RandomEvents plugin = Mockito.mock(RandomEvents.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(plugin.getLoggerP()).thenReturn(Logger.getLogger("test"));
        InventoryPers inv = new InventoryPers();
        String json = UtilidadesJson.fromInventoryToJSON(plugin, inv);
        assertNotNull(json);
    }
}
