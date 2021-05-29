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

package com.alexiwolf.prototype.modularspells.core.utils.storage;

import java.util.List;
import java.util.UUID;

/**
 * A map-like, UUID-based storage mechanism primarily used for tracking objects, and/or looking up information about them.
 * @param <T> The type of the objects being stored.
 */
public interface Registry<T> {
    /**
     * Add a UUID and an object to the registry.
     * @param uuid  The UUID of the object.
     * @param object The object associated with the UUID.
     */
    void register(UUID uuid, T object);

    /**
     * Remove a specific object from the registry if it is present.
     * @param uuid The UUID of the object.
     */
    void remove(UUID uuid);

    /**
     * Clear all objects from the registry.
     */
    void clear();

    /**
     * Check if the registry contains a UUID.
     * @param uuid The UUID to search for.
     * @return     True if the UUID is present in the registry, otherwise false.
     */
    boolean contains(UUID uuid);

    /**
     * Get the object associated with a UUID if it exists.
     * @param uuid The UUID to search.
     * @return     The object associated with the UUID, or null if the UUID is not present.
     */
    T get(UUID uuid);

    /**
     * Get a list of all UUIDs in the registry.
     * @return A list of UUIDs.
     */
    List<UUID> getUUIDs();

    /**
     * Get a list of all objects in the registry.
     * @return A list of objects of type T.
     */
    List<T> getObjects();
}
