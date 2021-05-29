package com.alexiwolf.prototype.modularspells.core.spells.listeners;

import com.alexiwolf.prototype.modularspells.core.utils.Effects;
import com.alexiwolf.prototype.modularspells.core.utils.Exp;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellPrecastEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class SpellExpCostListener implements Listener {
    /**
     * Blocks the spell cast if the user does not have enough Exp.
     * @param event The pre-cast event.
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onSpellPrecastEvent(SpellPrecastEvent event) {
        if (!event.isCancelled()) {
            if (event.getCaster() instanceof Player) {
                Player player = (Player) event.getCaster();
                int spellCost = event.getSpell().getManaCost();
                if (Exp.getTotalExperience(player) > spellCost) {
                    Exp.setTotalExperience(player, Exp.getTotalExperience(player) - spellCost);
                } else {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "Not enough Mana");
                    Effects.fizzle(player);
                }
            }
        }
    }
}
