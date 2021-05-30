package com.alexiwolf.prototype.modularspells.core.spellbook;

import com.alexiwolf.prototype.modularspells.ModularSpells;
import com.alexiwolf.prototype.modularspells.core.providers.SpellProvider;
import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SpellBookUseListener implements Listener {

    private final ModularSpells plugin;
    private final SpellProvider spellProvider;

    public SpellBookUseListener(ModularSpells plugin, SpellProvider spellProvider) {
        this.plugin = plugin;
        this.spellProvider = spellProvider;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (isSpellBookItem(event.getItem())) {
            Player player = event.getPlayer();
            player.openInventory(getGui(player));
        }
    }

    private boolean isSpellBookItem(ItemStack item) {
        boolean isWand = false;
        try {
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();
            isWand = data.get(new NamespacedKey(plugin, "isSpellBook"), PersistentDataType.BYTE) != 0;
        } catch (NullPointerException e) {
        }
        return isWand;
    }

    private Inventory getGui(Player player) {
        Inventory gui = Bukkit.createInventory(
                player,
                18,
                ChatColor.DARK_PURPLE + "~ Spell Book" + ChatColor.RESET + " - " +
                        ChatColor.DARK_GREEN + "Select a Spell" + ChatColor.DARK_PURPLE + " ~"
        );
        for (Spell spell: spellProvider.getSpells()) {
            gui.addItem(spell.toItem());
        }
        return gui;
    }
}
