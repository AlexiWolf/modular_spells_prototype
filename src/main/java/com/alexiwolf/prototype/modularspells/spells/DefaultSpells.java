package com.alexiwolf.prototype.modularspells.spells;

import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.NullAmbientEffect;
import com.alexiwolf.prototype.modularspells.spells.effects.*;
import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.effects.EffectType;
import com.alexiwolf.prototype.modularspells.core.spells.projectiles.ProjectileUpdateTask;
import com.alexiwolf.prototype.modularspells.spells.effects.ambient.FireBallAmbientEffect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;

public class DefaultSpells {
    public static Spell simpleAttack(ProjectileUpdateTask projectileSystem) {
        Spell spell = new Spell();
        spell.setName("Simple Attack");
        spell.addEffect(new ProjectileEffect(projectileSystem, 2, 0, 0,
            location -> {
                World world = location.getWorld();
                world.spawnParticle(Particle.SPELL, location, 3, 0.0, 0.0, 0.0, 0, null, true);
            })
        );
        spell.addEffect(new DamageEffect(EffectType.IMPACT, new NullAmbientEffect(), 5, 500, 1));
        return spell;
    }

    public static Spell largeFireball(ProjectileUpdateTask projectileSystem) {
        Spell spell = new Spell();
        spell.setName("Big Fireball");
        spell.addEffect(new ProjectileEffect(projectileSystem, 1.3,  0, 0, new FireBallAmbientEffect()));
        spell.addEffect(new DamageEffect(EffectType.IMPACT, new NullAmbientEffect(), 20,  4000, 2));
        spell.addEffect(new ExplosionEffect(EffectType.IMPACT, 2,  1000, 2));
        return spell;
    }

    public static Spell healSelf() {
        Spell spell = new Spell();
        spell.setName("Heal Self");
        spell.addEffect(new HealingEffect(EffectType.CASTER_AREA,
            location -> {
                World world = location.getWorld();
                world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                world.spawnParticle(Particle.VILLAGER_HAPPY, location, 20, 2, 2, 2);
                world.spawnParticle(Particle.HEART, location, 10, 1, 1, 1);
            },
            10.0,
            20_000,
            40
        ));
        return spell;
    }
}
