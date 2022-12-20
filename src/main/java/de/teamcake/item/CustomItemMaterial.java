package de.teamcake.item;

import de.teamcake.item.component.birchstick.BirchStick;
import de.teamcake.item.component.earthshard.EarthShard;
import de.teamcake.item.component.manapotion.ManaPotion;
import lombok.Getter;

public enum CustomItemMaterial {
    BIRCH_STICK(new BirchStick()),
    EARTH_SHARD(new EarthShard()),
    MANA_POTION(new ManaPotion());


    @Getter
    private final CustomItem customItem;

    CustomItemMaterial(CustomItem customItem) {
        this.customItem = customItem;
    }
}
