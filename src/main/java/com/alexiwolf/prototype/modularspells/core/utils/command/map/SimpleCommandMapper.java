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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

/**
 * Provides a basic command mapper which automatically finds and registers commands to the server-wide command map.
 */
public class SimpleCommandMapper implements CommandMapper {
    protected String defaultFallbackPrefix;
    protected CommandMap commandMap;
    protected Logger logger;

    public SimpleCommandMapper(String defaultFallbackPrefix) {
        this.defaultFallbackPrefix = defaultFallbackPrefix;
        logger = Bukkit.getLogger();
        commandMap = getCommandMap();
    }

    public SimpleCommandMapper(String defaultFallbackPrefix, CommandMap commandMap, Logger logger) {
        this.defaultFallbackPrefix = defaultFallbackPrefix;
        this.commandMap = commandMap;
        this.logger = logger;
    }

    @Override
    public void add(Command command) {
        commandMap.register(defaultFallbackPrefix, command);
    }

    @Override
    public void add(String fallbackPrefix, Command command) {
        commandMap.register(fallbackPrefix, command);
    }

    @Override
    public void addAll(String fallbackPrefix, List<Command> commands) {
        commandMap.registerAll(fallbackPrefix, commands);
    }

    /**
     * Retrieves the server-wide command map from Bukkit.
     * @return Bukkit's server-wide command map (null if something goes wrong)
     */
    protected CommandMap getCommandMap() {
        CommandMap commandMap = null;
        try {
            commandMap = getGlobalCommandMap();
        } catch (Exception e) {
            logCommandMapError(e);
        }
        return commandMap;
    }

    private CommandMap getGlobalCommandMap() throws IllegalAccessException, NoSuchFieldException {
        Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        commandMapField.setAccessible(true);
        return (CommandMap) commandMapField.get(Bukkit.getServer());
    }

    private void logCommandMapError(Exception e) {
        logger.severe("Failed to retrieve command map: " + e.getMessage() +
                "\nCommands are likely to be broken!");
    }
}
