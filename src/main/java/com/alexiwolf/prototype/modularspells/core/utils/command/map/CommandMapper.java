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

package com.alexiwolf.prototype.modularspells.core.utils.command.map;

import org.bukkit.command.Command;

import java.util.List;

/**
 * Provides a mechanism which allows commands to be loaded with no need to touch the plugin.yml file.
 */
public interface CommandMapper {
    /**
     * Add a command to the server command map.
     * <br>
     * Provides a default fallback prefix.
     * @param command The command to add
     */
    void add(Command command);

    /**
     * Add a command to the server command map.
     * @param fallbackPrefix A custom fallback prefix (added before the command with a ':' to make it unique)
     * @param command The command to add
     */
    void add(String fallbackPrefix, Command command);

    /**
     * Add multiple commands to the server command map.
     * @param fallbackPrefix A custom fallback prefix (added before the command with a ':' to make it unique)
     * @param commands A list of commands to add
     */
    void addAll(String fallbackPrefix, List<Command> commands);
}
