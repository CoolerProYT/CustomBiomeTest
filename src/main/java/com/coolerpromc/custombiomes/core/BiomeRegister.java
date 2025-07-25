package com.coolerpromc.custombiomes.core;

import com.coolerpromc.custombiomes.worldgen.biome.ModBiomes;
import net.fabricmc.fabric.impl.biome.TheEndBiomeData;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

/**
 * This class is responsible for registering biomes in the game.
 */
public class BiomeRegister {
    public static void init() {
        OverworldBiomeInjector.registerBiome(
                ModBiomes.TEST_BIOME,
                new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(0.1F, 0.8F),
                        MultiNoiseUtil.ParameterRange.of(0.2F, 0.7F),
                        MultiNoiseUtil.ParameterRange.of(-0.19F, 0.4F),
                        MultiNoiseUtil.ParameterRange.of(-0.22F, 0.55F),
                        MultiNoiseUtil.ParameterRange.of(0.0F, 0.0F),
                        MultiNoiseUtil.ParameterRange.of(-0.56F, 0.56F),
                        0L
                ),
                7
        );

        NetherBiomeInjector.registerBiome(ModBiomes.TEST_NETHER_BIOME,
                new MultiNoiseUtil.NoiseHypercube(
                        MultiNoiseUtil.ParameterRange.of(-0.8F, -0.1F),
                        MultiNoiseUtil.ParameterRange.of(-0.7F, -0.2F),
                        MultiNoiseUtil.ParameterRange.of(-0.4F, 0.19F),
                        MultiNoiseUtil.ParameterRange.of(-0.55F, 0.22F),
                        MultiNoiseUtil.ParameterRange.of(0.0F, 0.0F),
                        MultiNoiseUtil.ParameterRange.of(-0.56F, 0.56F),
                        1L
                ),
                7
        );

        TheEndBiomeData.addEndBarrensReplacement(BiomeKeys.END_HIGHLANDS, ModBiomes.TEST_NETHER_BIOME, 10);
        TheEndBiomeData.addEndMidlandsReplacement(BiomeKeys.END_HIGHLANDS, ModBiomes.TEST_BIOME, 10);
    }
}