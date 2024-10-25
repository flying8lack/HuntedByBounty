package now.flying_8lack.smartcraft.util.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;

public interface IPlasmaDeployer {

    float PLASMA_VELOCITY = 2.5f;

    float getBoltDamage();
    float getInaccuracy();
    PlasmaBoltEntity createBolt(Entity shooter, Vec3 pos, float RotX, float RotY, Level level);


}
