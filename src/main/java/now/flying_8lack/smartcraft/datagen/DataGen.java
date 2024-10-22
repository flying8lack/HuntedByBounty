package now.flying_8lack.smartcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGen
{
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packet = gen.getPackOutput();
        ExistingFileHelper filehelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();

        //gen.addProvider(event.includeServer(), new CraftingProvider(packet, lookup));
        gen.addProvider(event.includeServer(), new ModCraftingProvider(packet, lookup));
        gen.addProvider(event.includeClient(), new ModBlockStateProvider(packet, filehelper));
        gen.addProvider(event.includeClient(), new ModItemModelProvider(packet, filehelper));




    }
}