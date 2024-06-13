package com.toastworth.arbolith.wood;

import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.ToolAction;

import javax.annotation.Nullable;

public class WoodBlock extends RotatedPillarBlock {
    private Block strippedBlock;

    public WoodBlock(MaterialColor color) {
        super(Properties.of(Material.WOOD, color).strength(2.0F).sound(SoundType.WOOD));
    }

    public WoodBlock(MaterialColor color, Block strippedBlock) {
        super(Properties.of(Material.WOOD, color).strength(2.0F).sound(SoundType.WOOD));

        this.strippedBlock = strippedBlock;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        if (strippedBlock != null && context.getItemInHand().getItem() instanceof AxeItem) {
            if(state.is(this)) {
                return strippedBlock.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }

        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
