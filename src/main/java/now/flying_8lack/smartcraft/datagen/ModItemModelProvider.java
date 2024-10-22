package now.flying_8lack.smartcraft.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import now.flying_8lack.smartcraft.main.ModBlocks;
import now.flying_8lack.smartcraft.main.ModItems;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MOD_ID, existingFileHelper);
    }


    @Override
    protected void registerModels() {
        handheldItem(ModItems.HEAVY_IRON_SWORD.get());
        handheldItem(ModItems.PO_101.get());
        basicItem(ModItems.BASE_COPPER_PLATE.get());
        basicItem(ModItems.ETCHED_COPPER_PLATE.get());
        basicItem(ModItems.SWIFT_BLIND.get());
    }
}
