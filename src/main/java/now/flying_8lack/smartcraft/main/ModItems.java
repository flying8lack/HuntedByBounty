package now.flying_8lack.smartcraft.main;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import now.flying_8lack.smartcraft.enums.TierSystem;
import now.flying_8lack.smartcraft.items.misc.EtchedCopperPlate;
import now.flying_8lack.smartcraft.items.misc.SwiftBlindItem;
import now.flying_8lack.smartcraft.items.ranged.PO101;
import now.flying_8lack.smartcraft.items.tools.ThickIronSword;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    public static final DeferredItem<Item> HEAVY_IRON_SWORD = ITEMS.register("heavy_iron_sword",
            () -> new ThickIronSword( new Item.Properties()
                    .attributes(SwordItem.createAttributes(TierSystem.HEAVYIRON, 3, -2.99F))));

    public static final DeferredItem<Item> SWIFT_BLIND = ITEMS.register("swift_blind",
            () -> new SwiftBlindItem( new Item.Properties()));

    public static final DeferredItem<Item> BASE_COPPER_PLATE = ITEMS.register("base_copper_plate",
            () -> new Item(new Item.Properties().stacksTo(64)));

    public static final DeferredItem<Item> ETCHED_COPPER_PLATE = ITEMS.register("etched_copper_plate",
            () -> new EtchedCopperPlate(new Item.Properties().stacksTo(64)));

    public static final DeferredItem<Item> PO_101 = ITEMS.register("po_101",
            () -> new PO101(new Item.Properties().stacksTo(1)));


    public static void register(IEventBus bus){
        ITEMS.register(bus);
    }
}
