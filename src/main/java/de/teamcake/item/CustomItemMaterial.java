package de.teamcake.item;

import de.teamcake.item.component.birchstick.BirchStick;
import de.teamcake.item.component.earthshard.EarthShard;
import lombok.Getter;

public enum CustomItemMaterial {
    BIRCH_STICK(new BirchStick()),
    EARTH_SHARD(new EarthShard());


    @Getter
    private final CustomItem customItem;

    CustomItemMaterial(CustomItem customItem) {
        this.customItem = customItem;
    }
}
