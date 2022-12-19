package de.teamcake.item.listener;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import de.teamcake.item.CustomEntityDropItem;
import de.teamcake.item.CustomItemMaterial;

public class CustomEntityDropListener implements Listener {

    private final EnumMap<EntityType, List<MobDrop>> mobDropHashMap = new EnumMap<>(EntityType.class);

    public CustomEntityDropListener() {
        for (CustomItemMaterial material : CustomItemMaterial.values()) {
            if (!(material.getCustomItem() instanceof CustomEntityDropItem customDropItem)) {
                continue;
            }
            if (!mobDropHashMap.containsKey(customDropItem.getSourceEntity())) {
                mobDropHashMap.put(customDropItem.getSourceEntity(), new ArrayList<>());
            }

            final ItemStack customItemStack = material.getCustomItem().getItemStack();
            final MobDrop blockDrop = new MobDrop(customDropItem.getEntityDropChance(), customItemStack);

            mobDropHashMap.get(customDropItem.getSourceEntity()).add(blockDrop);
        }
    }

    @EventHandler
    protected void onEntityDeath(EntityDeathEvent event) {
        LivingEntity diedEntity = event.getEntity();
        EntityType entityType = diedEntity.getType();
        if (!mobDropHashMap.containsKey(entityType)) {
            return;
        }

        for (MobDrop blockDrop : mobDropHashMap.get(entityType)) {
            if (blockDrop.chance() > Math.random()) {
                diedEntity.getWorld().dropItem(diedEntity.getLocation(), blockDrop.drop());
            }
        }
    }

    private record MobDrop(double chance, ItemStack drop) {

    }
}
