package now.flying_8lack.smartcraft.main;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final Supplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example", () -> CreativeModeTab.builder()
            //Set the title of the tab. Don't forget to add a translation!
            .title(Component.translatable("itemGroup." + MOD_ID + ".example"))
            //Set the icon of the tab.
            .icon(() -> new ItemStack(ModItems.SWIFT_BLIND.get()))
            //Add your items to the tab.
            .displayItems((params, output) -> {
                output.accept(ModItems.HEAVY_IRON_SWORD.get());
                output.accept(ModBlocks.SAND_PRIZE_BLOCK.get());
                output.accept(ModItems.SWIFT_BLIND.get());
                output.accept(ModItems.PO_101.get());
            })
            .build()
    );
    public static void register(IEventBus bus){
        CREATIVE_MODE_TABS.register(bus);
    }
}
