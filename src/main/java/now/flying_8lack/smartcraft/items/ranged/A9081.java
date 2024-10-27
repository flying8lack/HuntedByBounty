package now.flying_8lack.smartcraft.items.ranged;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import now.flying_8lack.smartcraft.util.abstracts.AbstractScanHitWeapon;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class A9081 extends AbstractScanHitWeapon {
    public A9081(Properties properties) {
        super(properties);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 16;
    }

    @Override
    public void shootProjectile(LivingEntity shooter, int index, float velocity, float inaccuracy, float angle) {
        Level level = shooter.level();
        Vec3 look = shooter.getForward().normalize();
        List<LivingEntity> pot;
        LivingEntity target = null;
        for(int i = 1; i < this.getDefaultProjectileRange(); i++){
            pot = level.getEntities(EntityTypeTest.forClass(LivingEntity.class),
                    AABB.ofSize(shooter.position().add(look.scale(i)), 1, 1, 1),
                    e -> !e.is(shooter));
            if(!pot.isEmpty()){
                target = pot.getFirst();
                shooter.sendSystemMessage(Component.literal("FOUND ENTITY!"));
                break;
            }
        }
        if(target != null){
            target.hurt(shooter.damageSources().genericKill(), 10.0f);
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if(!level.isClientSide()) {
            this.shootProjectile(player, 0, 2, 2, 0);
        }
        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }
}
