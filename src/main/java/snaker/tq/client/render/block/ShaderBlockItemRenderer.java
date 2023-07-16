package snaker.tq.client.render.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import snaker.snakerlib.client.render.PreppedRenderer;
import snaker.snakerlib.math.BasicCube;

/**
 * Created by SnakerBone on 28/04/2023
 **/
public class ShaderBlockItemRenderer extends PreppedRenderer
{
    private final RenderType type;

    public ShaderBlockItemRenderer(RenderType type)
    {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.type = type;
    }

    @Override
    public void renderByItem(@NotNull ItemStack itemStack, @NotNull ItemDisplayContext context, @NotNull PoseStack stack, @NotNull MultiBufferSource source, int packedLight, int packedOverlay)
    {
        VertexConsumer consumer = source.getBuffer(type);
        if (context == ItemDisplayContext.FIXED) {
            stack.translate(0, 0, 0);
        } else {
            stack.translate(0, 0.5, 0);
        }
        BasicCube.create(consumer, stack);
    }
}
