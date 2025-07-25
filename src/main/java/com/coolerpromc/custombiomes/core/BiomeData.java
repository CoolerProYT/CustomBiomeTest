package com.coolerpromc.custombiomes.core;

import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

/**
 * Represents biome data including the biome key, climate parameters, and a weight.
 * The weight is clamped between 1 and 10 to ensure it remains within a reasonable range.
 */
public record BiomeData(RegistryKey<Biome> biome, MultiNoiseUtil.NoiseHypercube climate, int weight) {
        @Override
        public int weight() {
            return Math.max(1, Math.min(10, weight));
        }
    }