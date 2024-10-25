package now.flying_8lack.smartcraft.Cap;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.capabilities.CapabilityRegistry;
import net.neoforged.neoforge.capabilities.ICapabilityProvider;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.items.ItemStackHandler;
import now.flying_8lack.smartcraft.main.ModCapability;
import now.flying_8lack.smartcraft.main.ModDataAttachment;
import now.flying_8lack.smartcraft.util.ItemSackData;
import now.flying_8lack.smartcraft.util.abstracts.AbstractPlasmaWeapon;
import now.flying_8lack.smartcraft.util.interfaces.IPlasmaStorage;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

public class PlasmaStorageProvider implements ICapabilityProvider<ItemStack, Void, IPlasmaStorage>, INBTSerializable<CompoundTag>, IPlasmaStorage {

    private int MAX = 30;
    private int Charges = 0;
    private ItemStack item;


    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag e = new CompoundTag();
        e.putInt("charges", this.Charges);
        e.putInt("max", this.MAX);
        return e;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.Charges = nbt.getInt("charges");
        this.MAX = nbt.getInt("max");
    }


    @Override
    public @Nullable IPlasmaStorage getCapability(ItemStack object, Void context) {
        return object.getCapability(ModCapability.PLASMA_STORAGE);

    }



    @Override
    public void setCharges(int amount) {
        this.Charges += amount;

    }

    @Override
    public int getCharges() {
        return this.Charges;
    }

    @Override
    public void useCharges(int amount) {
        this.Charges -= amount;

    }


    @Override
    public int getMaxCharges() {
        return this.MAX;
    }

    @Override
    public void setMaxCharges(int amount) {
        this.MAX = amount;
    }


}
