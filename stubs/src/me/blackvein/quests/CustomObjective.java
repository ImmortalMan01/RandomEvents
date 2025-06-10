package me.blackvein.quests;

import org.bukkit.entity.Player;

public class CustomObjective {
    public void setName(String name){}
    public void setAuthor(String author){}
    public void setShowCount(boolean b){}
    public void setCountPrompt(String prompt){}
    public void setDisplay(String display){}
    public static void incrementObjective(Player p, CustomObjective obj, int amt, Quest quest){}
}
