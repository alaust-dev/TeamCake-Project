package de.teamcake.item;

import de.teamcake.item.component.birchstick.BirchStick;
import de.teamcake.item.component.earthshard.EarthShard;
import de.teamcake.item.wand.wandofthewater.WandOfTheWater;
import lombok.Getter;

public enum CustomItemMaterial {
    BIRCH_STICK(new BirchStick()),
    EARTH_SHARD(new EarthShard()),
    WAND_OF_THE_WATER(new WandOfTheWater());


    @Getter
    private final CustomItem customItem;

    CustomItemMaterial(CustomItem customItem) {
        this.customItem = customItem;
    }
}
