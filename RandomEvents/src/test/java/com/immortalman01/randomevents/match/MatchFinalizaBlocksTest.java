package com.immortalman01.randomevents.match;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.block.data.BlockData;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.immortalman01.api.API1711;
import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.config.ReventConfig;
import com.immortalman01.randomevents.match.enums.MinigameType;

public class MatchFinalizaBlocksTest {
    @Test
    public void blockListsClearedOnFinalization() {
        RandomEvents plugin = Mockito.mock(RandomEvents.class, Mockito.RETURNS_DEEP_STUBS);
        ReventConfig config = Mockito.mock(ReventConfig.class, Mockito.RETURNS_DEEP_STUBS);
        when(plugin.getReventConfig()).thenReturn(config);
        when(config.getCommandsOnEventFire()).thenReturn(Collections.emptyList());
        when(config.getCommandsOnMatchEnd()).thenReturn(Collections.emptyList());
        when(config.getDistanceClearEntities()).thenReturn(1);
        when(config.isDebugMode()).thenReturn(true);
        when(plugin.getWaterDrops()).thenReturn(Collections.emptyList());
        com.immortalman01.randomevents.language.LanguageMessages lang = Mockito.mock(com.immortalman01.randomevents.language.LanguageMessages.class);
        when(lang.getEventCancelled()).thenReturn("");
        when(lang.getEventStopped()).thenReturn("");
        when(lang.getEventEnded()).thenReturn("");
        when(lang.getTagPlugin()).thenReturn("");
        when(plugin.getLanguage()).thenReturn(lang);
        API1711 api = Mockito.mock(API1711.class);
        when(plugin.getApi()).thenReturn(api);
        when(plugin.getLoggerP()).thenReturn(java.util.logging.Logger.getLogger("test"));

        Match match = new Match();
        match.setMinigame(MinigameType.TNT_RUN);
        match.setScenes(new ArrayList<>());
        match.setRewards(new ArrayList<>());
        match.setTiempoPartida(10);

        MatchActive active = new MatchActive(match, plugin, false) {
            @Override
            public void matchWaitingPlayers() {}

            @Override
            public void matchWaitingPlayers(Boolean segundo) {}
        };
        active.setPlaying(true);

        Location l1 = Mockito.mock(Location.class);
        Block b1 = Mockito.mock(Block.class);
        when(l1.getBlock()).thenReturn(b1);
        active.getMapHandler().getBlockDisappeared().put(l1, Mockito.mock(BlockData.class));

        Location l2 = Mockito.mock(Location.class);
        Block b2 = Mockito.mock(Block.class);
        when(l2.getBlock()).thenReturn(b2);
        active.getMapHandler().getBlockDisappearedType().put(l2, Material.STONE);

        Location l3 = Mockito.mock(Location.class);
        Block b3 = Mockito.mock(Block.class);
        when(l3.getBlock()).thenReturn(b3);
        active.getMapHandler().getBlockPlaced().put(l3, Mockito.mock(BlockData.class));

        try (MockedStatic<Bukkit> mocked = Mockito.mockStatic(Bukkit.class)) {
            mocked.when(Bukkit::getOnlinePlayers).thenReturn(Collections.emptyList());
            mocked.when(Bukkit::getPluginManager).thenReturn(Mockito.mock(org.bukkit.plugin.PluginManager.class));
            active.finalizaPartida(new ArrayList<Player>(), false, false);
        }

        assertTrue(active.getMapHandler().getBlockDisappeared().isEmpty());
        assertTrue(active.getMapHandler().getBlockDisappearedType().isEmpty());
        assertTrue(active.getMapHandler().getBlockPlaced().isEmpty());

        verify(api, times(2)).convertBlock(any(Location.class), any());
        verify(b2).setType(Material.STONE);
    }
}
