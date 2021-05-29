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

package com.alexiwolf.prototype.modularspells.core.utils.systems;

/**
 * A facade for self-starting and self-stopping bundles of related functionality (the event-listeners, tasks,
 * config files, ext. required to make a feature tick.)
 *
 * <br>
 *
 * The main motivation for System is to provide a single place complex initialization and tear-down logic for related
 * pieces of functionality.
 *
 * Systems should have their dependencies injected in the constructor, then they can be started and stopped with a
 * single method call.
 *
 * <b>Ex.</b>
 *
 * <code>
 *  System someSystem;
 *
 *  public void onEnable() {
 *      someSystem = new SomeSystem(dep1, dep2, ext);
 *      someSystem.start();
 *  }
 *
 *  public void onDisable() {
 *      someSystem.stop();
 *  }
 * </code>
 */
public interface System {
    void enable();
    void disable();
}
