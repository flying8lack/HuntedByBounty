package now.flying_8lack.smartcraft.enums;

import net.minecraft.util.StringRepresentable;

public enum SandPrizeBlockTypes implements StringRepresentable {
    SAND("sand"),
    STONE("stone"),
    DIRT("dirt");

    private final String name;

    private SandPrizeBlockTypes(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
