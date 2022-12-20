package de.teamcake.item.wand.wandofthewater;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import de.teamcake.TeamcakeProject;
import de.teamcake.common.util.ItemStackBuilder;
import de.teamcake.item.CustomCraftItem;
import de.teamcake.item.CustomItem;
import de.teamcake.item.CustomItemMaterial;
import net.kyori.adventure.text.Component;

public class WandOfTheWater extends CustomItem implements CustomCraftItem {

    public WandOfTheWater() {
        super(new ItemStackBuilder(Material.CARROT_ON_A_STICK)
                .setName(Component.text("§3Wand of the Water"))
                .setDescription(Component.text("§7Shoot with a bouncy water ball!"))
                .setCustomModelData(1)
                .build());
    }


    @Override
    public Recipe[] getRecipes() {
        CustomItem birchStick = CustomItemMaterial.BIRCH_STICK.getCustomItem();
        ShapedRecipe recipe = new ShapedRecipe(
                new NamespacedKey(TeamcakeProject.getPlugin(), "wand_of_the_water"), itemStack);

        recipe.shape(" LB", " SL", "S  ");
        recipe.setIngredient('L', Material.LILY_PAD);
        recipe.setIngredient('S', birchStick.getItemStack());
        recipe.setIngredient('B', Material.WATER_BUCKET);
        return new Recipe[]{recipe};
    }
}
