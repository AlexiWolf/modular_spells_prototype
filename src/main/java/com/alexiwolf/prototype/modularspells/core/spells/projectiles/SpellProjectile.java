/*
 * Copyright (C) 2021 AlexiWolf
 *
 * This file is part of Modular Spells.
 *
 * Modular Spells is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Modular Spells is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Modular Spells.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.alexiwolf.prototype.modularspells.core.spells.projectiles;


import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.AmbientEffect;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellImpactEvent;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class SpellProjectile {

    protected Entity caster;
    protected Spell spell;
    protected Location location;
    protected Vector velocity;
    protected double gravity;
    protected PluginManager pluginManager;
    protected AmbientEffect ambientEffect;

    protected RayTraceResult impact = null;
    protected boolean hasImpacted = false;

    public SpellProjectile(
            Spell spell,
            Entity caster,
            Location location,
            Vector velocity,
            double gravity,
            AmbientEffect ambientEffect
    ) {
        this.caster = caster;
        this.spell = spell;
        this.location = location;
        this.velocity = velocity;
        this.gravity = gravity;
        this.ambientEffect = ambientEffect;

        pluginManager = Bukkit.getPluginManager();
    }

    public void tick() {
        if (impact == null) {
            update();
            ambientEffect.play(location);
        } else if (!hasImpacted) {
            if (impact.getHitEntity() != caster) {
                if (impact.getHitEntity() instanceof Player && caster instanceof Player) {
                    Player player = (Player) caster;
                    player.playSound(player.getEyeLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                }
                World world = location.getWorld();
                Vector hitPosition = impact.getHitPosition();
                Location impactLocation = new Location(world, hitPosition.getX(), hitPosition.getY(), hitPosition.getZ());
                pluginManager.callEvent(new SpellImpactEvent(spell, caster, impactLocation, impact.getHitEntity()));
                hasImpacted = true;
            } else {
                impact = null;
            }
        }
    }

    public void update() {
        velocity.setY(velocity.getY() - gravity);
        location.add(velocity);
        impact = rayTraceImpact();
    }

    private RayTraceResult rayTraceImpact() {
        World world = location.getWorld();
        return world.rayTrace(location, velocity.clone().normalize(), 2.5, FluidCollisionMode.ALWAYS, true, 0.2, null);
    }

    public Entity getCaster() {
        return caster;
    }

}
