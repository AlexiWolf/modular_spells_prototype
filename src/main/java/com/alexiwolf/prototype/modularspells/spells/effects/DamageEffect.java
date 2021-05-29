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
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class DamageEffect extends Effect {

    private double damage;

    /**
     * Initialize with an EffectType value, and damage amount.
     *
     * @param type   The EffectType for the effect.
     * @param damage The amount of damage to deal to the target.
     */
    public DamageEffect(EffectType type, AmbientEffect ambientEffect, double damage, long coolDown, int manaCost) {
        super(type, ambientEffect, coolDown, manaCost);
        this.damage = damage;
    }

    @Override
    public void applyToEntity(Entity target, Spell spell, Entity caster) {
        super.applyToEntity(target, spell, caster);
        if (target instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) target;
            entity.damage(damage, caster);
        }
    }
}
