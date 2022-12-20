package de.teamcake.common.util.projectiles;

import org.bukkit.entity.Arrow;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomProjectile {

    private int liveTime = 200;
    private double damage;
    private double force;
    private double bounceForceReduction = 1;
    private boolean bouncing;
    private TickHandler tickHandler;
    private BounceHandler bounceHandler;
    private DestroyHandler destroyHandler;


    public interface TickHandler {

        void onTick(Arrow arrow);
    }

    public interface BounceHandler {

        void onBounce(Arrow arrow);
    }

    public interface DestroyHandler {

        void onDestroy(Arrow arrow);
    }
}