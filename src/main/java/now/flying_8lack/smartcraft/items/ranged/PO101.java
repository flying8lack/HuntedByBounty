package now.flying_8lack.smartcraft.items.ranged;


import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.attachment.AttachmentHolder;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.items.ItemStackHandler;
import now.flying_8lack.smartcraft.main.ModCapability;
import now.flying_8lack.smartcraft.main.ModDataAttachment;
import now.flying_8lack.smartcraft.util.abstracts.AbstractPlasmaWeapon;
import now.flying_8lack.smartcraft.util.interfaces.IPlasmaStorage;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class PO101 extends AbstractPlasmaWeapon {


    public PO101(Properties properties) {
        super(properties);

    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack item = player.getMainHandItem();
        int amount = item.getData(ModDataAttachment.CHARGES);
        player.sendSystemMessage(Component.literal("Charges: " + amount));
        item.setData(ModDataAttachment.CHARGES, amount + 1);
        return InteractionResultHolder.success(player.getMainHandItem());
    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if(!player.level().isClientSide()) {
            this.Reload(stack);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if(!level.isClientSide()) {
            this.coolDownTick();
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);

    }



    @Override
    public float getInaccuracy() {
        return 2.0f;
    }




    @Override
    public float getBoltDamage() {
        return 5.0f;
    }

    @Override
    public void Reload(ItemStack item) {
        var m = item.getCapability(ModCapability.PLASMA_STORAGE, null);
        if(m != null){
            m.setCharges(12);

        }

    }

    @Override
    public void consume(ItemStack item) {

    }


}
