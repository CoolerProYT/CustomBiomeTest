package com.coolerpromc.custombiomes.core;

import com.coolerpromc.custombiomes.util.MultiNoiseSamplerHooks;
import com.coolerpromc.custombiomes.util.WeightedPicker;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenCustomHashMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EndBiomeInjector {
    public static final ThreadLocal<HolderGetter<Biome>> biomeRegistry = new ThreadLocal<>();
    public static final Set<ResourceKey<Biome>> ADDED_BIOMES = new HashSet<>();
    private static final Map<ResourceKey<Biome>, WeightedPicker<ResourceKey<Biome>>> END_BIOMES_MAP = new IdentityHashMap<>();
    private static final Map<ResourceKey<Biome>, WeightedPicker<ResourceKey<Biome>>> END_MIDLANDS_MAP = new IdentityHashMap<>();
    private static final Map<ResourceKey<Biome>, WeightedPicker<ResourceKey<Biome>>> END_BARRENS_MAP = new IdentityHashMap<>();

    public static void replaceHighlands(ResourceKey<Biome> biome, double weight) {
        END_BIOMES_MAP.computeIfAbsent(Biomes.END_HIGHLANDS, (key) -> new WeightedPicker<>()).add(biome, weight);
        ADDED_BIOMES.add(biome);
    }

    public static void replaceMidlands(ResourceKey<Biome> biome, double weight) {
        END_MIDLANDS_MAP.computeIfAbsent(Biomes.END_MIDLANDS, (key) -> new WeightedPicker<>()).add(biome, weight);
        ADDED_BIOMES.add(biome);
    }

    public static void replaceBarrens(ResourceKey<Biome> biome, double weight) {
        END_BARRENS_MAP.computeIfAbsent(Biomes.END_BARRENS, (key) -> new WeightedPicker<>()).add(biome, weight);
        ADDED_BIOMES.add(biome);
    }

    public static Overrides createOverrides(HolderGetter<Biome> biomes) {
        return new Overrides(biomes);
    }

    static {
        END_BIOMES_MAP.computeIfAbsent(Biomes.THE_END, (key) -> new WeightedPicker<>()).add(Biomes.THE_END, 1.0F);
        END_BIOMES_MAP.computeIfAbsent(Biomes.END_HIGHLANDS, (key) -> new WeightedPicker<>()).add(Biomes.END_HIGHLANDS, 1.0F);
        END_BIOMES_MAP.computeIfAbsent(Biomes.SMALL_END_ISLANDS, (key) -> new WeightedPicker<>()).add(Biomes.SMALL_END_ISLANDS, 1.0F);
        END_MIDLANDS_MAP.computeIfAbsent(Biomes.END_MIDLANDS, (key) -> new WeightedPicker<>()).add(Biomes.END_MIDLANDS, 1.0F);
        END_BARRENS_MAP.computeIfAbsent(Biomes.END_BARRENS, (key) -> new WeightedPicker<>()).add(Biomes.END_BARRENS, 1.0F);
    }

    public static class Overrides {
        public final Set<Holder.Reference<Biome>> customBiomes;
        private final Holder<Biome> endMidlands;
        private final Holder<Biome> endBarrens;
        private final Holder<Biome> endHighlands;
        private final @Nullable Map<Holder<Biome>, WeightedPicker<Holder<Biome>>> endBiomesMap;
        private final @Nullable Map<Holder<Biome>, WeightedPicker<Holder<Biome>>> endMidlandsMap;
        private final @Nullable Map<Holder<Biome>, WeightedPicker<Holder<Biome>>> endBarrensMap;

        public Overrides(HolderGetter<Biome> biomeRegistry) {
            Stream<ResourceKey<Biome>> var10001 = EndBiomeInjector.ADDED_BIOMES.stream();
            Objects.requireNonNull(biomeRegistry);
            this.customBiomes = var10001.map(biomeRegistry::getOrThrow).collect(Collectors.toSet());
            this.endMidlands = biomeRegistry.getOrThrow(Biomes.END_MIDLANDS);
            this.endBarrens = biomeRegistry.getOrThrow(Biomes.END_BARRENS);
            this.endHighlands = biomeRegistry.getOrThrow(Biomes.END_HIGHLANDS);
            this.endBiomesMap = this.resolveOverrides(biomeRegistry, EndBiomeInjector.END_BIOMES_MAP, null);
            this.endMidlandsMap = this.resolveOverrides(biomeRegistry, EndBiomeInjector.END_MIDLANDS_MAP, Biomes.END_MIDLANDS);
            this.endBarrensMap = this.resolveOverrides(biomeRegistry, EndBiomeInjector.END_BARRENS_MAP, Biomes.END_BARRENS);
        }

        private @Nullable Map<Holder<Biome>, WeightedPicker<Holder<Biome>>> resolveOverrides(HolderGetter<Biome> biomeRegistry, Map<ResourceKey<Biome>, WeightedPicker<ResourceKey<Biome>>> overrides, @Nullable ResourceKey<Biome> vanillaKey) {
            Map<Holder<Biome>, WeightedPicker<Holder<Biome>>> result = new Object2ObjectOpenCustomHashMap<>(overrides.size(), ResourceKeyHashStrategy.INSTANCE);

            for(Map.Entry<ResourceKey<Biome>, WeightedPicker<ResourceKey<Biome>>> entry : overrides.entrySet()) {
                WeightedPicker<ResourceKey<Biome>> picker = entry.getValue();
                int count = picker.getEntryCount();
                if (count != 0 && (vanillaKey == null || count != 1 || entry.getKey() != vanillaKey)) {
                    Holder.Reference<Biome> var10001 = biomeRegistry.getOrThrow(entry.getKey());
                    Objects.requireNonNull(biomeRegistry);
                    result.put(var10001, picker.map(biomeRegistry::getOrThrow));
                }
            }

            return result.isEmpty() ? null : result;
        }

        public Holder<Biome> pick(int x, int z, Climate.Sampler noise, Holder<Biome> vanillaBiome) {
            Holder<Biome> var10001 = this.endMidlands;
            Objects.requireNonNull(var10001);
            boolean isMidlands = vanillaBiome.is(var10001::is);
            if (!isMidlands) {
                var10001 = this.endBarrens;
                Objects.requireNonNull(var10001);
                if (!vanillaBiome.is(var10001::is)) {
                    return this.pick(vanillaBiome, vanillaBiome, this.endBiomesMap, x, z, noise);
                }
            }

            Map<Holder<Biome>, WeightedPicker<Holder<Biome>>> map = isMidlands ? this.endMidlandsMap : this.endBarrensMap;
            return this.pick(vanillaBiome, vanillaBiome, map, x, z, noise);
        }

        private <T extends Holder<Biome>> T pick(T key, T defaultValue, Map<T, WeightedPicker<T>> pickers, int x, int z, Climate.Sampler noise) {
            if (pickers == null) {
                return defaultValue;
            } else {
                WeightedPicker<T> picker = pickers.get(key);
                if (picker == null) {
                    return defaultValue;
                } else {
                    int count = picker.getEntryCount();
                    if (count != 0) {
                        return picker.pickFromNoise(((MultiNoiseSamplerHooks)(Object)noise).getEndBiomesSampler(), (double)x / 64.0, 0.0, (double)z / 64.0);
                    } else {
                        return defaultValue;
                    }
                }
            }
        }
    }

    public enum ResourceKeyHashStrategy implements Hash.Strategy<Holder<?>> {
        INSTANCE;

        public boolean equals(Holder<?> a, Holder<?> b) {
            if (a == b) {
                return true;
            } else if (a != null && b != null) {
                if (a.kind() != b.kind()) {
                    return false;
                } else {
                    Either<? extends ResourceKey<?>, ?> var10000 = a.unwrap();
                    Function<ResourceKey<?>, Boolean> var10001 = (key) -> b.unwrapKey().orElseThrow() == key;
                    Object var10002 = b.value();
                    Objects.requireNonNull(var10002);
                    return var10000.map(var10001, var10002::equals);
                }
            } else {
                return false;
            }
        }

        public int hashCode(Holder<?> a) {
            return a == null ? 0 : a.unwrap().map(System::identityHashCode, Object::hashCode);
        }
    }
}