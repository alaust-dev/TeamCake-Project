package de.teamcake.mechanic.darkervision;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class DarkerVisionRunnable extends BukkitRunnable {

    private static final Material[] lightSources = {
            Material.TORCH,
            Material.SOUL_TORCH,
            Material.LANTERN,
            Material.SOUL_LANTERN
    };

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            World playerWorld = player.getWorld();

            if (playerWorld.getEnvironment() != World.Environment.NORMAL) {
                continue;
            }
            if ((playerWorld.getTime() < 14000 || playerWorld.getTime() >= 23000)
                    && player.getLocation().getBlock().getLightFromSky() != 0) {
                continue;
            }
            if (playerHoldsLightSource(player)) {
                continue;
            }
            if (player.getLocation().getBlock().getLightFromBlocks() >= 7) {
                continue;
            }

            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 1, false, false));
        }
    }

    private boolean playerHoldsLightSource(Player player) {
        PlayerInventory playerInventory = player.getInventory();
        Material playerHandItem = playerInventory.getItemInOffHand().getType();
        Material playerOffHandItem = playerInventory.getItemInMainHand().getType();

        return Arrays.stream(lightSources)
                .anyMatch(material -> playerHandItem == material || playerOffHandItem == material);
    }
}
