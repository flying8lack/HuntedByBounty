package now.flying_8lack.smartcraft.util.interfaces;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;
import now.flying_8lack.smartcraft.main.ModEntity;

public interface IPlasmaDeployer {

    void setCharges();
    int getMaxCharges();
    int getCharges();
    void ConsumeCharges();

    float getInaccuracy();




    void Fire(Entity shooter, Level level);
    default boolean canFire() { return true;}
}
