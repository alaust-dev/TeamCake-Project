package de.teamcake.item;

import org.bukkit.entity.EntityType;

public interface CustomEntityDropItem {

    double getEntityDropChance();

    EntityType getSourceEntity();
}
