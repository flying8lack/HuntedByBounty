package now.flying_8lack.smartcraft.items.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import static now.flying_8lack.smartcraft.main.ModDataComponentRegister.ETCHED_DATA;
import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class EtchedCopperPlate extends Item {

    public ResourceLocation m =  ResourceLocation.fromNamespaceAndPath(MOD_ID, "plasma_bolt");
    // Basic codec
    public static final Codec<EtchedCopperPlateData> BASIC_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("design").forGetter(EtchedCopperPlateData::design)
            ).apply(instance, EtchedCopperPlateData::new)
    );
    public static final StreamCodec<ByteBuf, EtchedCopperPlateData> BASIC_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, EtchedCopperPlateData::design,
            EtchedCopperPlateData::new
    );

    public EtchedCopperPlate(Properties properties) {
        super(properties.component(ETCHED_DATA.value(), new EtchedCopperPlateData(0)));
    }

    @Override
    public Component getDescription() {
        return Component.literal("Hello World!");
    }


}
