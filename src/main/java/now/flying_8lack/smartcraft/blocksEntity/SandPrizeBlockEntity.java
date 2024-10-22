package now.flying_8lack.smartcraft.blocksEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.IItemHandler;
import now.flying_8lack.smartcraft.main.Config;

import java.util.ArrayList;
import java.util.List;

import static now.flying_8lack.smartcraft.main.ModBlocksEntity.SAND_PRIZE_BLOCK_ENTITY;


public class SandPrizeBlockEntity extends BlockEntity {
    public List<Zombie> agent = new ArrayList<>();
    public int number_of_mon_left = 4;
    public int state = 0;
    public SandPrizeBlockEntity(BlockPos pos, BlockState blockState) {
        super(SAND_PRIZE_BLOCK_ENTITY.get(), pos, blockState);
    }

    public void start(){
        //SandPrizeBlockEntity b = ((SandPrizeBlockEntity) entity);
        this.number_of_mon_left = 4;
        this.state = 1;

    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        this.number_of_mon_left = tag.getInt("number_of_mon_left");
        this.state = tag.getInt("state");


    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("number_of_mon_left", this.number_of_mon_left );
        tag.putInt("state", this.state);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T blockEntity) {
        if (level.isClientSide()){
            return;
            }

        SandPrizeBlockEntity b = (SandPrizeBlockEntity) blockEntity;
        switch (b.state){
            case 1:
                for(int num_z = 0; num_z < Config.sand_prize_mob_number; num_z++){
                    Zombie m = new Zombie(level);
                    m.setCustomName(Component.literal("Jenny"));
                    m.setPos(pos.getX() + 0.5, pos.getY() + 1.4, pos.getZ() + 0.5);
                    m.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, MobEffectInstance.INFINITE_DURATION, 2));
                    m.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, MobEffectInstance.INFINITE_DURATION, 1));
                    m.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.IRON_AXE));
                    b.agent.add(m);
                    level.addFreshEntity(m);
                }


                b.state = 2;
                break;
            case 2: //checking state
                if (b.agent.isEmpty()){
                    b.state = 1;
                    break;
                }
                if (b.agent.stream().noneMatch(z -> z.isAlive())) {
                    b.state = 3;
                }
                break;
            case 3: //reward state
                ItemStack reward = new ItemStack(Items.DIAMOND);
                if (level.getBlockState(pos.east()).is(Blocks.CHEST)){
                    IItemHandler chest = level.getCapability(Capabilities.ItemHandler.BLOCK, pos.east(), Direction.UP);
                    if (chest != null) {
                        for (int i = 0; i < 28; i++) {
                            if (chest.isItemValid(i, reward)) {
                                chest.insertItem(i, reward, false);
                                break; //break from loop
                            }
                        }
                    }
                } else { //if no chest was found
                    level.removeBlock(pos.east(), false);
                    level.setBlock(pos.east(), Blocks.CHEST.defaultBlockState(), 3);
                    IItemHandler chest = level.getCapability(Capabilities.ItemHandler.BLOCK, pos.east(), Direction.UP);
                    if (chest != null){
                        chest.insertItem(1, reward, false);
                    }
                }

                b.state = 0;
                break;

            default:
                b.state = 0;
                break;


        }
    }



}
