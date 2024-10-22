package now.flying_8lack.smartcraft.util.abstracts;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;
import now.flying_8lack.smartcraft.main.ModEntity;
import now.flying_8lack.smartcraft.util.interfaces.IPlasmaDeployer;

public abstract class AbstractPlasmaWeapon extends Item implements IPlasmaDeployer {

    private final int MAX_CHARGES;
    public int charges;
    private boolean allowFire = true;


    public AbstractPlasmaWeapon(Properties properties, int MAX_CHARGES) {
        super(properties);
        this.MAX_CHARGES = MAX_CHARGES;
        this.charges = MAX_CHARGES;
    }

    @Override
    public abstract float getBoltDamage();

    public boolean shouldCheckForAmmo(){
        return true;
    }

    public abstract void Reload();

    
    public void setCharges(int amount){
        this.charges = Math.min(amount, this.getMaxCharges());
    }



    public int getMaxCharges(){
        return this.MAX_CHARGES;
    }

    public void useCharge(int amount){
        this.charges -= amount;
    }

    public int getCharges(){
        return this.charges;
    }
    
    public void consumeCharges(){
        if(getCharges() > 0){
            this.useCharge(1);
            this.setAllowFire(true);
        } else {
            this.setAllowFire(false);
        }
    }

    public void setAllowFire(boolean state){
        this.allowFire = state;
    }
    
    public boolean canFire(){
        return this.allowFire;
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        this.firePlasma(player, level);

        return super.use(level, player, usedHand);
    }


    public PlasmaBoltEntity createBolt(Level level) {
        return new PlasmaBoltEntity(ModEntity.PLASMA_BOLT.get(), level);
    }

    @Override
    public void firePlasma(Entity shooter, Level level) {
        if(!level.isClientSide()){
            this.consumeCharges();
            if (this.canFire() || !this.shouldCheckForAmmo()) {
                PlasmaBoltEntity bolt = this.createBolt(level);

                bolt.setOnHitDamage(this.getBoltDamage());

                bolt.setOwner(shooter);
                bolt.setPos(shooter.getOnPos().above(1).getCenter());
                bolt.shootFromRotation(shooter,
                        shooter.getXRot(),
                        shooter.getYRot(),
                        0.0f,
                        PLASMA_VELOCITY, this.getInaccuracy());
                level.addFreshEntity(bolt);
                this.consumeCharges();
            }
        }
    }
}
