package com.immortalman01.randomevents.listeners;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.language.LanguageMessages;
import com.immortalman01.randomevents.match.Match;
import com.immortalman01.randomevents.match.enums.Creacion;

public class ChatTeamCountTest {
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

    private void invoke(String msg, int step) throws Exception {
        Method m = Chat.class.getDeclaredMethod("checkMessageCreation", String.class, Player.class, Integer.class);
        m.setAccessible(true);
        creation.put("p", step);
        matches.putIfAbsent("p", new Match());
        m.invoke(chat, msg, player, step);
    }

    @Test
    public void validTeams() throws Exception {
        invoke("3", Creacion.NUMBER_OF_TEAMS.getPosition());
        Match m = matches.get("p");
        assertEquals(3, m.getNumberOfTeams());
        assertFalse(creation.containsKey("p"));
        assertFalse(messages.isEmpty());
        assertNotEquals("invalid", messages.get(0));
    }

    @Test
    public void invalidTeamsTooLow() throws Exception {
        invoke("1", Creacion.NUMBER_OF_TEAMS.getPosition());
        Match m = matches.get("p");
        assertNull(m.getNumberOfTeams());
        assertTrue(creation.containsKey("p"));
        assertEquals("invalid", messages.get(0));
        assertEquals(2, messages.size());
    }

    @Test
    public void invalidTeamsTooHigh() throws Exception {
        invoke("10", Creacion.NUMBER_OF_TEAMS.getPosition());
        Match m = matches.get("p");
        assertNull(m.getNumberOfTeams());
        assertTrue(creation.containsKey("p"));
        assertEquals("invalid", messages.get(0));
        assertEquals(2, messages.size());
    }

    @Test
    public void invalidTeamsNonNumeric() throws Exception {
        invoke("abc", Creacion.NUMBER_OF_TEAMS.getPosition());
        Match m = matches.get("p");
        assertNull(m.getNumberOfTeams());
        assertTrue(creation.containsKey("p"));
        assertEquals("invalid", messages.get(0));
        assertEquals(2, messages.size());
    }
}
