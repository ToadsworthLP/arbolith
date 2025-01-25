package com.toastworth.arbolith.block;

import com.toastworth.arbolith.block.entity.ArbolithHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ArbolithWallHangingSignBlock extends WallHangingSignBlock {
    public ArbolithWallHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ArbolithHangingSignBlockEntity(pPos, pState);
    }
}
