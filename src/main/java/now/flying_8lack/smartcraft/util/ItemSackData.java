package now.flying_8lack.smartcraft.util;


import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemSackData extends net.neoforged.neoforge.attachment.AttachmentHolder {


    private final ItemStack item;


    private ItemSackData(ItemStack item) {
        this.item = item;

    }

    public Item getItem() {
        return item.getItem();
    }

}
