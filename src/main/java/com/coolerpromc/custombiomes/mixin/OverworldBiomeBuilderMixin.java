package com.coolerpromc.custombiomes.mixin;

import com.coolerpromc.custombiomes.core.OverworldBiomeInjector;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

/**
 * Mixin to inject custom biomes into the OverworldBiomeBuilder.
 */
@Mixin(VanillaBiomeParameters.class)
public class OverworldBiomeBuilderMixin {
    @Inject(method = "writeOverworldBiomeParameters", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/biome/source/util/VanillaBiomeParameters;writeOceanBiomes(Ljava/util/function/Consumer;)V"))
    protected void injectCustomBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, CallbackInfo ci) {
        OverworldBiomeInjector.injectBiomes(parameters);
    }
}