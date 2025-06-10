package me.blackvein.quests;

import java.util.UUID;

public class Quests {
    public Quester getQuester(UUID id){ return new Quester(); }
    public static class Quester {
        public java.util.Map<Quest,Integer> getCurrentQuests(){ return new java.util.HashMap<>(); }
    }
}
