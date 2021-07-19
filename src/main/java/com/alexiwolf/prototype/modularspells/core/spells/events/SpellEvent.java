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
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Provides the base for events having to do with Spells.  It is recommended SpellEvent be used only as a parent to
 * other, more specific event implementations.  SpellEvent does not provide enough information on its own to be useful
 * to executing specific spell casting steps.
 */
public abstract class SpellEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();

    protected boolean isCancelled = false;

    /**
     * The Spell object associated with the event.
     */
    private Spell spell;

    /**
     * A basic constructor accepting a Spell object.
     * @param spell The Spell object to be used in the spell cast.
     */
    public SpellEvent(Spell spell) {
        this.spell = spell;
    }

    /**
     * Access the spell object associated with the event.
     * @return The Spell object associated with th event.
     */
    public Spell getSpell() {
        return spell;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * A static variant of getHandlers().  Returns the HandlerList object for the Event.
     * @return The HandlerList object.
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}
