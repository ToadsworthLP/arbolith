package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ArbolithBlockStateProvider extends BlockStateProvider {
    private static final String CUTOUT_RENDER_TYPE = "cutout";

    public ArbolithBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Arbolith.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithRenderTypeAndItem(Arbolith.CHERRY_LEAVES.get(), CUTOUT_RENDER_TYPE);
        logBlockWithItem((RotatedPillarBlock) Arbolith.CHERRY_LOG.get());
    }

    // Utility methods to add a block + associated item

    public void simpleBlockWithRenderTypeAndItem(Block block, String renderType) {
        ModelFile model = (ModelFile)((BlockModelBuilder)this.cubeAll(block)).renderType(renderType);
        this.simpleBlock(block, model);
        this.simpleBlockItem(block, model);
    }

    public void logBlockWithItem(RotatedPillarBlock block) {
        ResourceLocation side = this.blockTexture(block);
        ResourceLocation end = this.extend(this.blockTexture(block), "_top");
        ModelFile vertical = (ModelFile) this.models().cubeColumn(this.name(block), side, end);
        ModelFile horizontal = (ModelFile) this.models().cubeColumnHorizontal(this.name(block) + "_horizontal", side, end);
        ((VariantBlockStateBuilder)((VariantBlockStateBuilder) this.getVariantBuilder(block).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState().modelFile(vertical).addModel()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState().modelFile(horizontal).rotationX(90).addModel()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
        this.simpleBlockItem(block, vertical);
    }

    // Private methods I had to copy over

    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        String var10002 = rl.getNamespace();
        String var10003 = rl.getPath();
        return new ResourceLocation(var10002, var10003 + suffix);
    }

    private String name(Block block) {
        return this.key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
