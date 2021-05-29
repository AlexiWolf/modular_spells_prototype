package com.alexiwolf.prototype.modularspells.spells.effects.ambient;

import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.AmbientEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class FireBallAmbientEffect implements AmbientEffect {
    @Override
    public void play(Location location) {
        World world = location.getWorld();
        world.spawnParticle(Particle.FLAME, location, 1, 0.02, 0.02, 0.02, 0.02, null, true);
        world.spawnParticle(Particle.SMOKE_NORMAL, location, 3, 0.1, 0.1, 0.1, 0.02, null, true);
        world.spawnParticle(Particle.LAVA, location, 1, 0.1, 0.1, 0.1, 0.03, null, true);
    }
}
