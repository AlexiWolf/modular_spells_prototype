package com.alexiwolf.prototype.modularspells.core.providers;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import org.bukkit.entity.Entity;

/**
 * Set and access the a Spell for the provided entity.
 */
public interface SpellProvider {
    Spell getActiveSpell(Entity caster);
    void selectNextSpell(Entity caster);
    void selectPreviousSpell(Entity caster);
}