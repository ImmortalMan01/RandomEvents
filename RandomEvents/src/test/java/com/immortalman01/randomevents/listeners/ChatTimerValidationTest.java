package com.immortalman01.randomevents.listeners;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.*;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.language.LanguageMessages;
import com.immortalman01.randomevents.match.Match;
import com.immortalman01.randomevents.match.enums.Creacion;

public class ChatTimerValidationTest {
    private Chat chat;
    private RandomEvents plugin;
    private Player player;
    private LanguageMessages lang;
    private Map<String, Match> matches;
    private Map<String, Integer> creation;
    private List<String> messages;

    @BeforeEach
    public void setup() {
        plugin = Mockito.mock(RandomEvents.class, Mockito.RETURNS_DEEP_STUBS);
        chat = new Chat(plugin);
        lang = Mockito.mock(LanguageMessages.class);
        Mockito.when(plugin.getLanguage()).thenReturn(lang);
        Mockito.when(lang.getInvalidInput()).thenReturn("invalid");
        matches = new HashMap<>();
        creation = new HashMap<>();
        Mockito.when(plugin.getPlayerMatches()).thenReturn(matches);
        Mockito.when(plugin.getPlayersCreation()).thenReturn(creation);

        player = Mockito.mock(Player.class);
        Mockito.when(player.getName()).thenReturn("p");
        messages = new ArrayList<>();
        Mockito.doAnswer(inv -> { messages.add(inv.getArgument(0)); return null; }).when(player).sendMessage(Mockito.anyString());
    }

    private static List<Creacion> steps() {
        return Arrays.asList(
            Creacion.TIMER_MOB_SPAWN,
            Creacion.PLAY_TIME,
            Creacion.SHRINK_TIME,
            Creacion.REFILL_CHEST,
            Creacion.TIMER_BLOCK_DISAPPEAR,
            Creacion.TIMER_DECREASE_TIME,
            Creacion.COLOR_APPEAR_TIME,
            Creacion.COLOR_APPEAR_DECREASE_TIME,
            Creacion.SHRINK_BLOCKS,
            Creacion.NO_MOVE_TIME,
            Creacion.TIMER_ARROW_SPAWN,
            Creacion.TIMER_ANVIL_SPAWN,
            Creacion.TIMER_GEM_SPAWN,
            Creacion.SECONDS_TO_SPAWN_BEAST,
            Creacion.TIMER_BOMB,
            Creacion.WARMUP_TIME
        );
    }

    private static Stream<Arguments> data() {
        return Stream.of("0", "-1").flatMap(v -> steps().stream().map(s -> Arguments.of(s, v)));
    }

    private void invoke(String msg, Creacion c) throws Exception {
        Method m = Chat.class.getDeclaredMethod("checkMessageCreation", String.class, Player.class, Integer.class);
        m.setAccessible(true);
        creation.put("p", c.getPosition());
        matches.putIfAbsent("p", new Match());
        m.invoke(chat, msg, player, c.getPosition());
    }

    @ParameterizedTest
    @MethodSource("data")
    public void invalidTimerValues(Creacion c, String value) throws Exception {
        invoke(value, c);
        Match m = matches.get("p");
        switch (c) {
            case TIMER_MOB_SPAWN:
            case TIMER_ARROW_SPAWN:
            case TIMER_ANVIL_SPAWN:
            case TIMER_GEM_SPAWN:
            case SECONDS_TO_SPAWN_BEAST:
            case TIMER_BOMB:
            case WARMUP_TIME:
                assertNull(m.getSecondsMobSpawn());
                break;
            case PLAY_TIME:
            case SHRINK_TIME:
                assertNull(m.getTiempoPartida());
                break;
            case REFILL_CHEST:
                assertNull(m.getTimeRefill());
                break;
            case TIMER_BLOCK_DISAPPEAR:
                assertNull(m.getBlockTimer());
                break;
            case TIMER_DECREASE_TIME:
                assertNull(m.getBlockDecreaseTimer());
                break;
            case COLOR_APPEAR_TIME:
                assertNull(m.getColorTimer());
                break;
            case COLOR_APPEAR_DECREASE_TIME:
                assertNull(m.getColorDecreaseTimer());
                break;
            case SHRINK_BLOCKS:
                assertNull(m.getShrinkBlocks());
                break;
            case NO_MOVE_TIME:
                assertNull(m.getSecondsToBegin());
                break;
            default:
                break;
        }
        assertTrue(creation.containsKey("p"));
        assertEquals("invalid", messages.get(0));
    }
}
