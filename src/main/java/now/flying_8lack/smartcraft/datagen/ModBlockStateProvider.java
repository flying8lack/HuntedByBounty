package now.flying_8lack.smartcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import now.flying_8lack.smartcraft.blocks.SandPrizeBlock;
import now.flying_8lack.smartcraft.enums.SandPrizeBlockTypes;
import now.flying_8lack.smartcraft.main.ModBlocks;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        ModelFile dirt = models().cubeAll("prize_dirt", modLoc("block/prize_dirt"));
        ModelFile sand = models().cubeAll("prize_sand", modLoc("block/prize_sand"));
        ModelFile stone = models().cubeAll("prize_stone", modLoc("block/prize_stone"));



        Block block = ModBlocks.SAND_PRIZE_BLOCK.get();

        this.getVariantBuilder(block).partialState()
                .with(SandPrizeBlock.Type, SandPrizeBlockTypes.DIRT)
                .modelForState()
                .modelFile(dirt)
                .addModel().partialState().
                with(SandPrizeBlock.Type, SandPrizeBlockTypes.SAND)
                .modelForState()
                .modelFile(sand)
                .addModel().partialState().
                with(SandPrizeBlock.Type, SandPrizeBlockTypes.STONE)
                .modelForState()
                .modelFile(stone)
                .addModel();

        simpleBlockItem(ModBlocks.SAND_PRIZE_BLOCK.get(), sand);



    }

    public void add_block_item(DeferredBlock<?> block){
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }
}
