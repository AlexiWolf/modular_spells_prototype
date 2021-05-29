/*
 * Copyright (C) 2020 AlexiWolf
 *
 * This file is part of Modular Spells.
 *
 * Modular Spells is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Modular Spells is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modular Spells.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.alexiwolf.prototype.modularspells.spells.effects;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.effects.Effect;
import com.alexiwolf.prototype.modularspells.core.spells.effects.EffectType;
import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.AmbientEffect;
import com.alexiwolf.prototype.modularspells.core.spells.projectiles.ProjectileUpdateTask;
import com.alexiwolf.prototype.modularspells.core.spells.projectiles.SpellProjectile;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

/**
 * An Effect that shoots a SpellProjectile.
 */
public class ProjectileEffect extends Effect {

    private ProjectileUpdateTask projectileSystem;
    private double launchVelocity;
    private double gravity;

    /**
     * Default constructor requiring a reference to the plugin's ProjectileSystem, and a launch velocity.
     * @param projectileSystem The ProjectileSystem required to handle the shooting and calculations of the projectile.
     * @param launchVelocity   How fast to launch the projectile.
     */
    public ProjectileEffect(
            ProjectileUpdateTask projectileSystem,
            double launchVelocity,
            long coolDown,
            int manaCost,
            AmbientEffect ambientEffect
    ) {
        super(EffectType.PROJECTILE, ambientEffect, coolDown, manaCost);
        this.projectileSystem = projectileSystem;
        this.launchVelocity = launchVelocity;
        this.gravity = 0.05;
    }

    /**
     *
     * @param projectileSystem The ProjectileSystem required to handle the shooting and calculations of the projectile.
     * @param launchVelocity   How fast to launch the projectile.
     * @param gravity          The downward force of gravity to apply to the projectile.
     */
    public ProjectileEffect(
            ProjectileUpdateTask projectileSystem,
            double launchVelocity,
            long coolDown,
            int manaCost,
            double gravity,
            AmbientEffect ambientEffect
    ) {
        super(EffectType.PROJECTILE, ambientEffect, coolDown, manaCost);
        this.projectileSystem = projectileSystem;
        this.launchVelocity = launchVelocity;
        this.gravity = gravity;
    }

    @Override
    public void applyToEntity(Entity entity, Spell spell, Entity caster) {
        if (entity instanceof LivingEntity) {
            LivingEntity shooter = (LivingEntity) caster;
            Location casterEyeLocation = shooter.getEyeLocation().clone();
            Vector casterLookDirection = casterEyeLocation.getDirection().normalize();
            Vector projectileVelocity = casterLookDirection.multiply(launchVelocity);
            projectileSystem.shoot(
                new SpellProjectile(spell, shooter, casterEyeLocation, projectileVelocity, gravity, ambientEffect)
            );
        }
    }

    @Override
    public void applyToLocation(Location location, Spell spell, Entity caster) {/* Effect requires an entity. */}
}
