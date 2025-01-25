package com.toastworth.arbolith.block.entity;

import com.toastworth.arbolith.Arbolith;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ArbolithHangingSignBlockEntity extends SignBlockEntity {
    public ArbolithHangingSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(Arbolith.HANGING_SIGN_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return Arbolith.HANGING_SIGN_BLOCK_ENTITY.get();
    }
}
