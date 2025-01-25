package com.toastworth.arbolith.block;

import com.toastworth.arbolith.block.entity.ArbolithHangingSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ArbolithHangingSignBlock extends CeilingHangingSignBlock {
    public ArbolithHangingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ArbolithHangingSignBlockEntity(pPos, pState);
    }
}
