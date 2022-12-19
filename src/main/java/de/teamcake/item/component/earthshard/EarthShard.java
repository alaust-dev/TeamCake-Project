package de.teamcake.item.component.earthshard;

import de.teamcake.TeamcakeProject;
import de.teamcake.common.util.ItemStackBuilder;
import de.teamcake.item.CustomCraftItem;
import de.teamcake.item.CustomItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

public class EarthShard extends CustomItem implements CustomCraftItem {

    public EarthShard() {
        super(new ItemStackBuilder(Material.AMETHYST_SHARD)
                .setCustomModelData(1)
                .setName(Component.text("§fEarth Shard"))
                .setDescription(Component.text("§7It keeps the power of earth in it.."))
                .build());
    }

    @Override
    public Recipe[] getRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(TeamcakeProject.getPlugin(),
                "earth_shard"), getItemStack());
        recipe.addIngredient(Material.AMETHYST_SHARD);
        recipe.addIngredient(Material.COBBLESTONE);
        recipe.addIngredient(Material.DIRT);
        return new Recipe[]{recipe};
    }
}
