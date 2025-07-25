package com.coolerpromc.custombiomes.mixin;

import com.coolerpromc.custombiomes.util.MultiNoiseSamplerHooks;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;


@Mixin({NoiseChunk.class})
public class ChunkNoiseSamplerMixin {
    @Unique
    private long seed;

    public ChunkNoiseSamplerMixin() {
    }

    @Inject(
        method = {"<init>"},
        at = {@At("TAIL")}
    )
    private void init(int cellCountXZ, RandomState random, int firstNoiseX, int firstNoiseZ, NoiseSettings noiseSettings, DensityFunctions.BeardifierOrMarker beardifier, NoiseGeneratorSettings noiseGeneratorSettings, Aquifer.FluidPicker fluidPicker, Blender blendifier, CallbackInfo ci) {
        this.seed = ((MultiNoiseSamplerHooks)(Object)random.sampler()).getSeed();
    }

    @Inject(
        method = {"cachedClimateSampler"},
        at = {@At("RETURN")}
    )
    private void createMultiNoiseSampler(NoiseRouter noiseRouter, List<Climate.ParameterPoint> points, CallbackInfoReturnable<Climate.Sampler> cir) {
        ((MultiNoiseSamplerHooks)(Object)cir.getReturnValue()).setSeed(this.seed);
    }
}