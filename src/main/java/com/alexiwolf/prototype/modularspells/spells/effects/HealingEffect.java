package com.alexiwolf.prototype.modularspells.spells.effects;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.effects.Effect;
import com.alexiwolf.prototype.modularspells.core.spells.effects.EffectType;
import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.AmbientEffect;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.Objects;

public class HealingEffect extends Effect {

    private double healAmount;

    public HealingEffect(EffectType type, AmbientEffect ambientEffect, double healAmount, long coolDown, int manaCost) {
        super(type, ambientEffect, coolDown, manaCost);
        this.healAmount = healAmount;
    }

    @Override
    public void applyToEntity(Entity target, Spell spell, Entity _caster) {
        super.applyToEntity(target, spell, _caster);
        if (target instanceof LivingEntity) {
            LivingEntity entity = (LivingEntity) target;
            double maxHealth = Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
            double healthValue = clamp(entity.getHealth() + healAmount, maxHealth);
            entity.setHealth(healthValue);
        }
    }

    private double clamp(double value, double max) {
        if (value > max) {
            return max;
        } else {
            return value;
        }
    }

    @Override
    public String getLore() {
        return ChatColor.WHITE + "Heals: " + ChatColor.GREEN + healAmount + ChatColor.RESET;
    }
}
