package com.toastworth.arbolith.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.OakTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TreeType {
    private String name;
    private String displayName;
    private RegistryObject<Block> leavesBlock;
    private RegistryObject<Block> saplingBlock;
    private RegistryObject<Block> pottedSaplingBlock;

    public TreeType(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
    }

    public void addToDeferredRegister(DeferredRegister<Block> blockDeferredRegister, DeferredRegister<Item> itemDeferredRegister) {
        leavesBlock = blockDeferredRegister.register(name + "_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.of(Material.LEAVES).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(TreeType::ocelotOrParrot).isSuffocating(TreeType::never).isViewBlocking(TreeType::never)) {
            @Override
            public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
            }

            @Override
            public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
            }

            @Override
            public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
            }
        });

        saplingBlock = blockDeferredRegister.register(name + "_sapling", () -> new SaplingBlock(new OakTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
        pottedSaplingBlock = blockDeferredRegister.register("potted_" + name + "_sapling", () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, saplingBlock, BlockBehaviour.Properties.copy(Blocks.POTTED_OAK_SAPLING)));
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public RegistryObject<Block> getLeavesBlock() {
        return leavesBlock;
    }

    public RegistryObject<Block> getSaplingBlock() {
        return saplingBlock;
    }

    public RegistryObject<Block> getPottedSaplingBlock() {
        return pottedSaplingBlock;
    }

    private static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    private static boolean ocelotOrParrot(BlockState p_50822_, BlockGetter p_50823_, BlockPos p_50824_, EntityType<?> p_50825_) {
        return p_50825_ == EntityType.OCELOT || p_50825_ == EntityType.PARROT;
    }
}
