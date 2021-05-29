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
 * Signals the start of a spell cast.  This pre-cast phase should be used to perform pre-checks and to cancel the spell
 * if need be.
 */
public class SpellPrecastEvent extends SpellEvent implements Casted {

    private Entity caster;

    public SpellPrecastEvent(Spell spell, Entity caster) {
        super(spell);
        this.caster = caster;
    }

    @Override
    public Entity getCaster() {
        return caster;
    }
}
