package de.teamcake.item;

import de.teamcake.item.component.birchstick.BirchStick;
import lombok.Getter;

public enum CustomItemMaterial {
    BIRCH_STICK(new BirchStick());


    @Getter
    private final CustomItem customItem;

    CustomItemMaterial(CustomItem customItem) {
        this.customItem = customItem;
    }
}
