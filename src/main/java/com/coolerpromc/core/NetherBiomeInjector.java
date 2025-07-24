package com.coolerpromc.core;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.*;
import java.util.function.Function;

public final class NetherBiomeInjector {
    private static final List<BiomeData> CUSTOM_BIOME = new ArrayList<>();

    public static void registerBiome(ResourceKey<Biome> biome, Climate.ParameterPoint spawnNoisePoint, int weight) {
        CUSTOM_BIOME.add(new BiomeData(biome, spawnNoisePoint, weight));
    }

    public static <T> Climate.ParameterList<T> injectBiomes(Climate.ParameterList<T> entries, Function<ResourceKey<Biome>, T> biomes) {
        if (CUSTOM_BIOME.isEmpty()) {
            return entries;
        } else {
            ArrayList<Pair<Climate.ParameterPoint, T>> entryList = new ArrayList<>(entries.values());

            for(BiomeData entry : CUSTOM_BIOME) {
                int injectionCount = Math.max(1, entry.weight() / 3);
                for (int i = 0; i < injectionCount; i++) {
                    entryList.add(Pair.of(entry.climate(), biomes.apply(entry.biome())));
                }
            }

            return new Climate.ParameterList<>(Collections.unmodifiableList(entryList));
        }
    }
}
