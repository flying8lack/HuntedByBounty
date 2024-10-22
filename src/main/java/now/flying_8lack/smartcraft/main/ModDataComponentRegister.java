package now.flying_8lack.smartcraft.main;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import now.flying_8lack.smartcraft.items.misc.EtchedCopperPlateData;

import static now.flying_8lack.smartcraft.items.misc.EtchedCopperPlate.BASIC_CODEC;
import static now.flying_8lack.smartcraft.items.misc.EtchedCopperPlate.BASIC_STREAM_CODEC;
import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModDataComponentRegister {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<EtchedCopperPlateData>> ETCHED_DATA = REGISTRAR.registerComponentType(
            "basic",
            builder -> builder
                    // The codec to read/write the data to disk
                    .persistent(BASIC_CODEC)
                    // The codec to read/write the data across the network
                    .networkSynchronized(BASIC_STREAM_CODEC)
    );

    public static void register(IEventBus bus){
        REGISTRAR.register(bus);
    }
}
