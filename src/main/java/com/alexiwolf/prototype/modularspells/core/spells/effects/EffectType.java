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

package com.alexiwolf.prototype.modularspells.core.spells.effects;

/**
 * Provides information about when / how an effect should be applied.
 */
public enum EffectType {
    /**
     * The effect launches something.
     * <p>
     * PROJECTILE type effects are applied as the spell is cast.
     */
    PROJECTILE,

    /**
     * The effect is applied to a single target entity or location.
     * <p>
     * IMPACT type effects are applied when a PROJECTILE type effect hits something.
     */
    IMPACT,

    /**
     * The effect is applied in an area around an impact target entity or location effecting all entities, or locations
     * within a given radius.
     */
    IMPACT_AREA,

    /**
     * The effect is applied to the one casting the spell.
     * <p>
     * CASTER type effects are applied as the spell is cast.
     */
    CASTER,

    /**
     * The effect is applied in an area around the one casting the spell effecting all entities, or locations within a
     * given radius.
     * <p>
     * CASTER_AREA type effects are applied as the spell is cast.
     */
    CASTER_AREA
}
