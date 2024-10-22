package now.flying_8lack.smartcraft.entities.projectiles;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class PlasmaBoltEntity extends AbstractArrow {

    public int life;
    public double travel = 0;
    private float on_hit_damage;
    public static final EntityDataAccessor<Boolean> AMPED = SynchedEntityData.defineId(PlasmaBoltEntity.class, EntityDataSerializers.BOOLEAN);
    public PlasmaBoltEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.pickup = Pickup.ALLOWED;

    }

    public void setOnHitDamage(float dmg){
        this.on_hit_damage = dmg;
    }



    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if(!this.level().isClientSide()){
            this.discard();
        }
    }

    @Override
    public void tick() {

        if (this.travel >= 64) { //traveled 64 or more blocks, remove bolt
            this.discard();
            return;
        }
        Vec3 speed = this.getDeltaMovement();

        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level().getBlockState(blockpos);
        if (!blockstate.isAir()) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
            if (!voxelshape.isEmpty()) {
                Vec3 vec31 = this.position();

                for (AABB aabb : voxelshape.toAabbs()) {
                    if (aabb.move(blockpos).contains(vec31)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }

        if (this.inGround) {
            if (!this.level().isClientSide) {
                this.tickDespawn();
            }

        } else {

            Vec3 position = this.position();
            Vec3 new_pos = position.add(speed);
            HitResult hitresult = this.level().clip(new ClipContext(position, new_pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            if (hitresult.getType() != HitResult.Type.MISS) {
                new_pos = hitresult.getLocation();
            }

            while (!this.isRemoved()) {
                EntityHitResult entityhitresult = this.findHitEntity(position, new_pos);
                if (entityhitresult != null) {
                    hitresult = entityhitresult;
                }

                if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
                    Entity entity = ((EntityHitResult) hitresult).getEntity();
                    Entity entity1 = this.getOwner();
                    if (entity instanceof Player && entity1 instanceof Player) {
                        if(!((Player) entity1).canHarmPlayer((Player) entity)) {
                            hitresult = null;
                            entityhitresult = null;
                        }
                    }
                }

                if (hitresult != null && hitresult.getType() != HitResult.Type.MISS) {
                    if (net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult))
                        break;
                    ProjectileDeflection projectiledeflection = this.hitTargetOrDeflectSelf(hitresult);
                    this.hasImpulse = true;
                    if (projectiledeflection != ProjectileDeflection.NONE) {
                        break;
                    }
                }

                if (entityhitresult == null || this.getPierceLevel() <= 0) {
                    break;
                }

                hitresult = null;
            }
            this.setDeltaMovement(this.getDeltaMovement().scale(1.0));

            this.travel += speed.horizontalDistance();
            this.setPos(this.getX() + speed.x,
                    this.getY() + speed.y,
                    this.getZ() + speed.z);

            //super.tick();

        }
    }

    @Override
    protected void tickDespawn() {
        this.life++;
        if (this.life >= 1200) {
            this.discard();
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.0f;
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        if(!this.level().isClientSide()){
            Entity e = result.getEntity();//Mth.clamp( 1.0f, 1, 1000);
            e.hurt(this.damageSources().onFire(), this.on_hit_damage);




        }
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.DIAMOND);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(AMPED, false);

    }
}
