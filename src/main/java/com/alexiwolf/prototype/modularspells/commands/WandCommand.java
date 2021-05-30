package com.alexiwolf.prototype.modularspells.commands;

import com.alexiwolf.prototype.modularspells.ModularSpells;
import com.alexiwolf.prototype.modularspells.core.Items;
import com.alexiwolf.prototype.modularspells.core.utils.command.PluginCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WandCommand extends PluginCommand {
    private final ModularSpells plugin;
    private final String message =
            ChatColor.GREEN + "A shiny new" + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + " Magic Wand "
            + ChatColor.RESET + ChatColor.GREEN + "for you!";

    public WandCommand(String name, ModularSpells plugin) {
        super(name, plugin, "modular_spells-use_wand_command");
        this.plugin = plugin;
    }

    @Override
    public boolean doCommand(CommandSender sender, String commandLabel, String[] args) {
        if (args.length > 0) {
            for (String name: args) {
                Player player = Bukkit.getPlayer(name);
                if (player != null) {
                    giveWand(player);
                }
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                giveWand(player);
            } else {
                sender.sendMessage("This command can only be used by players.");
            }
        }
        return true;
    }

    private void giveWand(Player player) {
        player.sendMessage(message);
        player.getInventory().addItem(Items.wand(plugin));
    }

}
