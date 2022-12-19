package de.teamcake.item.listener;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.teamcake.item.CustomBlockDropItem;
import de.teamcake.item.CustomItemMaterial;

public class CustomBlockDropListener implements Listener {

    private final EnumMap<Material, List<BlockDrop>> blockDropHashMap = new EnumMap<>(Material.class);

    public CustomBlockDropListener() {
        for (CustomItemMaterial material : CustomItemMaterial.values()) {
            if (!(material.getCustomItem() instanceof CustomBlockDropItem customDropItem)) {
                continue;
            }
            if(!blockDropHashMap.containsKey(customDropItem.getSourceMaterial())) {
                blockDropHashMap.put(customDropItem.getSourceMaterial(), new ArrayList<>());
            }

            final ItemStack customItemStack = material.getCustomItem().getItemStack();
            final BlockDrop blockDrop = new BlockDrop(customDropItem.getBlockDropChance(), customItemStack);

            blockDropHashMap.get(customDropItem.getSourceMaterial()).add(blockDrop);
        }
    }

    @EventHandler
    protected void onBlockBreak(BlockBreakEvent event) {
        Block destroyedBlock = event.getBlock();
        Material destroyedBlockMaterial = destroyedBlock.getType();
        if (!blockDropHashMap.containsKey(destroyedBlockMaterial)) {
            return;
        }

        for (BlockDrop blockDrop : blockDropHashMap.get(destroyedBlockMaterial)) {
            if (blockDrop.chance() > Math.random()) {
                destroyedBlock.getWorld().dropItem(destroyedBlock.getLocation(), blockDrop.drop());
            }
        }
    }

    private record BlockDrop(double chance, ItemStack drop) {

    }
}
