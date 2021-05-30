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

package com.alexiwolf.prototype.modularspells.core.spells.effects;

import com.alexiwolf.prototype.modularspells.core.spells.ManaConsumer;
import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.AmbientEffect;
import com.alexiwolf.prototype.modularspells.core.spells.RateLimited;
import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.List;

/**
 * Provides the base programming interface for spell effects, as well as some default implementations for convenience
 * methods.
 */
public class Effect implements RateLimited, ManaConsumer, AmbientEffect {

    /**
     * The type of the effect.  This controls how and when the effect is applied in the spell casting process.
     * <p>
     * See {@link EffectType} for more information.
     */
    protected EffectType type;
    protected AmbientEffect ambientEffect;
    protected long coolDown;
    protected int manaCost;

    /**
     * Initialize with a type and a cool-down.
     * @param type     The EffectType for the effect.
     * @param coolDown The time between uses in milliseconds.
     */
    public Effect(EffectType type, AmbientEffect ambientEffect, long coolDown, int manaCost) {
        this.type = type;
        this.ambientEffect = ambientEffect;
        this.coolDown = coolDown;
        this.manaCost = manaCost;
    }

    /**
     * Apply the effect to an Entity.  Some effects may instead apply to that entity's location.
     *
     * @param targets  A List of target Entities to which, or at which to apply the effect.
     * @param spell    The Spell from which the effect was cast.
     */
    public void applyToAllEntities(List<Entity> targets, Spell spell, Entity caster) {
        targets.forEach((Entity target) ->
                applyToEntity(target, spell, caster));
    }

    /**
     * Apply the effect to an Entity.  Some effects may instead apply to that entity's location.
     *
     * @param target   A target Entity to which, or at which to apply the effect.
     * @param spell    The Spell from which the effect was cast.
     */
    public void applyToEntity(Entity target, Spell spell, Entity caster) {
        play(target.getLocation());
    }

    /**
     * Apply the effect at a List of Locations.  Some effects may instead apply to any entity at or near the Locations
     * as applicable.
     *
     * @param locations A List of Locations at which to apply the effect.
     * @param spell     The Spell from which the effect was cast.
     */
    public void applyToAllLocations(List<Location> locations, Spell spell, Entity caster) {
        locations.forEach((Location location) ->
                applyToLocation(location, spell, caster));
    }

    /**
     * Apply the effect to a Location.  Some effects may instead apply to any entity at or near the Location as
     * applicable.
     *
     * @param location A Location at which to apply the effect.
     * @param spell    The Spell from which the effect was cast.
     */
    public void applyToLocation(Location location, Spell spell, Entity caster) {
        play(location);
    }

    /**
     * Access the EffectType.
     *
     * @return The EffectType.
     */
    public EffectType getType() {
        return type;
    }

    @Override
    public void play(Location location) {
        ambientEffect.play(location);
    }

    @Override
    public long getCoolDown() {
        return coolDown;
    }

    @Override
    public int getManaCost() {
        return this.manaCost;
    }

    public String getLore() {
        return ChatColor.RED + "Unknown Effects";
    }
}
