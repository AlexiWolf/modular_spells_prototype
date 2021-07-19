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

package com.alexiwolf.prototype.modularspells.core.utils.command.map;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides very minimal, plugin-centric extensions to SimplePluginMapper to make it slightly cleaner to use.
 * <br>
 * PluginCommandMapper uses the provided plugin's name as the defaultFallbackPrefix, and uses the plugin's logger
 * instead of Bukkit's logger.
 */
public class PluginCommandMapper extends SimpleCommandMapper {
    public PluginCommandMapper(JavaPlugin plugin) {
        super(plugin.getName());
        logger = plugin.getLogger();
    }
}
