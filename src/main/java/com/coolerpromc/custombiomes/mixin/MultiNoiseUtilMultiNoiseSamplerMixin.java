package com.coolerpromc.custombiomes.mixin;

import com.coolerpromc.custombiomes.util.MultiNoiseSamplerHooks;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin({Climate.Sampler.class})
public class MultiNoiseUtilMultiNoiseSamplerMixin implements MultiNoiseSamplerHooks {
    @Unique
    private Long seed = null;
    @Unique
    private ImprovedNoise endBiomesSampler = null;

    public MultiNoiseUtilMultiNoiseSamplerMixin() {
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return this.seed;
    }

    public ImprovedNoise getEndBiomesSampler() {
        if (this.endBiomesSampler == null) {
            this.endBiomesSampler = new ImprovedNoise(new WorldgenRandom(new LegacyRandomSource(this.seed)));
        }

        return this.endBiomesSampler;
    }
}
