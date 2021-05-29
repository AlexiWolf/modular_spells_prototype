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

package com.alexiwolf.prototype.modularspells.core.utils.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Provides a plugin-centric command base with pre-run checks (permissions mostly.)
 */
public abstract class PluginCommand extends Command implements PluginIdentifiableCommand {
    private JavaPlugin plugin;

    public PluginCommand(String name, JavaPlugin plugin) {
        super(name);
        this.plugin = plugin;
    }

    public PluginCommand(String name, JavaPlugin plugin, String permission) {
        super(name);
        this.plugin = plugin;
        setPermission(permission);
    }

    /**
     * Handle the command's logic here instead of the execute method, as execute is used to do pre-processing.
     * @param sender Entity making the command call
     * @param commandLabel The alias used to call the command
     * @param args The arguments passed in with the command
     * @return True if the command executed successfully, otherwise false
     */
    public abstract boolean doCommand(CommandSender sender, String commandLabel, String[] args);

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        boolean isCommandSuccessful = false;
        if (testPermission(sender))
            isCommandSuccessful = doCommand(sender, commandLabel, args);
        return isCommandSuccessful;
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
