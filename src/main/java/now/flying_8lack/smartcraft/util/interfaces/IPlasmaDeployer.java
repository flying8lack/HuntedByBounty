package now.flying_8lack.smartcraft.util.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public interface IPlasmaDeployer {

    float PLASMA_VELOCITY = 1.4f;

    float getBoltDamage();
    float getInaccuracy();
    void firePlasma(Entity shooter, Level level);

}
