package com.foxinerd.mercury_madness.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.Nullable;

public class MercuryBulbBlock extends Block {
    public static final IntegerProperty LIGHT = IntegerProperty.create("light",0,15);
    public MercuryBulbBlock(Properties properties) {

        super(properties);
        registerDefaultState(defaultBlockState().setValue(LIGHT, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIGHT);
    }

    public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(LIGHT, context.getLevel().getBestNeighborSignal(context.getClickedPos()));
    }

    protected void neighborChanged(final BlockState state, final Level level, final BlockPos pos, final Block block, final @Nullable Orientation orientation, final boolean movedByPiston) {
        if (!level.isClientSide()) {
            int light = state.getValue(MercuryBulbBlock.LIGHT);
            if(light != level.getBestNeighborSignal(pos)){
                int newLight = level.getBestNeighborSignal(pos);
                level.setBlockAndUpdate(pos, state.setValue(LIGHT, newLight));
            }
        }
    }

    public static int getLuminance(BlockState currentBlockState){
        int light = currentBlockState.getValue(MercuryBulbBlock.LIGHT);
        return light;
    }


}
