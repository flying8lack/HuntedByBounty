package now.flying_8lack.smartcraft.entities.model;


// Made with Blockbench 4.11.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import now.flying_8lack.smartcraft.entities.projectiles.PlasmaBoltEntity;
import org.jetbrains.annotations.NotNull;

import static now.flying_8lack.smartcraft.main.SmartCraft.MOD_ID;

public class PlasmaBoltModel<T extends PlasmaBoltEntity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(MOD_ID, "plasma_bolt"), "main");


    private final ModelPart body;

    public PlasmaBoltModel(ModelPart root) {
        this.body = root.getChild("body");
        ModelPart dark = this.body.getChild("dark");
        ModelPart light = this.body.getChild("light");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition dark = body.addOrReplaceChild("dark", CubeListBuilder.create().texOffs(8, 18).addBox(-1.0F, 8.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition light = body.addOrReplaceChild("light", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 8.0F, -1.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head_r1 = light.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(12, 12).addBox(-1.5F, -1.5F, -3.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 9.5F, 0.5F, 0.0F, 0.0F, 0.7418F));

        PartDefinition cube_z8_r1 = light.addOrReplaceChild("cube_z8_r1", CubeListBuilder.create().texOffs(12, 6).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-0.5F, 9.5F, 0.5F, 0.0F, 0.0F, 1.3963F));

        PartDefinition cube_z6_r1 = light.addOrReplaceChild("cube_z6_r1", CubeListBuilder.create().texOffs(12, 0).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-0.5F, 9.5F, 0.5F, 0.0F, 0.0F, 1.0472F));

        PartDefinition cube_z4_r1 = light.addOrReplaceChild("cube_z4_r1", CubeListBuilder.create().texOffs(0, 12).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-0.5F, 9.5F, 0.5F, 0.0F, 0.0F, 0.6981F));

        PartDefinition cube_z2_r1 = light.addOrReplaceChild("cube_z2_r1", CubeListBuilder.create().texOffs(0, 6).addBox(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(1.0F)), PartPose.offsetAndRotation(-0.5F, 9.5F, 0.5F, 0.0F, 0.0F, 0.3491F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    @Override
    public void setupAnim(@NotNull PlasmaBoltEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }



    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, buffer, packedLight, packedOverlay, color);
    }





}