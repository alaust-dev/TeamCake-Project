package de.teamcake.mechanic.mana.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.teamcake.mechanic.mana.PlayerMana;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;

public class ManaRenderRunnable extends BukkitRunnable {

    private static final String CONTAINER_SYMBOL = "\\uF802\\uC001";
    private static final String EMPTY_CONTAINER_SYMBOL = "\\uF802\\uC002";


    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            final int playerMana = PlayerMana.getMana(player);

            String displayText = "\\uF806\\uF80A\\uF80B\\uF824"
                    + CONTAINER_SYMBOL.repeat(Math.max(0, playerMana))
                    + EMPTY_CONTAINER_SYMBOL.repeat(Math.max(0, 10 - playerMana));

            player.sendActionBar(GsonComponentSerializer.gson().deserialize("{\"text\":\"" + displayText + "\"}"));
        }
    }
}
