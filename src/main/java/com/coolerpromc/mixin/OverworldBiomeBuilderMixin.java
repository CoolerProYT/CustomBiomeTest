package com.coolerpromc.mixin;

import com.coolerpromc.core.OverworldBiomeInjector;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

/**
 * Mixin to inject custom biomes into the OverworldBiomeBuilder.
 */
@Mixin(OverworldBiomeBuilder.class)
public class OverworldBiomeBuilderMixin {
    @Inject(method = "addBiomes", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/biome/OverworldBiomeBuilder;addOffCoastBiomes(Ljava/util/function/Consumer;)V"))
    private void injectCustomBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, CallbackInfo ci) {
        OverworldBiomeInjector.injectBiomes(consumer);
    }
}