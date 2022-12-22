package de.teamcake.item.wand.wandoftheearth;

import java.util.HashMap;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import de.teamcake.TeamcakeProject;
import de.teamcake.common.util.projectiles.CustomProjectile;
import de.teamcake.common.util.projectiles.Projectiles;
import de.teamcake.item.CustomItemMaterial;
import de.teamcake.mechanic.mana.PlayerMana;

public class WOTEInteractionListener implements Listener {

    private static final int DIRTBALL_MANA_COST = 1;
    private static final int DIRT_BLOCK_MANA_COST = 2;
    private static final HashMap<Player, Long> rightClickCoolDown = new HashMap<>();


    @EventHandler
    protected void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null || !CustomItemMaterial.WAND_OF_THE_EARTH.getCustomItem()
                .isSimilarTo(event.getItem())) {
            return;
        }
        if (!event.getAction().name().startsWith("RIGHT_CLICK")) {
            return;
        }

        final Player player = event.getPlayer();
        final int mana = PlayerMana.getMana(player);

        if (!player.isSneaking() && mana >= DIRTBALL_MANA_COST) {
            shootDirtBall(event);
        } else if (event.getClickedBlock() != null && mana >= DIRT_BLOCK_MANA_COST) {
            placeDirtBlock(event);
        }
    }

    private static void shootDirtBall(PlayerInteractEvent event) {
        final CustomProjectile customProjectile = CustomProjectile.builder()
                .damage(2)
                .force(0.5f)
                .bouncing(false)
                .liveTime(20 * 4)
                .tickHandler(arrow -> arrow.getWorld()
                        .spawnParticle(Particle.REDSTONE, arrow.getLocation(), 10, 0, 0, 0, 0,
                                new Particle.DustOptions(Color.fromBGR(1, 100, 200), 1)))
                .destroyHandler(arrow -> {
                    arrow.getWorld().playSound(arrow.getLocation(), Sound.BLOCK_ROOTED_DIRT_BREAK, 1, 1);
                    BlockData data = arrow.getLocation().add(0, -1, 0).getBlock().getBlockData();
                    arrow.getWorld()
                            .spawnParticle(Particle.BLOCK_CRACK, arrow.getLocation(), 10, 0.5, 0, 0.5, data);
                })
                .build();

        Projectiles.cast(TeamcakeProject.getPlugin(), event.getPlayer(), customProjectile);
        PlayerMana.consumeMana(event.getPlayer(), DIRTBALL_MANA_COST);
    }

    private static void placeDirtBlock(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) {
            return;
        }
        if (rightClickCoolDown.containsKey(event.getPlayer())
                && rightClickCoolDown.get(event.getPlayer()) > System.currentTimeMillis()) {
            return;
        }

        final Location clickLocation = event.getClickedBlock().getLocation();
        final Block newPlacedBlock = clickLocation.add(event.getBlockFace().getDirection()).getBlock();

        newPlacedBlock.setType(Material.DIRT);
        newPlacedBlock.getWorld().playSound(newPlacedBlock.getLocation(), Sound.BLOCK_ROOTED_DIRT_PLACE, 1, 1);
        PlayerMana.consumeMana(event.getPlayer(), DIRT_BLOCK_MANA_COST);
        rightClickCoolDown.put(event.getPlayer(), System.currentTimeMillis() + 100);
    }
}
