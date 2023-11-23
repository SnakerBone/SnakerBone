package bytesnek.tq.rego;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import bytesnek.tq.Tourniqueted;
import bytesnek.tq.level.effect.FlashBangEffect;

/**
 * Created by SnakerBone on 1/11/2023
 **/
public class Effects
{
    public static final DeferredRegister<MobEffect> REGISTER = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Tourniqueted.MODID);

    public static final RegistryObject<FlashBangEffect> FLASHBANG = REGISTER.register("flashbang", () -> new FlashBangEffect(MobEffectCategory.HARMFUL, 0));
}