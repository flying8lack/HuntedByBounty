package now.flying_8lack.smartcraft.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.ResourceLocation;
import now.flying_8lack.smartcraft.entities.model.PlasmaBoltModel;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class PlasmaBoltRender extends EntityRenderer<PlasmaBoltEntity> {
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MOD_ID, "textures/entity/plasma_bolt");
    private final PlasmaBoltModel model;
    public PlasmaBoltRender(EntityRendererProvider.Context context) {
        super(context);

        this.model = new PlasmaBoltModel(context.bakeLayer(PlasmaBoltModel.LAYER_LOCATION));
    }


    @Override
    public ResourceLocation getTextureLocation(PlasmaBoltEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PlasmaBoltEntity p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(p_entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pushPose();
        model.renderToBuffer(poseStack,
                bufferSource.getBuffer(RenderType.solid()),
                packedLight,
                0);
        poseStack.popPose();
    }
}
