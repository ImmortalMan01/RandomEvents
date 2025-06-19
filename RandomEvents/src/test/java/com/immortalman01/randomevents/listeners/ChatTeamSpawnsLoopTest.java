package com.immortalman01.randomevents.listeners;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.*;

import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.language.LanguageMessages;
import com.immortalman01.randomevents.match.Match;
import com.immortalman01.randomevents.match.enums.Creacion;

public class ChatTeamSpawnsLoopTest {
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

    private void invoke(String msg) throws Exception {
        Method m = Chat.class.getDeclaredMethod("checkMessageCreation", String.class, Player.class, Integer.class);
        m.setAccessible(true);
        Integer step = creation.get("p");
        m.invoke(chat, msg, player, step);
    }

    @Test
    public void multipleDoneBeforeNextStoresAllLocations() throws Exception {
        creation.put("p", Creacion.TEAM_SPAWNS.getPosition());
        Match match = new Match();
        match.setNumberOfTeams(3);
        match.setSpawns(new ArrayList<>());
        matches.put("p", match);

        invoke("Done");
        assertEquals(1, match.getSpawns().size());
        assertEquals(Creacion.ANOTHER_TEAM_SPAWNS.getPosition(), creation.get("p"));

        invoke("Done");
        assertEquals(2, match.getSpawns().size());
        assertEquals(Creacion.ANOTHER_TEAM_SPAWNS.getPosition(), creation.get("p"));

        invoke("Next");
        assertEquals(2, match.getSpawns().size());
        assertFalse(creation.containsKey("p"));
        assertFalse(messages.isEmpty());
        assertNotEquals("invalid", messages.get(0));
    }
}
