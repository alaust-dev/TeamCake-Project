package de.teamcake.item.component.manapotion;

import de.teamcake.item.CustomItemMaterial;
import de.teamcake.mechanic.mana.PlayerMana;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class ManaPotionConsumableListener implements Listener {

    @EventHandler
    public void onPlayerConsumeEvent(PlayerItemConsumeEvent event){

        Player player = event.getPlayer();

        if(event.getItem().equals(CustomItemMaterial.MANA_POTION.getCustomItem().getItemStack()))
        {
            PlayerMana.addMana(player,5);
        }
    }
}
