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

package com.alexiwolf.prototype.modularspells.core.spells.events;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class SpellImpactEvent extends SpellEvent implements Casted {

    private Entity caster;

    private Location impactLocation;

    private Entity impactedEntity;

    /**
     * A basic constructor which satisfies SpellCastEvent's constructor, and takes the location and impacted entity of
     * a projectile.
     *
     * @param spell  The Spell object to be used in the spell cast.
     * @param caster The Entity responsible for casting the spell.  This Entity can be considered the spell's origin.
     */
    public SpellImpactEvent(Spell spell, Entity caster, Location impactLocation, Entity impactedEntity) {
        super(spell);
        this.caster = caster;
        this.impactLocation = impactLocation;
        this.impactedEntity = impactedEntity;
    }

    /**
     * Get the impact location.
     * @return The impact location.
     */
    public Location getImpactLocation() {
        return impactLocation;
    }

    /**
     * Get the Entity, if any, that was impacted by the spell.
     * @return The Entity if one was impacted, otherwise null.
     */
    public Entity getImpactedEntity() {
        return impactedEntity;
    }

    /**
     * Check if the projectile hit an entity.
     * @return True if an entity was impacted, otherwise false.
     */
    public boolean hasImpactedEntity() {
        return impactedEntity != null;
    }

    @Override
    public Entity getCaster() {
        return caster;
    }
}
