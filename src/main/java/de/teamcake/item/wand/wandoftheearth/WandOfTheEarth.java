package de.teamcake.item.wand.wandoftheearth;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import de.teamcake.TeamcakeProject;
import de.teamcake.common.util.ItemStackBuilder;
import de.teamcake.item.CustomCraftItem;
import de.teamcake.item.CustomItem;
import de.teamcake.item.CustomItemMaterial;
import net.kyori.adventure.text.Component;

public class WandOfTheEarth extends CustomItem implements CustomCraftItem {

    public WandOfTheEarth() {
        super(new ItemStackBuilder(Material.CARROT_ON_A_STICK)
                .setName(Component.text("§2Wand of the Earth"))
                .setDescription(Component.text("§7Throw a dirtball or place a block, you decide!"))
                .setCustomModelData(2)
                .build());
    }

    @Override
    public Recipe[] getRecipes() {
        CustomItem birchStick = CustomItemMaterial.BIRCH_STICK.getCustomItem();
        CustomItem earthShard = CustomItemMaterial.EARTH_SHARD.getCustomItem();
        ShapedRecipe recipe = new ShapedRecipe(
                new NamespacedKey(TeamcakeProject.getPlugin(), "wand_of_the_earth"), itemStack);

        recipe.shape(" ED", " SE", "S  ");
        recipe.setIngredient('E', earthShard.getItemStack());
        recipe.setIngredient('S', birchStick.getItemStack());
        recipe.setIngredient('D', Material.DIRT);
        return new Recipe[]{recipe};
    }
}
