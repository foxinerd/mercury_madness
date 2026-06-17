package com.foxinerd.mercury_madness.mobeffect;

import com.foxinerd.mercury_madness.MMConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class MercuryEffect extends MobEffect {
    protected MercuryEffect(){
        super(MobEffectCategory.HARMFUL, 0x9fa4ca);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public boolean applyEffectTick(ServerLevel world, LivingEntity entity, int amplifier) {
        //apply damage
        DamageSource heavyMetal = new DamageSource(
                world.registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .get(MMConstants.HEAVY_METAL_DAMAGE.identifier()).orElseThrow());
        entity.hurtServer(world, heavyMetal, 1.0f);
        return super.applyEffectTick(world, entity, amplifier);
    }
}
