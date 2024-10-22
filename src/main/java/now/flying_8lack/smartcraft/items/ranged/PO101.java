package now.flying_8lack.smartcraft.items.ranged;


import now.flying_8lack.smartcraft.util.abstracts.AbstractPlasmaWeapon;

public class PO101 extends AbstractPlasmaWeapon {


    public PO101(Properties properties) {
        super(properties, 12);
    }

    @Override
    public float getInaccuracy() {
        return 2.0f;
    }


    @Override
    public float getBoltDamage() {
        return 5.0f;
    }

    @Override
    public void Reload() {
        this.setCharges(this.getMaxCharges());
    }
}
