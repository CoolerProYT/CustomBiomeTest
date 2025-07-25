package com.coolerpromc.custombiomes.mixin;

import com.coolerpromc.custombiomes.core.EndBiomeInjector;
import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.TheEndBiomeSource;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

@Mixin({TheEndBiomeSource.class})
public class TheEndBiomeSourceMixin extends BiomeSourceMixin {
    @Shadow
    @Mutable
    @Final
    public static MapCodec<TheEndBiomeSource> CODEC;
    @Unique
    private Supplier<EndBiomeInjector.Overrides> overrides;
    @Unique
    private boolean biomeSetModified = false;
    @Unique
    private boolean hasCheckedForModifiedSet = false;

    @Inject(method = {"<clinit>"}, at = {@At("TAIL")})
    private static void modifyCodec(CallbackInfo ci) {
        CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(RegistryOps.retrieveGetter(Registries.BIOME)).apply(instance, instance.stable(TheEndBiomeSource::create)));
    }

    @Inject(method = {"create"}, at = {@At("HEAD")})
    private static void rememberLookup(HolderGetter<Biome> biomes, CallbackInfoReturnable<?> ci) {
        EndBiomeInjector.biomeRegistry.set(biomes);
    }

    @Inject(method = {"create"}, at = {@At("TAIL")})
    private static void clearLookup(HolderGetter<Biome> biomes, CallbackInfoReturnable<?> ci) {
        EndBiomeInjector.biomeRegistry.remove();
    }

    @Inject(method = {"<init>"}, at = {@At("RETURN")})
    private void init(Holder<Biome> centerBiome, Holder<Biome> highlandsBiome, Holder<Biome> midlandsBiome, Holder<Biome> smallIslandsBiome, Holder<Biome> barrensBiome, CallbackInfo ci) {
        HolderGetter<Biome> biomes = EndBiomeInjector.biomeRegistry.get();
        if (biomes == null) {
            throw new IllegalStateException("Biome registry not set by Mixin");
        } else {
            this.overrides = Suppliers.memoize(() -> EndBiomeInjector.createOverrides(biomes));
        }
    }

    @Inject(method = {"getNoiseBiome"}, at = {@At("RETURN")}, cancellable = true)
    private void getWeightedEndBiome(int biomeX, int biomeY, int biomeZ, Climate.Sampler noise, CallbackInfoReturnable<Holder<Biome>> cir) {
        cir.setReturnValue(this.overrides.get().pick(biomeX, biomeZ, noise, cir.getReturnValue()));
    }

    protected Set<Holder<Biome>> getBiomeSet(Set<Holder<Biome>> biomes) {
        if (!this.hasCheckedForModifiedSet) {
            this.hasCheckedForModifiedSet = true;
            this.biomeSetModified = !this.overrides.get().customBiomes.isEmpty();
        }

        if (this.biomeSetModified) {
            LinkedHashSet<Holder<Biome>> modifiedBiomes = new LinkedHashSet<>(biomes);
            modifiedBiomes.addAll(this.overrides.get().customBiomes);
            return Collections.unmodifiableSet(modifiedBiomes);
        } else {
            return biomes;
        }
    }
}
