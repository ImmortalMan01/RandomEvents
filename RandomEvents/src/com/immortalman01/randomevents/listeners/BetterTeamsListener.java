package com.immortalman01.randomevents.listeners;

import com.booksaw.betterTeams.Team;
import com.immortalman01.randomevents.RandomEvents;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class BetterTeamsListener implements Listener {

    private final RandomEvents plugin;

    public BetterTeamsListener(RandomEvents plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (plugin.getMatchActive() == null || !plugin.getMatchActive().getPlaying()) {
            return;
        }

        if (!(event.getEntity() instanceof Player victim) || !(event.getDamager() instanceof Player attacker)) {
            return;
        }

        Team victimTeam = Team.getTeam(victim);
        Team attackerTeam = Team.getTeam(attacker);

        if (victimTeam != null && victimTeam.equals(attackerTeam) && event.isCancelled()) {
            Integer victimMatchTeam = plugin.getMatchActive().getEquipo(victim);
            Integer attackerMatchTeam = plugin.getMatchActive().getEquipo(attacker);
            if (victimMatchTeam == null || attackerMatchTeam == null || !victimMatchTeam.equals(attackerMatchTeam)) {
                event.setCancelled(false);
            }
        }
    }
}
