package com.alexiwolf.prototype.modularspells.core.spells.listeners;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.effects.Effect;
import com.alexiwolf.prototype.modularspells.core.spells.effects.EffectType;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellCastEvent;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellImpactEvent;
import com.alexiwolf.prototype.modularspells.core.spells.events.SpellPrecastEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.List;

/**
 * Provides the default, high-priority spell event listeners.  Most of these are required for the spell system to
 * function, and everything here is designed to be executed last, so each EventHandler will have it's priority set to
 * HIGHEST.
 */
public class DefaultSpellEventListener implements Listener {

    /**
     * Determines if the spell should be cast based on if the spell was cancelled or not.
     * <p>
     * This handler should be executed last, as to allow other systems to do pre-processing or otherwise effect the
     * outcome of the spell.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public final void onSpellPrecastEvent(SpellPrecastEvent event) {
        if (!event.isCancelled()) {
            Spell spell = event.getSpell();
            spell.cast(event.getCaster());
        }
    }

    /**
     * Starts the first stage of the spell cast by calling the spell's `applyCasterEffects`, and
     * `applyProjectileEffects` methods. In most cases, this should be the only handler listening for
     * {@link SpellCastEvent}s. All pre-processing should be done in the pre-cast stage by listening for
     * {@link SpellPrecastEvent}s.
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpellCastEvent(SpellCastEvent event) {
        Spell spell = event.getSpell();
        Entity caster = event.getCaster();
        spell.applyCasterEffects(caster);
        spell.launchProjectileEffects(caster);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpellImpactEvent(SpellImpactEvent event) {
        Spell spell = event.getSpell();
        Entity impactedEntity = event.getImpactedEntity();
        Location impactLocation = event.getImpactLocation();
        List<Effect> effects = spell.getEffectsOfType(EffectType.IMPACT, EffectType.IMPACT_AREA);
        if (impactedEntity != null) {
            effects.forEach(effect -> effect.applyToEntity(impactedEntity, spell, event.getCaster()));
        } else {
            effects.forEach(effect -> effect.applyToLocation(impactLocation, spell, event.getCaster()));
        }
    }
}
