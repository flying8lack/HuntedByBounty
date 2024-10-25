package now.flying_8lack.smartcraft.main;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModDataAttachment {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);


    // Serialization via INBTSerializable
    public static final Supplier<AttachmentType<Integer>> CHARGES = ATTACHMENT_TYPES.register(
            "charges", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build());

    public static void register(IEventBus bus){
        ATTACHMENT_TYPES.register(bus);
    }
}
