package de.teamcake.item.wand.wandofthewater;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import de.teamcake.TeamcakeProject;
import de.teamcake.common.util.projectiles.CustomProjectile;
import de.teamcake.common.util.projectiles.Projectiles;
import de.teamcake.item.CustomItemMaterial;
import de.teamcake.mechanic.mana.PlayerMana;

public class WOTWInteractionListener implements Listener {

    public static final int MANA_COST = 1;


    @EventHandler
    protected void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null || !CustomItemMaterial.WAND_OF_THE_WATER.getCustomItem()
                .isSimilarTo(event.getItem())) {
            return;
        }
        if (!event.getAction().name().startsWith("RIGHT_CLICK")) {
            return;
        }
        if (PlayerMana.getMana(event.getPlayer()) < MANA_COST) {
            return;
        }

        CustomProjectile customProjectile = CustomProjectile.builder()
                .damage(1)
                .force(0.25f)
                .bouncing(true)
                .bounceForceReduction(0.95)
                .liveTime(20 * 4)
                .tickHandler(
                        arrow -> arrow.getWorld().spawnParticle(
                                Particle.REDSTONE, arrow.getLocation(), 10, 0, 0, 0, 0,
                                new Particle.DustOptions(Color.fromBGR(250, 100, 1), 1)))
                .bounceHandler(
                        arrow -> arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1, 2))
                .destroyHandler(
                        arrow -> arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_GENERIC_SPLASH, 1, 1))
                .build();
        Projectiles.cast(TeamcakeProject.getPlugin(), event.getPlayer(), customProjectile);
        PlayerMana.consumeMana(event.getPlayer(), MANA_COST);
    }
}
