package com.coolerpromc.custombiomes.worldgen.biome;

import com.coolerpromc.custombiomes.CustomBiomes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.*;

/**
 * This class is used to register custom biomes for the mod.
 * It is for data generation to generate the biomes datapacks.
 */
public class ModBiomes {
    public static final RegistryKey<Biome> TEST_BIOME = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(CustomBiomes.MOD_ID, "test_biome"));
    public static final RegistryKey<Biome> TEST_NETHER_BIOME = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(CustomBiomes.MOD_ID, "test_nether_biome"));

    /**
     * This method is called by the data generator to register the biomes.
     */
    public static void boostrap(Registerable<Biome> context) {
        context.register(TEST_BIOME, testBiome(context));
        context.register(TEST_NETHER_BIOME, testNetherBiome(context));
    }

    /**
     * This method is used to create a test biome for the mod.
     * It is used for testing purposes and can be modified as needed.
     *
     * @param context The bootstrap context for the biome.
     * @return The created test biome.
     */
    public static Biome testBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));

        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);
        DefaultBiomeFeatures.addBatsAndMonsters(spawnBuilder);

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        //we need to follow the same order as vanilla biomes for the DefaultBiomeFeatures
        globalOverworldGeneration(biomeBuilder);
        DefaultBiomeFeatures.addMossyRocks(biomeBuilder);
        DefaultBiomeFeatures.addForestFlowers(biomeBuilder);
        DefaultBiomeFeatures.addLargeFerns(biomeBuilder);
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder);
        DefaultBiomeFeatures.addExtraGoldOre(biomeBuilder);

        biomeBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_PLAINS);

        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);

        BiomeEffects.Builder BiomeEffects = new BiomeEffects.Builder()
                .waterColor(0xe82e3b)
                .waterFogColor(0xbf1b26)
                .skyColor(0x30c918)
                .grassColor(0x7f03fc)
                .foliageColor(0xd203fc)
                .fogColor(0x22a1e6)
                .moodSound(BiomeMoodSound.CAVE);

        return buildBiome(true, 0.8f, 0.7f, spawnBuilder, biomeBuilder, BiomeEffects);
    }

    /**
     * This method is used to create a test nether biome for the mod.
     * It is used for testing purposes and can be modified as needed.
     *
     * @param context The bootstrap context for the biome.
     * @return The created test nether biome.
     */
    public static Biome testNetherBiome(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        spawnBuilder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 100, 4, 4));

        GenerationSettings.LookupBackedBuilder biomeBuilder = new GenerationSettings.LookupBackedBuilder(context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        biomeBuilder.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE).feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_NETHER).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA).feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED);
        DefaultBiomeFeatures.addNetherMineables(biomeBuilder);

        BiomeEffects.Builder BiomeEffects = new BiomeEffects.Builder()
                .waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(3344392)
                .skyColor(OverworldBiomeCreator.getSkyColor(2.0F))
                .loopSound(SoundEvents.AMBIENT_NETHER_WASTES_LOOP);

        return buildBiome(false, 0.0f, 2.0f, spawnBuilder, biomeBuilder, BiomeEffects);
    }

    public static void globalOverworldGeneration(GenerationSettings.LookupBackedBuilder builder) {
        DefaultBiomeFeatures.addLandCarvers(builder);
        DefaultBiomeFeatures.addAmethystGeodes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addSprings(builder);
        DefaultBiomeFeatures.addFrozenTopLayer(builder);
    }

    public static Biome buildBiome(boolean hasPrecipitation, float downfall, float temperature, SpawnSettings.Builder spawnBuilder, GenerationSettings.Builder biomeBuilder, BiomeEffects.Builder effectsBuilder) {
        return new Biome.Builder()
                .precipitation(hasPrecipitation)
                .downfall(downfall)
                .temperature(temperature)
                .generationSettings(biomeBuilder.build())
                .spawnSettings(spawnBuilder.build())
                .effects(effectsBuilder.build())
                .build();
    }
}
