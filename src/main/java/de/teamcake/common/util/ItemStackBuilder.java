package de.teamcake.common.util;

import java.util.Arrays;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;

import net.kyori.adventure.text.Component;

public class ItemStackBuilder {
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemStackBuilder(Material material) {
        itemStack = new ItemStack(material);
        itemMeta = itemStack.getItemMeta();
    }


    public ItemStackBuilder setName(Component name) {
        itemMeta.displayName(name);
        return this;
    }

    public ItemStackBuilder setDescription(Component... description) {
        itemMeta.lore(Arrays.asList(description));
        return this;
    }

    public ItemStackBuilder setCustomModelData(int i) {
        itemMeta.setCustomModelData(i);
        return this;
    }

    public ItemStackBuilder setPotionColor(Color color) {
        if(!(itemMeta instanceof PotionMeta meta)) {
            return this;
        }
        meta.setColor(color);
        return this;
    }

    public ItemStackBuilder setPotionData(PotionData data) {
        if(!(itemMeta instanceof PotionMeta meta)) {
            return this;
        }

        meta.setBasePotionData(data);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
