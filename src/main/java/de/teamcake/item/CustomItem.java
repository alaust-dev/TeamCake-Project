package de.teamcake.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;

public abstract class CustomItem {

    @Getter
    protected final ItemStack itemStack;

    protected CustomItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }


    public boolean isSimilarTo(ItemStack similarItemStack) {
        final ItemMeta similarItemMeta = similarItemStack.getItemMeta();
        final ItemMeta itemMeta = itemStack.getItemMeta();

        return itemStack.getType() == similarItemStack.getType()
                && itemMeta.getCustomModelData() == similarItemMeta.getCustomModelData();
    }
}
