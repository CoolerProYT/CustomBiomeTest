package com.coolerpromc.core;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;

import java.util.*;
import java.util.function.Function;

public final class NetherBiomeInjector {
    private static final Map<ResourceKey<Biome>, Climate.ParameterPoint> CUSTOM_BIOME = new HashMap<>();

    private NetherBiomeInjector() {
    }

    public static void registerBiome(ResourceKey<Biome> biome, Climate.ParameterPoint spawnNoisePoint) {
        CUSTOM_BIOME.put(biome, spawnNoisePoint);
    }

    public static <T> Climate.ParameterList<T> withModdedBiomeEntries(Climate.ParameterList<T> entries, Function<ResourceKey<Biome>, T> biomes) {
        if (CUSTOM_BIOME.isEmpty()) {
            return entries;
        } else {
            ArrayList<Pair<Climate.ParameterPoint, T>> entryList = new ArrayList<>(entries.values());

            for(Map.Entry<ResourceKey<Biome>, Climate.ParameterPoint> entry : CUSTOM_BIOME.entrySet()) {
                entryList.add(Pair.of(entry.getValue(), biomes.apply(entry.getKey())));
            }

            return new Climate.ParameterList<>(Collections.unmodifiableList(entryList));
        }
    }
}
