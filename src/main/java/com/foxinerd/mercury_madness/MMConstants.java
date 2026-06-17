package com.foxinerd.mercury_madness;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class MMConstants {
    public static final String MOD_ID = "mercury_madness";
    public static @NotNull Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
