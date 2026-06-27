package com.foxinerd.mercury_madness.block;

import com.foxinerd.mercury_madness.item.MMItems;
import com.foxinerd.mercury_madness.mixin.CauldronInteractionAccessMixin;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.core.cauldron.CauldronInteractions;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

import static net.minecraft.core.cauldron.CauldronInteractions.emptyBucket;
import static net.minecraft.core.cauldron.CauldronInteractions.fillBucket;

public class MMCauldronInteractions {
    private static final ExtraCodecs.LateBoundIdMapper<String, CauldronInteraction.Dispatcher> ID_MAPPER = new ExtraCodecs.LateBoundIdMapper<>();
    public static final Codec<CauldronInteraction.Dispatcher> CODEC = ID_MAPPER.codec(Codec.STRING);
    public static final CauldronInteraction.Dispatcher MERCURY = newDispatcher("mercury");

    private static CauldronInteraction.Dispatcher newDispatcher(final String name) {
        CauldronInteraction.Dispatcher result = new CauldronInteraction.Dispatcher();
        ID_MAPPER.put(name, result);
        return result;
    }
    private static boolean isUnderWater(final Level level, final BlockPos pos) {
        FluidState fluidState = level.getFluidState(pos.above());
        return fluidState.is(FluidTags.WATER);
    }

    public static void bootStrap() {
        ((CauldronInteractionAccessMixin)MERCURY).mercury_madness$invokePut(Items.BUCKET, (state, level, pos, player, hand, itemInHand) -> fillBucket(state, level, pos, player, hand, itemInHand, new ItemStack(MMItems.MERCURY_BUCKET), (var0) -> true, SoundEvents.BUCKET_FILL_POWDER_SNOW));
        addDefaultInteractions(MERCURY);
    }

    public static void addDefaultInteractions(final CauldronInteraction.Dispatcher interactionMap) {
        ((CauldronInteractionAccessMixin)interactionMap).mercury_madness$invokePut(MMItems.MERCURY_BUCKET, MMCauldronInteractions::fillMercuryInteraction);
    }

    private static InteractionResult fillMercuryInteraction(final BlockState state, final Level level, final BlockPos pos, final Player player, final InteractionHand hand, final ItemStack itemInHand) {
        return (InteractionResult)(isUnderWater(level, pos) ? InteractionResult.CONSUME : emptyBucket(level, pos, player, hand, itemInHand, MMBlocks.MERCURY_CAULDRON.defaultBlockState(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW));
    }



}
