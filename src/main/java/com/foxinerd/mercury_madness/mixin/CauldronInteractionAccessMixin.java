package com.foxinerd.mercury_madness.mixin;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(CauldronInteraction.Dispatcher.class)

public interface CauldronInteractionAccessMixin {
   @Invoker ("put")
    void mercury_madness$invokePut(final Item item, final CauldronInteraction interaction);
}
