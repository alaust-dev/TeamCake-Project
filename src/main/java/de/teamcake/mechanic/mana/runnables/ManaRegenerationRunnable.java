package de.teamcake.mechanic.mana.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.teamcake.mechanic.mana.PlayerMana;

public class ManaRegenerationRunnable extends BukkitRunnable {

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(!PlayerMana.canRegenerate(player)) {
                continue;
            }

            PlayerMana.addMana(player, 1);
        }
    }
}
