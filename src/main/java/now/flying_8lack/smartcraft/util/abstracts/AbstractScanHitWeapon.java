package now.flying_8lack.smartcraft.util.abstracts;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public abstract class AbstractScanHitWeapon extends Item {
    public AbstractScanHitWeapon(Properties properties) {
        super(properties);
    }


    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (item) -> item.is(Items.IRON_INGOT);
    }


    abstract public int getDefaultProjectileRange();


    abstract public void shootProjectile(LivingEntity shooter,
                                         int index, float velocity, float inaccuracy, float angle);
}
