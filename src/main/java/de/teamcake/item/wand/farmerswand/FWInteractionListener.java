package de.teamcake.item.wand.farmerswand;

import de.teamcake.item.CustomItemMaterial;
import de.teamcake.mechanic.mana.PlayerMana;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Crops;

public class FWInteractionListener implements Listener {

    public static final int MANA_COST = 2;

    @EventHandler
    protected void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null || !CustomItemMaterial.FARMERS_WAND.getCustomItem()
                .isSimilarTo(event.getItem())) {
            return;
        }
        if (!event.getAction().name().startsWith("RIGHT_CLICK")) {
            return;
        }
        if (PlayerMana.getMana(event.getPlayer()) < MANA_COST) {
            return;
        }
            Block target = event.getPlayer().getTargetBlockExact(10);
            if(target.getBlockData()instanceof Ageable){
                PlayerMana.consumeMana(event.getPlayer(), 3);
                Bukkit.broadcastMessage("debug");
                Ageable ab = (Ageable) target.getBlockData();
                int currentage = ab.getAge();
                int maxage = ab.getMaximumAge();

                if(currentage < maxage/2){
                    ab.setAge(currentage + maxage/2);
                    target.setBlockData(ab);
                }else {
                    ab.setAge(maxage);
                    target.setBlockData(ab);
                }
            }
        }
    }