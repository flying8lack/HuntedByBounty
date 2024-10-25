package now.flying_8lack.smartcraft.util.interfaces;

import net.neoforged.neoforge.attachment.IAttachmentHolder;

public interface IPlasmaStorage {

    void setCharges(int amount);
    int getCharges();
    void useCharges(int amount);

    int getMaxCharges();
    void setMaxCharges(int amount);

}
