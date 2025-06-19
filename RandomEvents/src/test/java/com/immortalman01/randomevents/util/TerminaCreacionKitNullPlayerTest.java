package com.immortalman01.randomevents.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.config.ReventConfig;
import com.immortalman01.randomevents.match.Kit;

public class TerminaCreacionKitNullPlayerTest {

    @Test
    public void conversionStepDoesNotThrow() throws Exception {
        RandomEvents plugin = Mockito.mock(RandomEvents.class, Mockito.RETURNS_DEEP_STUBS);
        File folder = Files.createTempDirectory("revent").toFile();
        Mockito.when(plugin.getDataFolder()).thenReturn(folder);

        ReventConfig cfg = Mockito.mock(ReventConfig.class);
        Mockito.when(cfg.getUseEncoding()).thenReturn("UTF-8");
        Mockito.when(plugin.getReventConfig()).thenReturn(cfg);

        Mockito.when(plugin.getLoggerP()).thenReturn(Logger.getLogger("test"));
        Mockito.when(plugin.getPlayersCreationKit()).thenReturn(new HashMap<>());
        Mockito.when(plugin.getPlayerKit()).thenReturn(new HashMap<>());
        List<Kit> kits = new ArrayList<>();
        Mockito.when(plugin.getKits()).thenReturn(kits);

        Kit kit = new Kit();
        kit.setName("example");
        kit.setItem(null);

        assertDoesNotThrow(() -> UtilsRandomEvents.terminaCreacionKit(plugin, null, kit));
        assertTrue(kits.contains(kit));
    }
}
