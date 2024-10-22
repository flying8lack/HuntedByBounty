package now.flying_8lack.smartcraft.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.NoiseChunk;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.Tags;
import now.flying_8lack.smartcraft.blocksEntity.SandPrizeBlockEntity;
import now.flying_8lack.smartcraft.enums.SandPrizeBlockTypes;
import now.flying_8lack.smartcraft.main.ModBlocksEntity;
import org.jetbrains.annotations.Nullable;

public class SandPrizeBlock extends Block implements EntityBlock {

    public static final EnumProperty<SandPrizeBlockTypes> Type = EnumProperty.create("type", SandPrizeBlockTypes.class);

    public SandPrizeBlock(Properties properties) {
        super(properties.friction(0.6f).strength(12f, 1200f).lightLevel(p -> 7));

        this.registerDefaultState(this.defaultBlockState().setValue(Type, SandPrizeBlockTypes.SAND));

    }

    public void switch_type(Level level, BlockState state, BlockPos pos, SandPrizeBlockTypes type){
        level.setBlockAndUpdate(pos, state.setValue(Type, type));
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(Type);
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);
        if (movedByPiston) {
            return;
        }
        if (!level.isClientSide()) {
            Block c = level.getBlockState(pos.below()).getBlock();

            if (c == Blocks.SAND){
                this.switch_type(level, state, pos, SandPrizeBlockTypes.SAND);
                return;
            }
            if (c == Blocks.DIRT) {
                this.switch_type(level, state, pos, SandPrizeBlockTypes.DIRT);

            }


        }
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {

        if (!level.isClientSide()){


            if( level.getBlockEntity(pos) instanceof SandPrizeBlockEntity) {
                level.explode(null, (double)pos.getX()+0.5, pos.getY(), pos.getZ()+0.5, 3.0f, Level.ExplosionInteraction.BLOCK);
                PlayerChatMessage chatMessage = PlayerChatMessage.system(
                        "Jenny was attacked by "+player.getDisplayName().getString());

                for(Player m : level.getServer().getPlayerList().getPlayers()){
                    m.createCommandSourceStack().sendChatMessage(OutgoingChatMessage.create(chatMessage),
                            false, ChatType.bind(ChatType.CHAT,  level.getServer().createCommandSourceStack()));

                }
                SandPrizeBlockEntity B = ((SandPrizeBlockEntity) level.getBlockEntity(pos));
                B.start();
            }


        }

        return InteractionResult.SUCCESS;
    }


    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SandPrizeBlockEntity(pos, state);
    }





    //@SuppressWarnings("unchecked")
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {

        return blockEntityType == ModBlocksEntity.SAND_PRIZE_BLOCK_ENTITY.get() ? (BlockEntityTicker<T>) SandPrizeBlockEntity::tick : null;
    }

}
