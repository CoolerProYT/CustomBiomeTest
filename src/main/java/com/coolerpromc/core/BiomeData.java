package com.coolerpromc.core;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

/**
 * Represents biome data including the biome key, climate parameters, and a weight.
 * The weight is clamped between 1 and 10 to ensure it remains within a reasonable range.
 */
public record BiomeData(ResourceKey<Biome> biome, Climate.ParameterPoint climate, int weight) {
        @Override
        public int weight() {
            return Math.max(1, Math.min(10, weight));
        }
    }