package now.flying_8lack.smartcraft.util.abstracts;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.common.extensions.IItemExtension;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;
import now.flying_8lack.smartcraft.main.ModCapability;
import now.flying_8lack.smartcraft.main.ModEntity;
import now.flying_8lack.smartcraft.util.interfaces.IPlasmaDeployer;
import now.flying_8lack.smartcraft.util.interfaces.IPlasmaStorage;

public abstract class AbstractPlasmaWeapon extends Item implements IPlasmaDeployer, IItemExtension {


    private int coolDown = 10;




    public AbstractPlasmaWeapon(Properties properties) {
        super(properties);

    }

    @Override
    abstract public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand);
    abstract public void consume(ItemStack item);
    public abstract void Reload(ItemStack item);

    @Override
    public abstract float getBoltDamage();



    public boolean fireAllowed(){
        return this.coolDown == 0;
    }

    public void coolDownTick() {

        if (coolDown == 0) {
            return;
        }
        coolDown--;

    }


    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }



    @Override
    public PlasmaBoltEntity createBolt(Entity shooter, Vec3 pos, float RotX, float RotY, Level level) {
        PlasmaBoltEntity bolt = new PlasmaBoltEntity(ModEntity.PLASMA_BOLT.get(), level);
        bolt.setOnHitDamage(this.getBoltDamage());

        bolt.setOwner(shooter);
        bolt.setPos(pos);
        bolt.shootFromRotation(shooter,
                RotX,
                RotY,
                0.0f,
                PLASMA_VELOCITY, this.getInaccuracy());

        return bolt;
    }

}
