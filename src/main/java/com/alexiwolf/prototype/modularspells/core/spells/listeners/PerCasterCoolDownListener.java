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

package com.alexiwolf.prototype.modularspells.core.spells.listeners;

import com.alexiwolf.prototype.modularspells.core.spells.events.SpellPrecastEvent;
import com.alexiwolf.prototype.modularspells.core.utils.Effects;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Imposes a delay for spell casting by listening for SpellPrecastEvents and cancel the spell if too little time
 * has passed since the last cast.
 */
public class PerCasterCoolDownListener implements Listener {
    private final Map<UUID, Long> coolDownMap = new HashMap<>();

    /**
     * Blocks the spell cast if to little time has passed since the last spell cast.
     * @param event The pre-cast event.
     */
    @EventHandler(priority = EventPriority.NORMAL)
    public void onSpellPrecastEvent(SpellPrecastEvent event) {
        if (!event.isCancelled()) {
            UUID uuid = event.getCaster().getUniqueId();
            long currentTime = System.currentTimeMillis();
            long coolDownEnd = coolDownMap.getOrDefault(uuid, -1L);
            if (coolDownEnd <= currentTime) {
                coolDownMap.put(uuid, currentTime + event.getSpell().getCoolDown());
            } else {
                event.setCancelled(true);
                Entity entity = event.getCaster();
                Effects.fizzle(entity);
                entity.sendMessage(
                    ChatColor.RED + "Spell is cooling down.  Wait " + ((double) (coolDownEnd - currentTime) / 1000) + " more seconds."
                );
            }
        }
    }
}
