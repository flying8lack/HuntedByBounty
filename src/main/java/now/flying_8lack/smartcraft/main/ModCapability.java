package now.flying_8lack.smartcraft.main;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.ItemCapability;
import now.flying_8lack.smartcraft.util.interfaces.IPlasmaStorage;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;


public class ModCapability {
    public static final ItemCapability<IPlasmaStorage, Void> PLASMA_STORAGE =
            ItemCapability.createVoid(
                    // Provide a name to uniquely identify the capability.
                    ResourceLocation.fromNamespaceAndPath(MOD_ID, "plasma_storage"),
                    // Provide the queried type. Here, we want to look up `IItemHandler` instances.
                    IPlasmaStorage.class);
}
