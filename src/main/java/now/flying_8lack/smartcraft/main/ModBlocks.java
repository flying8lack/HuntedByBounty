package now.flying_8lack.smartcraft.main;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import now.flying_8lack.smartcraft.blocks.SandPrizeBlock;

import java.util.function.Supplier;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);


    public static final DeferredBlock<Block> SAND_PRIZE_BLOCK = register_block("sand_prize_block",
            () -> new SandPrizeBlock(BlockBehaviour.Properties.of()));



    private static <T extends Block> DeferredBlock<T> register_block(String name, Supplier<T> block){
        DeferredBlock<T> r = BLOCKS.register(name, block);
        reg_block_item(name, r);
        return r;
    }

    private static <T extends Block> void reg_block_item(String name, DeferredBlock<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus bus){
        BLOCKS.register(bus);
    }
}
