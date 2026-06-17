package com.foxinerd.mercury_madness.mobeffect;

import com.foxinerd.mercury_madness.MMConstants;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;

public class MMMobEffects {
    public static final Holder<MobEffect> HYDRARGYRIA =
            Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(MMConstants.MOD_ID, "hydrargyria"), new MercuryEffect());

    public static void initialize() {
        // ...
    }
}
