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

package com.alexiwolf.prototype.modularspells.commands;

import com.alexiwolf.prototype.modularspells.Messages;
import com.alexiwolf.prototype.modularspells.core.utils.command.PluginCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * A command which provides basic plugin information (description, license / warranty statement, source code links,
 * ext.) to the command sender.
 */
public class AboutCommand extends PluginCommand {
    private Logger logger;

    String[] message = new String[] {
        ChatColor.GREEN + "" + ChatColor.BOLD + "About Modular Spells" + ChatColor.RESET,
        Messages.PLUGIN_SHORT_DESCRIPTION, Messages.PLUGIN_PROTOTYPE_DESCRIPTION,
        ChatColor.GREEN + "" + ChatColor.BOLD + "License" + ChatColor.RESET,
        Messages.COPYRIGHT_NOTICE, Messages.LICENSE_NOTICE,
        ChatColor.GREEN + "" + ChatColor.BOLD + "Source Code" + ChatColor.RESET,
        Messages.SOURCE_CODE_LINK_MESSAGE
    };

    public AboutCommand(String name, JavaPlugin plugin) {
        super(name, plugin);
    }

    @Override
    public boolean doCommand(CommandSender sender, String commandLabel, String[] args) {
        messagePluginInformation(sender);
        return true;
    }

    private void messagePluginInformation(CommandSender sender) {
        sender.sendMessage(message);
    }
}
