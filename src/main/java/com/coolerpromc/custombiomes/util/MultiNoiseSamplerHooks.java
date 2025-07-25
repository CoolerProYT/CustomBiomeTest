package com.coolerpromc.custombiomes.util;

import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

public interface MultiNoiseSamplerHooks {
    ImprovedNoise getEndBiomesSampler();

    void setSeed(long var1);

    long getSeed();
}
