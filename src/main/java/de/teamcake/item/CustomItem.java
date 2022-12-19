package de.teamcake.item;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;

public abstract class CustomItem {

    @Getter
    protected ItemStack itemStack;

    protected CustomItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }


    @Override
    public boolean equals(Object object) {
        if(!(object instanceof CustomItem)) {
            return false;
        }

        ItemStack comparedItemStack =  ((CustomItem) object).itemStack;
        ItemMeta comparedItemMeta = comparedItemStack.getItemMeta();
        ItemMeta itemMeta = itemStack.getItemMeta();

        return itemStack.getType() == comparedItemStack.getType()
                && itemMeta.getCustomModelData() == comparedItemMeta.getCustomModelData();
    }
}
