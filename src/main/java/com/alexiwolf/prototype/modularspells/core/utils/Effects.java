package com.alexiwolf.prototype.modularspells.core.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Effects {
    public static void fizzle(Entity entity) {
        if (entity instanceof LivingEntity) {
            LivingEntity caster = (LivingEntity) entity;
            Location location = caster.getEyeLocation();
            Vector direction = location.getDirection();
            World world = caster.getWorld();
            world.playSound(location, Sound.BLOCK_FIRE_EXTINGUISH, 0.5F, 1F);
            world.spawnParticle(
                    Particle.ASH,
                    location,
                    24,
                    direction.getX(),
                    direction.getY(),
                    direction.getZ(),
                    1,
                    null,
                    true
            );
        }
    }
}
