package com.alexiwolf.prototype.modularspells.spells;

import com.alexiwolf.prototype.modularspells.core.spells.Spell;
import com.alexiwolf.prototype.modularspells.core.spells.effects.EffectType;
import com.alexiwolf.prototype.modularspells.core.spells.effects.ambient.NullAmbientEffect;
import com.alexiwolf.prototype.modularspells.core.spells.projectiles.ProjectileUpdateTask;
import com.alexiwolf.prototype.modularspells.spells.effects.DamageEffect;
import com.alexiwolf.prototype.modularspells.spells.effects.ExplosionEffect;
import com.alexiwolf.prototype.modularspells.spells.effects.HealingEffect;
import com.alexiwolf.prototype.modularspells.spells.effects.ProjectileEffect;
import com.alexiwolf.prototype.modularspells.spells.effects.ambient.FireBallAmbientEffect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class DefaultSpells {

    public static Spell simpleAttack(ProjectileUpdateTask projectileSystem, JavaPlugin plugin) {
        Spell spell = new Spell();
        spell.setName("Quick Attack");
        spell.addEffect(new ProjectileEffect(projectileSystem, 2, 0, 0,
            location -> {
                World world = location.getWorld();
                world.spawnParticle(Particle.SPELL, location, 6, 0.0, 0.0, 0.0, 0, null, true);
            })
        );
        spell.addEffect(new DamageEffect(
                EffectType.IMPACT,
                location -> {
                    World world = location.getWorld();
                    world.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                    world.spawnParticle(Particle.CRIT, location, 18, 0, 0, 0, 0.8, null, true);
                },
                5,
                250,
                1
        ));
        return spell;
    }

    public static Spell magicBolt(ProjectileUpdateTask projectileSystem) {
        Spell spell = new Spell();
        spell.setName("Magic Bolt");
        spell.addEffect(
                new ProjectileEffect(
                        projectileSystem, 3, 0, 0, 0.0, new NullAmbientEffect(),
                        location -> {
                            World world = location.getWorld();
                            world.spawnParticle(Particle.SPELL_WITCH, location, 1, 0, 0, 0, 0, null, true);
                        }
                )
        );
        spell.addEffect(
                new DamageEffect(
                        EffectType.IMPACT,
                        location -> {
                            World world = location.getWorld();
                            world.playSound(location, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1, 1);
                            world.spawnParticle(Particle.CRIT_MAGIC, location, 20, 1, 1, 1, 0, null, true);
                        },
                        8,
                        1_000,
                        4
                )
        );
        return spell;
    }

    public static Spell largeFireball(ProjectileUpdateTask projectileSystem) {
        Spell spell = new Spell();
        spell.setName("Big Fireball");
        spell.addEffect(new ProjectileEffect(projectileSystem, 1.3,  0, 0, new FireBallAmbientEffect()));
        spell.addEffect(new DamageEffect(EffectType.IMPACT, new NullAmbientEffect(), 20,  0, 2));
        spell.addEffect(new ExplosionEffect(EffectType.IMPACT, 3,  10_000, 2));
        return spell;
    }

    public static Spell healSelf() {
        Spell spell = new Spell();
        spell.setName("Heal Self");
        spell.addEffect(new HealingEffect(EffectType.CASTER,
            location -> {
                World world = location.getWorld();
                world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                world.spawnParticle(Particle.VILLAGER_HAPPY, location, 20, 2, 2, 2, 0, null, true);
                world.spawnParticle(Particle.HEART, location, 10, 1, 1, 1, 0, null, true);
            },
            10.0,
            20_000,
            40
        ));
        return spell;
    }

    public static Spell healingProjectile(ProjectileUpdateTask projectileSystem) {
        Spell spell = new Spell();
        spell.setName("Healing Projectile");
        spell.addEffect(new ProjectileEffect(
                projectileSystem,
                1.5,
                0,
                0,
                location -> {
                    World world = location.getWorld();
                    world.spawnParticle(Particle.HEART, location, 1, 0.1, 0.1, 0.1, 0.1, null, true);
                    world.spawnParticle(Particle.VILLAGER_HAPPY, location, 1, 0.1, 0.1, 0.1, 0.1, null, true);
                }
        ));
        spell.addEffect(new HealingEffect(EffectType.IMPACT,
                location -> {
                    World world = location.getWorld();
                    world.playSound(location, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    world.spawnParticle(Particle.VILLAGER_HAPPY, location, 20, 2, 2, 2, 0, null, true);
                    world.spawnParticle(Particle.HEART, location, 10, 1, 1, 1, 0, null, true);
                },
                10.0,
                20_000,
                40
        ));
        return spell;
    }
}
