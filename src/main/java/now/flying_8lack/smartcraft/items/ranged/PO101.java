package now.flying_8lack.smartcraft.items.ranged;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;
import now.flying_8lack.smartcraft.main.ModEntity;
import now.flying_8lack.smartcraft.util.interfaces.IPlasmaDeployer;

public class PO101 extends Item implements IItemExtension, IPlasmaDeployer {


    public PO101(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        this.Fire(player, level);

        return super.use(level, player, usedHand);
    }

    @Override
    public void setCharges() {

    }


    @Override
    public int getMaxCharges() {
        return 0;
    }

    @Override
    public int getCharges() {
        return 0;
    }

    @Override
    public float getInaccuracy() {
        return 2.5f;
    }

    @Override
    public void ConsumeCharges() {

    }


    @Override
    public void Fire(Entity shooter, Level level) {
        if(!level.isClientSide()){
            PlasmaBoltEntity bolt = new PlasmaBoltEntity(ModEntity.PLASMA_BOLT.get(), shooter.level());

            bolt.setOwner(shooter);
            bolt.setPos(shooter.getEyePosition());
            bolt.shootFromRotation(shooter,
                    shooter.getXRot(),
                    shooter.getYRot(),
                    0.0f,
                    1.4f, this.getInaccuracy());
            level.addFreshEntity(bolt);
        }
    }
}
