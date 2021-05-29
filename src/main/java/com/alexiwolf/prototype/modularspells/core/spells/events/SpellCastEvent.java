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

package com.alexiwolf.prototype.modularspells.core.spells.events;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import org.bukkit.entity.Entity;

/**
 * Signals an actual spell cast.
 */
public class SpellCastEvent extends SpellEvent implements Casted {
    private Entity caster;

    /**
     * A basic constructor accepting a Spell object, and the Entity casting it.
     *
     * @param spell  The Spell object to be used in the spell cast.
     * @param caster The Entity responsible for casting the spell.  This Entity can be considered the spell's origin.
     */
    public SpellCastEvent(Spell spell, Entity caster) {
        super(spell);
        this.caster = caster;
    }

    /**
     * Access the spell's Caster.
     * @return The Entity responsible for casting the spell.
     */
    public Entity getCaster() {
        return caster;
    }
}
