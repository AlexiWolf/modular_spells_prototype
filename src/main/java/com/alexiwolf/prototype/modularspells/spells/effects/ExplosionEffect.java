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

import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.NullAmbientEffect;
import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.effects.Effect;
import com.alexiwolf.prototype.modularspells.core.spells.effects.EffectType;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

public class ExplosionEffect extends Effect {

    private float power;

    public ExplosionEffect(EffectType type, float power, long coolDown, int manaCost) {
        super(type, new NullAmbientEffect(), coolDown, manaCost);
        this.power = power;
    }

    @Override
    public void applyToEntity(Entity target, Spell spell, Entity caster) {
        applyToLocation(target.getLocation(), spell, caster);
    }

    @Override
    public void applyToLocation(Location location, Spell spell, Entity _caster) {
        World world = location.getWorld();
        world.createExplosion(location, power, false, false);
    }
}
