package de.teamcake.item.component.birchstick;

import org.bukkit.Material;

import de.teamcake.common.util.ItemStackBuilder;
import de.teamcake.item.CustomBlockDropItem;
import de.teamcake.item.CustomItem;
import net.kyori.adventure.text.Component;

public class BirchStick extends CustomItem implements CustomBlockDropItem {

    public BirchStick() {
        super(new ItemStackBuilder(Material.STICK)
                .setCustomModelData(1)
                .setName(Component.text("§fBirch Stick"))
                .setDescription(Component.text("§7It seems that it has something special in it.."))
                .build());
    }

    @Override
    public double getBlockDropChance() {
        return 0.15d;
    }

    @Override
    public Material getSourceMaterial() {
        return Material.BIRCH_LEAVES;
    }
}
