package com.toastworth.arbolith.block.entity;

import com.toastworth.arbolith.Arbolith;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ArbolithSignBlockEntity extends SignBlockEntity {
    public ArbolithSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return Arbolith.SIGN_BLOCK_ENTITY.get();
    }
}
