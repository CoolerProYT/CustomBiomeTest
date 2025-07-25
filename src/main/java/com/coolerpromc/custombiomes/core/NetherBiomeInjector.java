package com.coolerpromc.custombiomes.core;

import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;

import java.util.*;
import java.util.function.Function;

/**
 * This class is used to inject custom biomes into the Nether biome generation.
 */
public final class NetherBiomeInjector {
    private static final List<BiomeData> CUSTOM_BIOME = new ArrayList<>();

    /**
     * Adds a custom biome to the Nether biome generation.
     */
    public static void registerBiome(RegistryKey<Biome> biome, MultiNoiseUtil.NoiseHypercube spawnNoisePoint, int weight) {
        CUSTOM_BIOME.add(new BiomeData(biome, spawnNoisePoint, weight));
    }

    /**
     * Method to inject custom biomes into the existing parameter list.
     * Called in mixin.
     */
    public static <T> MultiNoiseUtil.Entries<T> injectBiomes(MultiNoiseUtil.Entries<T> entries, Function<RegistryKey<Biome>, T> biomes) {
        if (CUSTOM_BIOME.isEmpty()) {
            return entries;
        } else {
            ArrayList<Pair<MultiNoiseUtil.NoiseHypercube, T>> entryList = new ArrayList<>(entries.getEntries());

            for(BiomeData entry : CUSTOM_BIOME) {
                int injectionCount = Math.max(1, entry.weight() / 3);
                for (int i = 0; i < injectionCount; i++) {
                    entryList.add(Pair.of(entry.climate(), biomes.apply(entry.biome())));
                }
            }
            return new MultiNoiseUtil.Entries<>(Collections.unmodifiableList(entryList));
        }
    }
}
