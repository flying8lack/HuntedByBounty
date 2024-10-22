package now.flying_8lack.smartcraft.items.misc;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SwiftBlindItem extends Item {
    public SwiftBlindItem(Properties properties) {
        super(properties.stacksTo(16));




    }

    @Override
    public @NotNull InteractionResult interactLivingEntity(ItemStack stack,
                                                           Player player, LivingEntity interactionTarget, InteractionHand usedHand) {
        if(interactionTarget instanceof Wolf){
            ((Wolf) interactionTarget).setAggressive(true);
            ((Wolf) interactionTarget).setTarget(player);
        }
        return super.interactLivingEntity(stack, player, interactionTarget, usedHand);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 20*6));
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20*6, 3));
        ItemStack m = player.getItemInHand(usedHand);
        m.setCount(m.getCount() - 1); //use up the item

        return super.use(level, player, usedHand);
    }
}
