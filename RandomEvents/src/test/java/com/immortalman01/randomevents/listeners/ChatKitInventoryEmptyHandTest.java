package com.immortalman01.randomevents.listeners;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.immortalman01.randomevents.RandomEvents;
import com.immortalman01.randomevents.language.LanguageMessages;
import com.immortalman01.randomevents.match.Kit;
import com.immortalman01.randomevents.match.enums.CreacionKit;

public class ChatKitInventoryEmptyHandTest {
    private Chat chat;
    private RandomEvents plugin;
    private Player player;
    private LanguageMessages lang;
    private Map<String, Kit> kits;
    private Map<String, Integer> creation;
    private List<String> messages;

    @BeforeEach
    public void setup() {
        plugin = Mockito.mock(RandomEvents.class, Mockito.RETURNS_DEEP_STUBS);
        chat = new Chat(plugin);
        lang = Mockito.mock(LanguageMessages.class);
        Mockito.when(plugin.getLanguage()).thenReturn(lang);
        Mockito.when(lang.getInvalidInput()).thenReturn("invalid");
        kits = new HashMap<>();
        creation = new HashMap<>();
        Mockito.when(plugin.getPlayerKit()).thenReturn(kits);
        Mockito.when(plugin.getPlayersCreationKit()).thenReturn(creation);

        player = Mockito.mock(Player.class);
        Mockito.when(player.getName()).thenReturn("p");
        PlayerInventory inv = Mockito.mock(PlayerInventory.class);
        Mockito.when(player.getInventory()).thenReturn(inv);
        Mockito.when(inv.getItemInMainHand()).thenReturn(new ItemStack(Material.AIR));
        messages = new ArrayList<>();
        Mockito.doAnswer(a -> { messages.add(a.getArgument(0)); return null; }).when(player).sendMessage(Mockito.anyString());
    }

    private void invoke(String msg, int step) throws Exception {
        Method m = Chat.class.getDeclaredMethod("checkMessageCreationKit", String.class, Player.class, Integer.class);
        m.setAccessible(true);
        creation.put("p", step);
        kits.putIfAbsent("p", new Kit());
        m.invoke(chat, msg, player, step);
    }

    @Test
    public void emptyHandDoesNotSaveInventory() throws Exception {
        invoke("Done", CreacionKit.INVENTORY.getPosition());
        Kit kit = kits.get("p");
        assertNull(kit.getInventory());
        assertTrue(creation.containsKey("p"));
        assertEquals("invalid", messages.get(0));
        assertEquals(2, messages.size());
    }
}
