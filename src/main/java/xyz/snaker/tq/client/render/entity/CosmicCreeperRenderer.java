package xyz.snaker.tq.client.render.entity;

import xyz.snaker.snakerlib.utility.ResourcePath;
import xyz.snaker.tq.client.layer.CosmicCreeperLayer;
import xyz.snaker.tq.client.model.entity.CosmicCreeperModel;
import xyz.snaker.tq.level.entity.mob.CosmicCreeper;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import org.jetbrains.annotations.NotNull;

/**
 * Created by SnakerBone on 2/01/2023
 **/
public class CosmicCreeperRenderer extends MobRenderer<CosmicCreeper, CosmicCreeperModel>
{
    public CosmicCreeperRenderer(EntityRendererProvider.Context context)
    {
        super(context, new CosmicCreeperModel(context.bakeLayer(CosmicCreeperModel.LAYER_LOCATION)), 0.5F);
        addLayer(new CosmicCreeperLayer(this, context.getModelSet()));
        addLayer(new CosmicCreeperLayer.Eyes(this));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull CosmicCreeper creeper)
    {
        return new ResourcePath("textures/entity/mob/cosmic_creeper/cosmic_creeper.png");
    }
}