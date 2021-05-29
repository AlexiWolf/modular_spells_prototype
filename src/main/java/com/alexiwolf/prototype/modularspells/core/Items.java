package com.alexiwolf.prototype.modularspells.core;

import com.alexiwolf.prototype.modularspells.ModularSpells;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class Items {
    public static ItemStack wand(ModularSpells plugin)  {
        ItemStack wand = new ItemStack(Material.STICK);
        ItemMeta meta = wand.getItemMeta();
        meta.addEnchant(Enchantment.CHANNELING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setDisplayName("Magic Wand");
        meta.setLore((Arrays.asList("Allows you to cast magic spells.")));
        meta.getPersistentDataContainer()
                .set(new NamespacedKey(plugin, "isWand"), PersistentDataType.BYTE, (byte) 1);
        wand.setItemMeta(meta);
        return wand;
    }
}
