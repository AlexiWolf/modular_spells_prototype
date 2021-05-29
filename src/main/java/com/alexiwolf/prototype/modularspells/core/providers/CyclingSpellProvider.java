package com.alexiwolf.prototype.modularspells.core.providers;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import org.bukkit.entity.Entity;

import java.util.*;

public class CyclingSpellProvider implements SpellProvider {

    private final List<Spell> spellList;
    private final Map<UUID, Integer> spellIndexMap = new HashMap<>();

    public CyclingSpellProvider(List<Spell> spellList) {
        this.spellList = spellList;
    }

    @Override
    public Spell getActiveSpell(Entity caster) {
        UUID casterUuid = caster.getUniqueId();
        if (!spellIndexMap.containsKey(casterUuid))
            spellIndexMap.put(casterUuid, 0);
        int spellIndex = spellIndexMap.getOrDefault(casterUuid, 0);
        return spellList.get(spellIndex);
    }

    /**
     * Cycle to caster to the next spell.
     * @param caster The caster to cycle.
     * @param spell  Ignores this.
     */
    @Override
    public void setActiveSpell(Entity caster, Spell spell) {
        UUID casterUuid = caster.getUniqueId();
        if (!spellIndexMap.containsKey(casterUuid))
            spellIndexMap.put(casterUuid, 0);
        int spellIndex = spellIndexMap.getOrDefault(casterUuid, 0);
        spellIndex++;
        if (spellIndex > spellList.size() - 1)
            spellIndex = 0;
        spellIndexMap.put(casterUuid, spellIndex);
    }
}
