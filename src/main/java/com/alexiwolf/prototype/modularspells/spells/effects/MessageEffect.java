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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

/**
 * Sends a message to the casting player if EffectType.CASTER, or to all players in the effected area if
 * EffectType.CASTER_AREA or EffectType.IMPACT_AREA.
 */
public class MessageEffect extends Effect {

    /**
     * Sends a message to the target.
     */
    private String message;

    /**
     * The radius in blocks to apply the effect if it is an area type.
     */
    private float radius;

    /**
     * Initialize with an EffectType value.
     *
     * @param type    The EffectType for the effect.
     * @param message The message the effect will send.
     */
    public MessageEffect(EffectType type, String message) {
        super(type, new NullAmbientEffect(),0, 0);
        this.message = message;
        this.radius = 3;
    }

    @Override
    public void applyToEntity(Entity target, Spell spell, Entity caster) {
        if (isAreaEffect()) {
            applyToLocation(target.getLocation(), spell, caster);
        } else {
            if (target instanceof Player) {
                sendMessage((Player) target);
            }
        }
    }

    @Override
    public void applyToLocation(Location location, Spell spell, Entity caster) {
        if (isAreaEffect())
            location.getNearbyPlayers(radius).forEach(this::sendMessage);
    }

    private void sendMessage(Player player) {
        player.sendMessage(message);
    }

    private boolean isAreaEffect() {
        return type == EffectType.CASTER_AREA || type == EffectType.IMPACT_AREA;
    }
}
