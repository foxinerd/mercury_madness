package com.foxinerd.mercury_madness;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import org.jetbrains.annotations.NotNull;

public class MMConstants {
    public static final String MOD_ID = "mercury_madness";
    public static @NotNull Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static final ResourceKey<DamageType> HEAVY_METAL_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(MOD_ID, "heavy_metal"));
}
