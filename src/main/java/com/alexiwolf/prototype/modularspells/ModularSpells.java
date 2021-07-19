/*
 * Copyright (C) 2021 AlexiWolf
 *
 * This file is part of Modular Spells.
 *
 * Modular Spells is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Modular Spells is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Modular Spells.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.alexiwolf.prototype.modularspells;

import com.alexiwolf.prototype.modularspells.core.providers.CyclingSpellProvider;
import com.alexiwolf.prototype.modularspells.core.providers.SpellProvider;
import com.alexiwolf.prototype.modularspells.core.spellbook.SpellBookUseListener;
import com.alexiwolf.prototype.modularspells.core.spells.listeners.DefaultSpellEventListener;
import com.alexiwolf.prototype.modularspells.core.spells.listeners.PerSpellCoolDownListener;
import com.alexiwolf.prototype.modularspells.commands.AboutCommand;
import com.alexiwolf.prototype.modularspells.commands.WandCommand;
import com.alexiwolf.prototype.modularspells.core.Items;
import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.listeners.SpellCastListener;
import com.alexiwolf.prototype.modularspells.core.spells.listeners.SpellExpCostListener;
import com.alexiwolf.prototype.modularspells.core.spells.projectiles.ProjectileUpdateTask;
import com.alexiwolf.prototype.modularspells.core.utils.command.map.CommandMapper;
import com.alexiwolf.prototype.modularspells.core.utils.command.map.PluginCommandMapper;
import com.alexiwolf.prototype.modularspells.spells.DefaultSpells;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public final class ModularSpells extends JavaPlugin {
    private final Logger logger = getLogger();
    private final CommandMapper commandMapper = new PluginCommandMapper(this);
    private final ProjectileUpdateTask projectileSystem = new ProjectileUpdateTask();
    private final SpellProvider spellProvider = newSpellProvider();

    @Override
    public void onEnable() {
        logLicenseNotice();
        registerEventListeners();
        registerCommands();
        registerWandRecipe();
        projectileSystem.runTaskTimer(this, 0 , 1);
    }

    private void logLicenseNotice() {
        logger.info(Messages.COPYRIGHT_NOTICE);
        logger.info(Messages.STARTUP_NOTICE);
    }

    private void registerCommands() {
        List<Command> commands = new ArrayList<>();
        commands.add(new AboutCommand("mspells-about", this));
        commands.add(new WandCommand("wand", this));
        commandMapper.addAll("ModularSpells", commands);
    }

    private void registerEventListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new SpellCastListener(this, spellProvider), this);
        pluginManager.registerEvents(new DefaultSpellEventListener(), this);
        pluginManager.registerEvents(new PerSpellCoolDownListener(), this);
        pluginManager.registerEvents(new SpellBookUseListener(this, spellProvider), this);
        // pluginManager.registerEvents(new SpellExpCostListener(), this);
    }

    private List<Spell> getSpells() {
        List<Spell> spells = new ArrayList<>();
        spells.add(DefaultSpells.simpleAttack(projectileSystem, this));
        spells.add(DefaultSpells.magicBolt(projectileSystem));
        spells.add(DefaultSpells.largeFireball(projectileSystem));
        spells.add(DefaultSpells.healSelf());
        spells.add(DefaultSpells.healingProjectile(projectileSystem));
        return spells;
    }

    private void registerWandRecipe() {
        NamespacedKey key = new NamespacedKey(this, "magic_wand");
        ShapelessRecipe recipe = new ShapelessRecipe(key, Items.wand(this));
        recipe.addIngredient(Material.STICK);
        recipe.addIngredient(Material.EMERALD);
        Bukkit.addRecipe(recipe);
    }

    private SpellProvider newSpellProvider() {
        return new CyclingSpellProvider(getSpells());
    }
}
