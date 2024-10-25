package now.flying_8lack.smartcraft.enums;

import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum TierSystem implements Tier {

    HEAVYIRON(404, 3.7f, 3.236f, 10, () -> Ingredient.of(Items.IRON_INGOT), BlockTags.INCORRECT_FOR_IRON_TOOL),
    GLOWCON(249, 5.98f, 2.2f, 20, () -> Ingredient.of(Items.GLOWSTONE_DUST), BlockTags.INCORRECT_FOR_IRON_TOOL);


    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;
    private final TagKey<Block> incorrectBlocksForDrops;

    TierSystem(int uses, float speed, float damage, int enchantmentValue,
               Supplier<Ingredient> repairIngredient, TagKey<Block> incorrectBlocksForDrops) {
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
