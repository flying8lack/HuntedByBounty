package now.flying_8lack.smartcraft.main;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModSounds {
    private static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT,
            MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> PLASMA_FIRE = SOUND.register("plasma_fire",
            () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MOD_ID, "plasma_fire")));

    public static void register(IEventBus bus){
        SOUND.register(bus);
    }
}
