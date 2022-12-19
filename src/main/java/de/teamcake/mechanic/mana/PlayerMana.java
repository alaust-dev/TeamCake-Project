package de.teamcake.mechanic.mana;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;

public class PlayerMana {

    private static final long REGENERATION_COOLDOWN = TimeUnit.SECONDS.toMillis(10);
    private static final long REGENERATION_STEPS_COOLDOWN = 500;
    private static final HashMap<Player, Integer> playersMana = new HashMap<>();
    private static final HashMap<Player, Long> regenerationCoolDowns = new HashMap<>();

    private PlayerMana() {
    }

    public static void addMana(Player player, int amount) {
        final int finalAmount = Math.abs(amount);
        final int currentMana = playersMana.get(player);

        playersMana.computeIfAbsent(player, newPlayer -> playersMana.put(newPlayer, finalAmount));
        if (currentMana + amount > 10) {
            playersMana.put(player, 10);
            return;
        }

        playersMana.put(player, currentMana + amount);
        regenerationCoolDowns.put(player, System.currentTimeMillis() + REGENERATION_STEPS_COOLDOWN);
    }

    public static void consumeMana(Player player, int amount) {
        final int finalAmount = Math.min(10, Math.abs(amount));
        final int currentMana = playersMana.get(player);

        regenerationCoolDowns.put(player,
                System.currentTimeMillis() + REGENERATION_COOLDOWN);
        playersMana.computeIfAbsent(player, newPlayer -> playersMana.put(player, 10 - finalAmount));
        if (currentMana - amount < 0) {
            playersMana.put(player, 0);
            return;
        }

        playersMana.put(player, currentMana - amount);
        regenerationCoolDowns.put(player,
                System.currentTimeMillis() + REGENERATION_COOLDOWN);
    }

    public static boolean canRegenerate(Player player) {
        regenerationCoolDowns.computeIfAbsent(player, newPlayer -> regenerationCoolDowns.put(newPlayer,
                System.currentTimeMillis() + REGENERATION_STEPS_COOLDOWN));

        return regenerationCoolDowns.get(player) <= System.currentTimeMillis();
    }

    public static int getMana(Player player) {
        playersMana.computeIfAbsent(player, newPlayer -> playersMana.put(player, 0));
        return playersMana.get(player);
    }
}
