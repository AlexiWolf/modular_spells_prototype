package com.alexiwolf.prototype.modularspells.core.providers;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

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
        int spellIndex = spellIndexMap.getOrDefault(casterUuid, 0);
        spellIndexMap.put(casterUuid, spellIndex);
        return spellList.get(spellIndex);
    }

    @Override
    public void selectNextSpell(Entity caster) {
        UUID casterUuid = caster.getUniqueId();
        int spellIndex = spellIndexMap.getOrDefault(casterUuid, 0);
        spellIndex++;
        if (spellIndex > spellList.size() - 1)
            spellIndex = 0;
        spellIndexMap.put(casterUuid, spellIndex);
        sendMessage(caster);
    }

    @Override
    public void selectPreviousSpell(Entity caster) {
        UUID casterUuid = caster.getUniqueId();
        int spellIndex = spellIndexMap.getOrDefault(casterUuid, 0);
        spellIndex--;
        if (spellIndex == -1) {
            spellIndex = spellList.size() - 1;
        }
        spellIndexMap.put(casterUuid, spellIndex);
        sendMessage(caster);
    }

    @Override
    public void selectSpell(Entity caster, int spellIndex) {
        UUID casterUuid = caster.getUniqueId();
        if (spellIndex < 0) {
            spellIndex = 0;
        } else if (spellIndex > spellList.size() - 1) {
            spellIndex = spellList.size() - 1;
        }
        spellIndexMap.put(casterUuid, spellIndex);
        sendMessage(caster);
    }

    private void sendMessage(Entity entity) {
        if (entity instanceof Player) {
            Player player = (Player) entity;
            player.sendMessage(
                    ChatColor.GREEN + "Active Spell: " + ChatColor.LIGHT_PURPLE
                            + getActiveSpell(player).getName());
        }
    }

    @Override
    public List<Spell> getSpells() {
        return spellList;
    }
}
