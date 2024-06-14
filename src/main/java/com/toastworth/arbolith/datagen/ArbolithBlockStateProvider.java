package com.toastworth.arbolith.datagen;

import com.toastworth.arbolith.Arbolith;
import com.toastworth.arbolith.wood.WoodSet;
import com.toastworth.arbolith.wood.WoodSets;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.RenderTypeHelper;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class ArbolithBlockStateProvider extends BlockStateProvider {
    private static final String CUTOUT_RENDER_TYPE = "cutout";

    public ArbolithBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Arbolith.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        WoodSets.WOOD_SETS.forEach(this::addWoodSetBlocks);
    }

    private void addWoodSetBlocks(WoodSet woodSet) {
        rotatedPillarBlockWithItem((RotatedPillarBlock) woodSet.getLogBlock().get());
        rotatedPillarBlockSameTextureWithItem((RotatedPillarBlock) woodSet.getWoodBlock().get(), blockTexture(woodSet.getLogBlock().get()));
        rotatedPillarBlockWithItem((RotatedPillarBlock) woodSet.getStrippedLogBlock().get());
        rotatedPillarBlockSameTextureWithItem((RotatedPillarBlock) woodSet.getStrippedWoodBlock().get(), blockTexture(woodSet.getStrippedLogBlock().get()));
        simpleBlockWithItem(woodSet.getPlanksBlock().get());
        slabBlockWithItem((SlabBlock) woodSet.getSlabBlock().get(), blockTexture(woodSet.getPlanksBlock().get()), blockTexture(woodSet.getPlanksBlock().get()));
        stairsBlockWithItem((StairBlock) woodSet.getStairsBlock().get(), blockTexture(woodSet.getPlanksBlock().get()));
        fenceBlockWithItem((FenceBlock) woodSet.getFenceBlock().get(), blockTexture(woodSet.getPlanksBlock().get()));
        fenceGateBlockWithItem((FenceGateBlock) woodSet.getFenceGateBlock().get(), blockTexture(woodSet.getPlanksBlock().get()));
        buttonBlockWithItem((ButtonBlock) woodSet.getButtonBlock().get(), blockTexture(woodSet.getPlanksBlock().get()));
        pressurePlateBlockWithItem((PressurePlateBlock) woodSet.getPressurePlateBlock().get(), blockTexture(woodSet.getPlanksBlock().get()));
        doorBlockWithItem((DoorBlock) woodSet.getDoorBlock().get(), new ResourceLocation(Arbolith.MOD_ID, "block/" + woodSet.getName() + "_door_bottom"), new ResourceLocation(Arbolith.MOD_ID, "block/" + woodSet.getName() + "_door_top"), woodSet.getDoorRenderType());
        trapdoorBlockWithItem((TrapDoorBlock) woodSet.getTrapdoorBlock().get(), new ResourceLocation(Arbolith.MOD_ID, "block/" + woodSet.getName() + "_trapdoor"), true, woodSet.getDoorRenderType());
        signBlock((StandingSignBlock)woodSet.getSignBlock().get(), (WallSignBlock)woodSet.getWallSignBlock().get(), blockTexture(woodSet.getPlanksBlock().get()));
    }

    // Utility methods to add a block + associated item

    public void simpleBlockWithItem(Block block) {
        ModelFile model = this.cubeAll(block);
        this.simpleBlock(block, model);
        this.simpleBlockItem(block, model);
    }

    public void simpleBlockWithRenderTypeAndItem(Block block, String renderType) {
        ModelFile model = (ModelFile)((BlockModelBuilder)this.cubeAll(block)).renderType(renderType);
        this.simpleBlock(block, model);
        this.simpleBlockItem(block, model);
    }

    public void rotatedPillarBlockWithItem(RotatedPillarBlock block) {
        ResourceLocation side = this.blockTexture(block);
        ResourceLocation end = this.extend(this.blockTexture(block), "_top");
        ModelFile vertical = (ModelFile) this.models().cubeColumn(this.name(block), side, end);
        ModelFile horizontal = (ModelFile) this.models().cubeColumnHorizontal(this.name(block) + "_horizontal", side, end);
        ((VariantBlockStateBuilder)((VariantBlockStateBuilder) this.getVariantBuilder(block).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState().modelFile(vertical).addModel()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState().modelFile(horizontal).rotationX(90).addModel()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
        this.simpleBlockItem(block, vertical);
    }

    public void rotatedPillarBlockSameTextureWithItem(RotatedPillarBlock block, ResourceLocation texture) {
        ModelFile vertical = (ModelFile) this.models().cubeColumn(this.name(block), texture, texture);
        ModelFile horizontal = (ModelFile) this.models().cubeColumnHorizontal(this.name(block) + "_horizontal", texture, texture);
        ((VariantBlockStateBuilder)((VariantBlockStateBuilder) this.getVariantBuilder(block).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState().modelFile(vertical).addModel()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState().modelFile(horizontal).rotationX(90).addModel()).partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState().modelFile(horizontal).rotationX(90).rotationY(90).addModel();
        this.simpleBlockItem(block, vertical);
    }

    public void slabBlockWithItem(SlabBlock block, ResourceLocation texture, ResourceLocation doubleSlabTexture) {
        ModelFile bottom = this.models().slab(this.name(block), texture, texture, texture);
        ModelFile top = this.models().slabTop(this.name(block) + "_top", texture, texture, texture);
        ModelFile doubleSlab = this.models().getExistingFile(doubleSlabTexture);
        this.getVariantBuilder(block).partialState().with(SlabBlock.TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel[]{new ConfiguredModel(bottom)}).partialState().with(SlabBlock.TYPE, SlabType.TOP).addModels(new ConfiguredModel[]{new ConfiguredModel(top)}).partialState().with(SlabBlock.TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel[]{new ConfiguredModel(doubleSlab)});
        this.simpleBlockItem(block, bottom);
    }

    public void stairsBlockWithItem(StairBlock block, ResourceLocation texture) {
        String baseName = this.key(block).toString();
        ModelFile stairs = this.models().stairs(baseName, texture, texture, texture);
        ModelFile stairsInner = this.models().stairsInner(baseName + "_inner", texture, texture, texture);
        ModelFile stairsOuter = this.models().stairsOuter(baseName + "_outer", texture, texture, texture);
        this.stairsBlock(block, (ModelFile)stairs, (ModelFile)stairsInner, (ModelFile)stairsOuter);
        this.simpleBlockItem(block, stairs);
    }

    public void fenceBlockWithItem(FenceBlock block, ResourceLocation texture) {
        String baseName = this.key(block).toString();
        ModelFile post = this.models().fencePost(baseName + "_post", texture);
        ModelFile side = this.models().fenceSide(baseName + "_side", texture);
        MultiPartBlockStateBuilder builder = ((MultiPartBlockStateBuilder.PartBuilder) this.getMultipartBuilder(block).part().modelFile(post).addModel()).end();
        PipeBlock.PROPERTY_BY_DIRECTION.entrySet().forEach((e) -> {
            Direction dir = (Direction)e.getKey();
            if (dir.getAxis().isHorizontal()) {
                ((MultiPartBlockStateBuilder.PartBuilder) builder.part().modelFile(side).rotationY(((int)dir.toYRot() + 180) % 360).uvLock(true).addModel()).condition((Property)e.getValue(), new Boolean[]{true});
            }
        });
        this.itemModels().fenceInventory(this.key(block).toString(), texture);
    }

    public void fenceGateBlockWithItem(FenceGateBlock block, ResourceLocation texture) {
        String baseName = this.key(block).toString();
        ModelFile gate = this.models().fenceGate(baseName, texture);
        ModelFile gateOpen = this.models().fenceGateOpen(baseName + "_open", texture);
        ModelFile gateWall = this.models().fenceGateWall(baseName + "_wall", texture);
        ModelFile gateWallOpen = this.models().fenceGateWallOpen(baseName + "_wall_open", texture);
        this.fenceGateBlock(block, gate, gateOpen, gateWall, gateWallOpen);
        this.simpleBlockItem(block, gate);
    }

    public void buttonBlockWithItem(ButtonBlock block, ResourceLocation texture) {
        ModelFile button = this.models().button(this.name(block), texture);
        ModelFile buttonPressed = this.models().buttonPressed(this.name(block) + "_pressed", texture);
        this.buttonBlock(block, button, buttonPressed);
        this.simpleBlockItem(block, button);
        this.itemModels().buttonInventory(this.key(block).toString(), texture);
    }

    public void pressurePlateBlockWithItem(PressurePlateBlock block, ResourceLocation texture) {
        ModelFile pressurePlate = this.models().pressurePlate(this.name(block), texture);
        ModelFile pressurePlateDown = this.models().pressurePlateDown(this.name(block) + "_down", texture);
        this.pressurePlateBlock(block, pressurePlate, pressurePlateDown);
        this.simpleBlockItem(block, pressurePlate);
    }

    public void doorBlockWithItem(DoorBlock block, ResourceLocation bottom, ResourceLocation top, String renderType) {
        String baseName = this.key(block).toString();
        ModelFile bottomLeft = this.models().doorBottomLeft(baseName + "_bottom_left", bottom, top).renderType(renderType);
        ModelFile bottomLeftOpen = this.models().doorBottomLeftOpen(baseName + "_bottom_left_open", bottom, top).renderType(renderType);
        ModelFile bottomRight = this.models().doorBottomRight(baseName + "_bottom_right", bottom, top).renderType(renderType);
        ModelFile bottomRightOpen = this.models().doorBottomRightOpen(baseName + "_bottom_right_open", bottom, top).renderType(renderType);
        ModelFile topLeft = this.models().doorTopLeft(baseName + "_top_left", bottom, top).renderType(renderType);
        ModelFile topLeftOpen = this.models().doorTopLeftOpen(baseName + "_top_left_open", bottom, top).renderType(renderType);
        ModelFile topRight = this.models().doorTopRight(baseName + "_top_right", bottom, top).renderType(renderType);
        ModelFile topRightOpen = this.models().doorTopRightOpen(baseName + "_top_right_open", bottom, top).renderType(renderType);
        this.doorBlock(block, bottomLeft, bottomLeftOpen, bottomRight, bottomRightOpen, topLeft, topLeftOpen, topRight, topRightOpen);
        this.itemModels().basicItem(block.asItem());
    }

    public void trapdoorBlockWithItem(TrapDoorBlock block, ResourceLocation texture, boolean orientable, String renderType) {
        String baseName = this.key(block).toString();
        ModelFile bottom = orientable ? this.models().trapdoorOrientableBottom(baseName + "_bottom", texture).renderType(renderType) : this.models().trapdoorBottom(baseName + "_bottom", texture).renderType(renderType);
        ModelFile top = orientable ? this.models().trapdoorOrientableTop(baseName + "_top", texture).renderType(renderType) : this.models().trapdoorTop(baseName + "_top", texture).renderType(renderType);
        ModelFile open = orientable ? this.models().trapdoorOrientableOpen(baseName + "_open", texture).renderType(renderType) : this.models().trapdoorOpen(baseName + "_open", texture).renderType(renderType);
        this.trapdoorBlock(block, bottom, top, open, orientable);
        this.simpleBlockItem(block, bottom);
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
