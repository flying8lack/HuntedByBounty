package now.flying_8lack.smartcraft.main;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;

import static net.minecraft.core.registries.Registries.BLOCK_ENTITY_TYPE;
import static net.minecraft.core.registries.Registries.ENTITY_TYPE;
import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModEntity {
    public static final DeferredRegister<EntityType<?>> ENTITY =
            DeferredRegister.create(ENTITY_TYPE, MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<PlasmaBoltEntity>> PLASMA_BOLT =
            ENTITY.register("plasma_bolt", () ->
                    EntityType.Builder.of(PlasmaBoltEntity::new, MobCategory.MISC)
                            .build("plasma_bolt"));

    public static void register(IEventBus bus){
        ENTITY.register(bus);
    }


}
