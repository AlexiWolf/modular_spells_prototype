package com.alexiwolf.prototype.modularspells.spells.effects.ambient;

import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.AmbientEffect;
import org.bukkit.*;

public class LolAmbientEffect implements AmbientEffect {
    @Override
    public void play(Location location) {
        World world = location.getWorld();
        world.spawnFallingBlock(location, Material.DIAMOND_BLOCK, (byte) 0);
    }
}
