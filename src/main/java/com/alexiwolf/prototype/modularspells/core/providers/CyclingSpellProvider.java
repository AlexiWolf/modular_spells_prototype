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

    @Override
    public void selectNextSpell(Entity caster) {
        UUID casterUuid = caster.getUniqueId();
        if (!spellIndexMap.containsKey(casterUuid))
            spellIndexMap.put(casterUuid, 0);
        int spellIndex = spellIndexMap.getOrDefault(casterUuid, 0);
        spellIndex++;
        if (spellIndex > spellList.size() - 1)
            spellIndex = 0;
        spellIndexMap.put(casterUuid, spellIndex);
    }

    @Override
    public void selectPreviousSpell(Entity caster) {
        UUID casterUuid = caster.getUniqueId();
        if (!spellIndexMap.containsKey(casterUuid))
            spellIndexMap.put(casterUuid, 0);
        int spellIndex = spellIndexMap.getOrDefault(casterUuid, 0);
        spellIndex--;
        if (spellIndex == -1) {
            spellIndex = spellList.size() - 1;
        }
        spellIndexMap.put(casterUuid, spellIndex);
    }

    @Override
    public List<Spell> getSpells() {
        return spellList;
    }
}
