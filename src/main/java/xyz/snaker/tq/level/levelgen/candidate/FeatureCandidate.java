package xyz.snaker.tq.level.levelgen.candidate;

import xyz.snaker.snakerlib.resources.ResourceReference;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * Created by SnakerBone on 27/08/2023
 **/
public enum FeatureCandidate
{
    CATNIP(GenerationStep.Decoration.VEGETAL_DECORATION, false),
    TALL_SNAKEROOT(GenerationStep.Decoration.VEGETAL_DECORATION, false),
    SPLITLEAF(GenerationStep.Decoration.VEGETAL_DECORATION, false),
    SNAKEROOT(GenerationStep.Decoration.VEGETAL_DECORATION, false),
    PINKTAILS(GenerationStep.Decoration.VEGETAL_DECORATION, false),
    ILLUSIVE_TREE(GenerationStep.Decoration.VEGETAL_DECORATION, false),
    DELUSIVE_TREE(GenerationStep.Decoration.VEGETAL_DECORATION, false),
    SWIRL_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    FLARE_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    WATERCOLOUR_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    BURNING_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    GEOMETRIC_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    MULTICOLOUR_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    SNOWFLAKE_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    STARRY_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    FOGGY_RUBBLE(GenerationStep.Decoration.LOCAL_MODIFICATIONS, true),
    COMA_SPIKE(GenerationStep.Decoration.RAW_GENERATION, false);

    private final String name;

    private final ResourceKey<PlacedFeature> placedFeatureKey;
    private final ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey;
    private final ResourceKey<BiomeModifier> biomeModifierKey;

    private final GenerationStep.Decoration step;
    private final boolean rubble;

    FeatureCandidate(GenerationStep.Decoration step, boolean rubble)
    {
        this.name = name().toLowerCase();
        this.placedFeatureKey = createPlacedFeatureKey(name);
        this.configuredFeatureKey = createConfiguredFeatureKey(name);
        this.biomeModifierKey = createBiomeModifierKey(name);
        this.step = step;
        this.rubble = rubble;
    }

    static ResourceKey<PlacedFeature> createPlacedFeatureKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceReference(name));
    }

    static ResourceKey<ConfiguredFeature<?, ?>> createConfiguredFeatureKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceReference(name));
    }

    static ResourceKey<BiomeModifier> createBiomeModifierKey(String name)
    {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceReference(name));
    }

    public ResourceKey<PlacedFeature> getPlacedFeatureKey()
    {
        return placedFeatureKey;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeatureKey()
    {
        return configuredFeatureKey;
    }

    public ResourceKey<BiomeModifier> getBiomeModifierKey()
    {
        return biomeModifierKey;
    }

    public GenerationStep.Decoration getStep()
    {
        return step;
    }

    public boolean isRubble()
    {
        return rubble;
    }
}
