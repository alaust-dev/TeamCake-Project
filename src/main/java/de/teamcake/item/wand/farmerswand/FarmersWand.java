package de.teamcake.item.wand.farmerswand;

import de.teamcake.TeamcakeProject;
import de.teamcake.common.util.ItemStackBuilder;
import de.teamcake.item.CustomCraftItem;
import de.teamcake.item.CustomItem;
import de.teamcake.item.CustomItemMaterial;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

public class FarmersWand extends CustomItem implements CustomCraftItem {
    public FarmersWand() {
        super(new ItemStackBuilder(Material.CARROT_ON_A_STICK)
                .setName(Component.text("§3Farmer's Wand"))
                .setDescription(Component.text("§7Were used since generations to boost the growth speed."))
                .setCustomModelData(3)
                .build());
    }

    @Override
    public Recipe[] getRecipes() {
        CustomItem birchStick = CustomItemMaterial.BIRCH_STICK.getCustomItem();
        ShapedRecipe recipe = new ShapedRecipe(
                new NamespacedKey(TeamcakeProject.getPlugin(), "farmers_wand"), itemStack);

        recipe.shape(" BW", " SB", "S  ");
        recipe.setIngredient('W', Material.WHEAT);
        recipe.setIngredient('S', birchStick.getItemStack());
        recipe.setIngredient('B', Material.BONE);
        return new Recipe[]{recipe};
    }
}
