package now.flying_8lack.smartcraft.main;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import now.flying_8lack.smartcraft.util.PlasmaDataObject;

public class ModCodec {
    public static final Codec<PlasmaDataObject> PLASMA_DATA_OBJECT_CODEC = RecordCodecBuilder.create(instance -> // Given an instance
            instance.group( // Define the fields within the instance
                    Codec.INT.fieldOf("charges").forGetter(PlasmaDataObject::charges),
                    Codec.INT.fieldOf("max").forGetter(PlasmaDataObject::max)
            ).apply(instance, PlasmaDataObject::new) // Define how to create the object
    );
}
