package de.teamcake.item.component.manapotion;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import de.teamcake.TeamcakeProject;
import de.teamcake.common.util.ItemStackBuilder;
import de.teamcake.item.CustomCraftItem;
import de.teamcake.item.CustomItem;
import net.kyori.adventure.text.Component;

public class ManaPotion extends CustomItem implements CustomCraftItem {

    public ManaPotion() {
        super(new ItemStackBuilder(Material.POTION)
                .setCustomModelData(1)
                .setName(Component.text("§fMana Potion"))
                .setDescription(Component.text("§fIt refills your mana bar."))
                .setPotionColor(Color.WHITE)
                .build());

    }

    @Override
    public Recipe[] getRecipes() {
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(TeamcakeProject.getPlugin(),
                "mana_potion"), getItemStack());
        recipe.addIngredient(Material.CORNFLOWER);
        recipe.addIngredient(Material.GLASS_BOTTLE);
        return new Recipe[]{recipe};
    }
}
