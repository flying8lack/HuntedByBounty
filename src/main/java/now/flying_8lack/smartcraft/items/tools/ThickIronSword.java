package now.flying_8lack.smartcraft.items.tools;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import now.flying_8lack.smartcraft.enums.TierSystem;

import java.util.List;
import java.util.Optional;

public class ThickIronSword extends SwordItem {
    public ThickIronSword(Properties p_properties) {
        super(TierSystem.HEAVYIRON, p_properties.stacksTo(1));

    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(
                Component.literal("A heavy sword with strength dependant on the armor's mass")
                        .withStyle(ChatFormatting.GRAY));
    }

    @Override
    public Optional<TooltipComponent> getTooltipImage(ItemStack stack) {
        return super.getTooltipImage(stack);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        entity.push(player.getForward().normalize().multiply(player.getArmorValue()*0.577,
                player.getArmorValue()*0.577,
                player.getArmorValue()*0.577));
        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return super.canDisableShield(stack, shield, entity, attacker);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.hurt(attacker.damageSources().genericKill(), Mth.clamp(attacker.getArmorValue() - target.getArmorValue(),0, 5));
        return super.hurtEnemy(stack, target, attacker);
    }
}
