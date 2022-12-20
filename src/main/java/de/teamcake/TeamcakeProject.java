package de.teamcake;

import de.teamcake.item.component.manapotion.ManaPotionConsumableListener;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.teamcake.item.CustomCraftItem;
import de.teamcake.item.CustomItemMaterial;
import de.teamcake.item.listener.CustomBlockDropListener;
import de.teamcake.item.listener.CustomEntityDropListener;
import de.teamcake.item.wand.wandofthewater.WOTWInteractionListener;
import de.teamcake.mechanic.darkervision.DarkerVisionRunnable;
import de.teamcake.mechanic.mana.runnables.ManaRegenerationRunnable;
import de.teamcake.mechanic.mana.runnables.ManaRenderRunnable;
import lombok.Getter;

public class TeamcakeProject extends JavaPlugin {

    @Getter
    public static TeamcakeProject plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerCustomRecipes();

        initializeRunnable();
        initializeListeners();
    }

    private void initializeRunnable() {
        new DarkerVisionRunnable().runTaskTimer(this, 0, 1);
        new ManaRenderRunnable().runTaskTimer(this, 0, 1);
        new ManaRegenerationRunnable().runTaskTimer(this, 0, 1);
    }

    private void initializeListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new CustomBlockDropListener(), this);
        pluginManager.registerEvents(new CustomEntityDropListener(), this);
        
        pluginManager.registerEvents(new ManaPotionConsumableListener(), this);
        pluginManager.registerEvents(new WOTWInteractionListener(), this);
    }

    private void registerCustomRecipes() {
        for(CustomItemMaterial material : CustomItemMaterial.values()) {
            if(!(material.getCustomItem() instanceof CustomCraftItem customCraftItem)) {
                continue;
            }

            for(Recipe recipe : customCraftItem.getRecipes()) {
                getServer().addRecipe(recipe);
            }
        }
    }
}
