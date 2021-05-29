package com.alexiwolf.prototype.modularspells.core.spells.listeners;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellPrecastEvent;
import com.alexiwolf.prototype.modularspells.core.utils.Effects;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PerSpellCoolDownListener implements Listener {
    private final Map<SpellCast, Long> coolDownMap = new HashMap<>();

    @EventHandler(priority = EventPriority.NORMAL)
    public void onSpellPrecastEvent(SpellPrecastEvent event) {
        if (!event.isCancelled()) {
            UUID uuid = event.getCaster().getUniqueId();
            Spell spell = event.getSpell();
            SpellCast spellCast = new SpellCast(uuid, spell);
            long currentTime = System.currentTimeMillis();
            long coolDownEnd = coolDownMap.getOrDefault(spellCast, -1L);
            if (coolDownEnd <= currentTime) {
                coolDownMap.put(spellCast, currentTime + event.getSpell().getCoolDown());
            } else {
                event.setCancelled(true);
                Entity entity = event.getCaster();
                Effects.fizzle(entity);
                entity.sendMessage(
                        ChatColor.RED + "Spell is cooling down.  Wait " + ((double) (coolDownEnd - currentTime) / 1000) + " more seconds."
                );
            }
        }
    }

    private static class SpellCast {
        final UUID casterId;
        final Spell spell;

        SpellCast(UUID casterId, Spell spell) {
            this.casterId = casterId;
            this.spell = spell;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SpellCast spellCast = (SpellCast) o;
            return Objects.equals(casterId, spellCast.casterId) && Objects.equals(spell, spellCast.spell);
        }

        @Override
        public int hashCode() {
            return Objects.hash(casterId, spell);
        }
    }
}
