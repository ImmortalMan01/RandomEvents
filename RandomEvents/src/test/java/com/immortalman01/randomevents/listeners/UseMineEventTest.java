package com.immortalman01.randomevents.listeners;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.MaterialData;
import org.bukkit.block.data.BlockData;
import org.bukkit.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.config.ReventConfig;
import com.immortalman01.randomevents.match.Match;
import com.immortalman01.randomevents.match.MatchActive;
import com.immortalman01.randomevents.match.data.MatchMapDataHandler;
import com.immortalman01.randomevents.match.data.MatchPlayerHandler;
import com.immortalman01.randomevents.match.enums.MinigameType;

public class UseMineEventTest {
    private Use use;
    private RandomEvents plugin;
    private MatchActive active;
    private Match match;
    private MatchPlayerHandler handler;
    private MatchMapDataHandler map;
    private ReventConfig config;
    private Player player;
    private Block block;
    private BlockBreakEvent event;
    private Map<org.bukkit.Location, BlockData> disappeared;

    @BeforeEach
    public void setup() {
        plugin = Mockito.mock(RandomEvents.class, Mockito.RETURNS_DEEP_STUBS);
        use = new Use(plugin);

        active = Mockito.mock(MatchActive.class, Mockito.RETURNS_DEEP_STUBS);
        match = Mockito.mock(Match.class, Mockito.RETURNS_DEEP_STUBS);
        handler = Mockito.mock(MatchPlayerHandler.class, Mockito.RETURNS_DEEP_STUBS);
        map = Mockito.mock(MatchMapDataHandler.class, Mockito.RETURNS_DEEP_STUBS);
        config = Mockito.mock(ReventConfig.class, Mockito.RETURNS_DEEP_STUBS);
        player = Mockito.mock(Player.class);
        block = Mockito.mock(Block.class);
        BlockState state = Mockito.mock(BlockState.class);
        event = Mockito.mock(BlockBreakEvent.class);

        when(event.getPlayer()).thenReturn(player);
        when(event.getBlock()).thenReturn(block);
        when(block.getType()).thenReturn(Material.DIRT);
        when(block.getData()).thenReturn((byte)0);
        when(block.getLocation()).thenReturn(Mockito.mock(Location.class));
        when(block.getBlockData()).thenReturn(Mockito.mock(BlockData.class));
        when(block.getState()).thenReturn(state);
        when(state.getData()).thenReturn(new MaterialData(Material.DIRT));

        when(plugin.getMatchActive()).thenReturn(active);
        when(plugin.getReventConfig()).thenReturn(config);
        when(active.getMatch()).thenReturn(match);
        when(active.getPlayerHandler()).thenReturn(handler);
        when(active.getMapHandler()).thenReturn(map);
        when(active.getCanBreak()).thenReturn(true);
        when(active.getPlaying()).thenReturn(true);
        when(config.isSnowballSpleef()).thenReturn(false);

        List<Player> spec = new ArrayList<>();
        spec.add(player);
        when(handler.getPlayersSpectators()).thenReturn(spec);
        when(map.getBlockPlaced()).thenReturn(new HashMap<>());
        disappeared = new HashMap<>();
        when(map.getBlockDisappeared()).thenReturn(disappeared);
    }

    @Test
    public void spleefCancelsNonMatchingBlock() {
        when(match.getMinigame()).thenReturn(MinigameType.SPLEEF);
        when(match.getMaterial()).thenReturn("SNOW_BLOCK");
        when(match.getAllMaterialAllowed()).thenReturn(true);

        use.onMine(event);

        verify(event).setCancelled(true);
        verify(block, never()).breakNaturally();
        assertTrue(disappeared.isEmpty());
    }

    @Test
    public void otherModeAllowsWithAllMaterial() {
        when(match.getMinigame()).thenReturn(MinigameType.SPLEGG);
        when(match.getMaterial()).thenReturn("SNOW_BLOCK");
        when(match.getAllMaterialAllowed()).thenReturn(true);

        use.onMine(event);

        verify(event).setCancelled(true);
        verify(block).breakNaturally();
        assertEquals(1, disappeared.size());
    }

    @Test
    public void spleefAllowsBlockFromDatas() {
        when(match.getMinigame()).thenReturn(MinigameType.SPLEEF);
        when(match.getMaterial()).thenReturn("SNOW_BLOCK");
        when(match.getAllMaterialAllowed()).thenReturn(false);
        List<MaterialData> datas = new ArrayList<>();
        datas.add(new MaterialData(Material.DIRT));
        when(match.getDatas()).thenReturn(datas);

        use.onMine(event);

        verify(event).setCancelled(true);
        verify(block, never()).breakNaturally();
        assertEquals(1, disappeared.size());
    }
}
