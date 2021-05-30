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

import com.alexiwolf.prototype.modularspells.core.providers.SpellProvider;
import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Listens for PlayerInteractEvents and determines if the player is using a wand.  When a wand item is used, the spell
 * will be triggered.
 */
public class SpellCastListener implements Listener {

    /**
     * The plugin to which this class is associated.  The plugin is needed for creating NamespacedKey objects for
     * persistent data lookups.
     */
    private JavaPlugin plugin;

    /**
     * Provides a Spell to be cast.
     */
    private SpellProvider spellProvider;

    /**
     * @param plugin
     * @param spellProvider
     */
    public SpellCastListener(JavaPlugin plugin, SpellProvider spellProvider) {
        this.plugin = plugin;
        this.spellProvider = spellProvider;
    }

    /**
     * Handles PlayerInteractEvents and verifies if a spell should be cast.
     *
     * @param event The event.
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().isSneaking() && isWandItem(event.getItem())) {
            if (isPunchAction(event.getAction())) {
                spellProvider.selectNextSpell(event.getPlayer());
            } else {
                spellProvider.selectPreviousSpell(event.getPlayer());
            }
            event.getPlayer().sendMessage(
                    ChatColor.GREEN + "Active Spell: " + ChatColor.LIGHT_PURPLE
                            + spellProvider.getActiveSpell(event.getPlayer()).getName());
        } else if (isPunchAction(event.getAction()) && isWandItem(event.getItem())) {
            Spell spell = spellProvider.getActiveSpell(event.getPlayer());
            spell.trigger(event.getPlayer());
            event.setCancelled(true);
        }
    }

    /**
     * Check if the provided ItemStack is a wand item.
     * <p>
     * An item is a "wand" if it has the "isWand"
     * value set to 1 in its PersistentDataContainer.
     *
     * @param item The item to check.
     * @return True if the item is a wand.
     */
    private boolean isWandItem(ItemStack item) {
        boolean isWand = false;
        try {
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();
            isWand = data.get(new NamespacedKey(plugin, "isWand"), PersistentDataType.BYTE) != 0;
        } catch (NullPointerException e) {
        }
        return isWand;
    }

    /**
     * Check if the action is a "punch" action, or a left-click.
     *
     * @param action The action to check.
     * @return True if the Action is a "punch" (left-click.)
     */
    private boolean isPunchAction(Action action) {
        return ((action == Action.LEFT_CLICK_AIR) || (action == Action.LEFT_CLICK_BLOCK));
    }
}
