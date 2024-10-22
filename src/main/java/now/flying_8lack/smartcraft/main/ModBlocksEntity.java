package now.flying_8lack.smartcraft.main;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import now.flying_8lack.smartcraft.blocksEntity.SandPrizeBlockEntity;

import java.util.function.Supplier;

import static net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE;
import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModBlocksEntity {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY =
            DeferredRegister.create(BLOCK_ENTITY_TYPE, MOD_ID);

    public static final Supplier<BlockEntityType<SandPrizeBlockEntity>> SAND_PRIZE_BLOCK_ENTITY =
            BLOCK_ENTITY.register("sand_prize_block_entity",
                    () -> BlockEntityType.Builder.of(
                            SandPrizeBlockEntity::new,
                            ModBlocks.SAND_PRIZE_BLOCK.get()
                    ).build(null)
            );

    public static void register(IEventBus bus){
        BLOCK_ENTITY.register(bus);
    }
}
