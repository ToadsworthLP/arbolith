package com.toastworth.arbolith.block;

import com.toastworth.arbolith.block.entity.ArbolithSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ArbolithStandingSignBlock extends StandingSignBlock {
    public ArbolithStandingSignBlock(Properties pProperties, WoodType pType) {
        super(pProperties, pType);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ArbolithSignBlockEntity(pPos, pState);
    }
}
