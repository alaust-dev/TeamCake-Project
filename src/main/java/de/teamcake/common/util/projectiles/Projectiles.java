package de.teamcake.common.util.projectiles;

import java.util.Arrays;
import java.util.Objects;

import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Projectiles {

    private static final Material[] NON_SOLID = new Material[]{
            Material.AIR,
            Material.TORCH
    };

    private Projectiles() {

    }


    public static void cast(Plugin plugin, Player player, CustomProjectile projectile) {
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setDamage(projectile.getDamage());
        arrow.setVelocity(arrow.getVelocity().multiply(projectile.getForce()));
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        arrow.setSilent(true);

        new BukkitRunnable() {
            int ticksLived;

            @Override
            public void run() {
                if (projectile.getTickHandler() != null) {
                    projectile.getTickHandler().onTick(arrow);
                }

                if (projectile.isBouncing()) {
                    Vector newVelocity = calculateBounceVelocity(arrow, projectile.getBounceForceReduction());
                    Vector oldVelocity = arrow.getVelocity();
                    if (!(Objects.equals(newVelocity, oldVelocity)) && projectile.getBounceHandler() != null) {
                        projectile.getBounceHandler().onBounce(arrow);
                    }

                    arrow.setVelocity(newVelocity);
                }

                if (arrow.isDead() || arrow.isInBlock() || ticksLived >= projectile.getLiveTime()) {
                    arrow.remove();
                    if (projectile.getDestroyHandler() != null) {
                        projectile.getDestroyHandler().onDestroy(arrow);
                    }
                    cancel();
                }
                ticksLived++;
            }
        }.runTaskTimer(plugin, 0, 1);
    }


    private static boolean checkNonSolid(Arrow arrow, double x, double y, double z) {
        return !Arrays.asList(NON_SOLID).contains(arrow.getLocation().add(x, y, z).getBlock().getType());
    }

    private static Vector calculateBounceVelocity(Arrow arrow, double bounceForceReduction) {
        Vector velocity = arrow.getVelocity();
        if (checkNonSolid(arrow, 1, 0, 0) && arrow.getVelocity().getX() > 0.1) {
            velocity.setX(velocity.getX() * -bounceForceReduction);
        } else if (checkNonSolid(arrow, -1, 0, 0) && arrow.getVelocity().getX() < -0.1) {
            velocity.setX(velocity.getX() * -bounceForceReduction);
        } else if (checkNonSolid(arrow, 0, 1, 0) && arrow.getVelocity().getY() > 0.1) {
            velocity.setY(velocity.getY() * -bounceForceReduction);
        } else if (checkNonSolid(arrow, 0, -1, 0) && arrow.getVelocity().getY() < -0.1) {
            velocity.setY(velocity.getY() * -bounceForceReduction);
        } else if (checkNonSolid(arrow, 0, 0, 1) && arrow.getVelocity().getZ() > 0.1) {
            velocity.setZ(velocity.getZ() * -bounceForceReduction);
        } else if (checkNonSolid(arrow, 0, 0, -1) && arrow.getVelocity().getZ() < -0.1) {
            velocity.setZ(velocity.getZ() * -bounceForceReduction);
        }

        return velocity;
    }
}

