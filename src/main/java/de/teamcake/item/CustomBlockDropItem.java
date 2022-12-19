package de.teamcake.item;

import org.bukkit.Material;

public interface CustomBlockDropItem {

    double getBlockDropChance();

    Material getSourceMaterial();
}
