/*
 * Copyright (C) 2020 AlexiWolf
 *
 * This file is part of Modular Spells.
 *
 * Modular Spells is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Modular Spells is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Modular Spells.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.alexiwolf.prototype.modularspells.core.spells.projectiles;

import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides tracking and updating for custom {@link SpellProjectile}s.
 * <br>
 *
 * In order for {@link SpellProjectile}s to work as intended, ProjectileUpdateTask should be scheduled to run every
 * tick.  This can be done as such:
 *
 * <code>
 * projectileSystem = new ProjectileSystem();
 * projectileSystem.runTaskTimer(plugin, 0 , 1);
 * </code>
 *
 * There should ideally be only one ProjectileUpdateTask per plugin. Because ProjectileUpdateTask is immutable, it is
 * safe to have it globally accessible.
 */
public class ProjectileUpdateTask extends BukkitRunnable {
    private final List<SpellProjectile> projectiles = new ArrayList<>();

    public void shoot(SpellProjectile projectile) {
        projectiles.add(projectile);
    }

    @Override
    public void run() {
        projectiles.forEach(SpellProjectile::tick);
    }
}
