package com.coolerpromc.core;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

public record BiomeData(ResourceKey<Biome> biome, Climate.ParameterPoint climate, int weight) {
        @Override
        public int weight() {
            return Math.max(1, Math.min(10, weight));
        }
    }