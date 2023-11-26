package bytesnek.tq.rego;

import java.util.function.Supplier;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredRegister;

import bytesnek.tq.Tourniqueted;
import bytesnek.tq.level.world.tree.IllusiveFoliagePlacer;

/**
 * Created by SnakerBone on 25/08/2023
 **/
public class FoliagePlacers
{
    public static final DeferredRegister<FoliagePlacerType<?>> REGISTER = DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, Tourniqueted.MODID);

    public static final Supplier<FoliagePlacerType<IllusiveFoliagePlacer>> ILLUSIVE = REGISTER.register("illusive", () -> new FoliagePlacerType<>(IllusiveFoliagePlacer.CODEC));
}
