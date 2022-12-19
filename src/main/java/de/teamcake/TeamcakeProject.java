package de.teamcake;

import org.bukkit.plugin.java.JavaPlugin;

import de.teamcake.mechanic.darkervision.DarkerVisionRunnable;
import de.teamcake.mechanic.mana.runnables.ManaRegenerationRunnable;
import de.teamcake.mechanic.mana.runnables.ManaRenderRunnable;

public class TeamcakeProject extends JavaPlugin {

    @Override
    public void onEnable() {
        initializeRunnable();
    }

    private void initializeRunnable() {
        new DarkerVisionRunnable().runTaskTimer(this, 0, 1);
        new ManaRenderRunnable().runTaskTimer(this, 0, 1);
        new ManaRegenerationRunnable().runTaskTimer(this, 0, 1);
    }
}
