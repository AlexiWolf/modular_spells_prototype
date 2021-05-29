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

package com.alexiwolf.prototype.modularspells.core.spells;

import com.alexiwolf.prototype.modularspells.core.spells.effects.Effect;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellCastEvent;
import com.alexiwolf.prototype.modularspells.core.spells.effects.EffectType;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellImpactEvent;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellPrecastEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a set of effects to be cast.
 */
public class Spell implements RateLimited, ManaConsumer {
    final Set<Effect> effects = new HashSet<>();

    String name;

    PluginManager pluginManager;

    /**
     * Initialize the Spell with no effects.
     */
    public Spell() {
        pluginManager = Bukkit.getPluginManager();
        name = "Spell";
    }

    /**
     * Initialize the spell with a custom PluginManager.
     *
     * @param pluginManager The PluginManager to use for triggering events.
     */
    public Spell(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
        name = "Spell";
    }

    /**
     * Initialize the spell with default effects.
     *
     * @param effects The starting effect of the spell.
     */
    public Spell(Collection<? extends Effect> effects) {
        this.effects.addAll(effects);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add and effect to the spell.
     * <p>
     * Requests to add duplicate effects should be ignored.
     *
     * @param effect The effect to add.
     */
    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    /**
     * Add a collection of effects to the spell.
     * <p>
     * Requests to add duplicate effects should be ignored.
     */
    public void addAllEffects(Collection<? extends Effect> effects) {
        this.effects.addAll(effects);
    }

    /**
     * Remove an effect from the spell.
     * <p>
     * Requests to remove non-existent effects should be ignored.
     *
     * @param effect The effect to remove.
     */
    public void removeEffect(Effect effect) {
        effects.remove(effect);
    }

    /**
     * Remove a collection of effects from the spell.
     * <p>
     * Requests to remove non-existent effects should be ignored.
     *
     * @param effects The effects to remove.
     */
    public void removeAllEffects(Collection<? extends Effect> effects) {
        this.effects.removeAll(effects);
    }

    /**
     * Get a List of the Spell's effects.
     * <p>
     * The list should be a copy of the internal Effects set.
     *
     * @return A copy of the spell's effects.
     */
    public List<Effect> getEffects() {
        return new ArrayList<>(effects);
    }

    /**
     * Get a List of the Spell's Effects which match the requested EffectType
     *
     * @param type The desired EffectType of the returned effects.
     * @return A List containing the filtered Effects.
     */
    public List<Effect> getEffectsOfType(EffectType ... type) {
        List<EffectType> types = Arrays.asList(type);
        return effects.stream()
                .filter(effect -> types.contains(effect.getType()))
                .collect(Collectors.toList());
    }

    /**
     * Initiate a spell cast.
     * <p>
     * This will trigger a PrecastSpellEvent.
     *
     * @param caster The entity casting the spell.  This entity can be considered the spell's origin.
     */
    public void trigger(Entity caster) {
        pluginManager.callEvent(new SpellPrecastEvent(this, caster));
    }

    /**
     * Start the actual spell cast.
     * <p>
     * No pre-checks are done in this stage.
     *
     * @param caster The entity casting the spell.  This entity can be considered the spell's origin.
     */
    public void cast(Entity caster) {
        pluginManager.callEvent(new SpellCastEvent(this, caster));
    }

    public void impact(Entity caster, Location impactLocation, Entity impactedEntity) {
        pluginManager.callEvent(new SpellImpactEvent(this, caster, impactLocation, impactedEntity));
    }

    /**
     * Apply all effects of type CASTER, and CASTER_AREA.
     *
     * @param caster The entity casting the spell.  This entity will be provided to the effects as the target.
     */
    public void applyCasterEffects(Entity caster) {
        List<Effect> effects = getEffectsOfType(EffectType.CASTER, EffectType.CASTER_AREA);
        effects.forEach(effect -> effect.applyToEntity(caster, this, caster));
    }

    /**
     * Apply all effects of type PROJECTILE.
     *
     * @param caster The entity casting the spell.  This entity will be provided to the effects as the origin.
     */
    public void launchProjectileEffects(Entity caster) {
        List<Effect> effects = getEffectsOfType(EffectType.PROJECTILE);
        effects.forEach(effect -> effect.applyToEntity(caster, this, caster));
    }

    @Override
    public long getCoolDown() {
        return effects.stream()
                .mapToLong(Effect::getCoolDown)
                .sum();
    }

    @Override
    public int getManaCost() {
        return effects.stream()
                .mapToInt(Effect::getManaCost)
                .sum();
    }
}