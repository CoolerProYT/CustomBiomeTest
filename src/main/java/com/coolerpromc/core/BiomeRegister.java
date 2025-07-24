package com.coolerpromc;

import com.coolerpromc.core.BiomeInjector;
import com.coolerpromc.worldgen.biome.ModBiomes;
import net.minecraft.world.level.biome.Climate;

public class UniversalBiomeRegistration {
    public static void init() {
        BiomeInjector.registerBiome(
                ModBiomes.TEST_BIOME,
                new Climate.ParameterPoint(
                        Climate.Parameter.span(0.1F, 0.8F),
                        Climate.Parameter.span(0.2F, 0.7F),
                        Climate.Parameter.span(-0.19F, 0.4F),
                        Climate.Parameter.span(-0.22F, 0.55F),
                        Climate.Parameter.span(0.0F, 0.0F),
                        Climate.Parameter.span(-0.56F, 0.56F),
                        0L
                ),
                7
        );
    }
}