package now.flying_8lack.smartcraft.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import now.flying_8lack.smartcraft.entities.model.PlasmaBoltModel;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;
import org.jetbrains.annotations.NotNull;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class PlasmaBoltRender extends EntityRenderer<PlasmaBoltEntity> {
    public static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(MOD_ID,
                    "textures/entity/plasma_bolt.png");
    private final PlasmaBoltModel<PlasmaBoltEntity> model;
    public PlasmaBoltRender(EntityRendererProvider.Context context) {
        super(context);

        this.model = new PlasmaBoltModel<>(context.bakeLayer(PlasmaBoltModel.LAYER_LOCATION));
    }


    @Override
    public @NotNull ResourceLocation getTextureLocation(PlasmaBoltEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PlasmaBoltEntity p_entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        //super.render(p_entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(p_entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(p_entity.getXRot()));
//        Minecraft.getInstance().getItemRenderer().renderStatic(new ItemStack(Items.IRON_AXE),
//                ItemDisplayContext.NONE,
//                15728880,
//                OverlayTexture.NO_OVERLAY,
//                poseStack,
//                bufferSource,
//                p_entity.level(),
//                0);
//        Minecraft.getInstance().getEntityRenderDispatcher().render(p_entity,
//                p_entity.getX(),
//                p_entity.getY(),
//                p_entity.getZ(),
//                p_entity.getYRot(),
//                1,
//                poseStack,
//                bufferSource,
//                15728880);
 //       poseStack.translate(0, -1, 0);

        model.renderToBuffer(poseStack,
                bufferSource.getBuffer(RenderType.entityTranslucent(TEXTURE)),
                15728880,
                OverlayTexture.NO_OVERLAY);

        poseStack.popPose();
        //15728740
    }
    

}
