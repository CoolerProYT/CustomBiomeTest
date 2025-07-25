package com.coolerpromc.custombiomes.mixin;

import com.coolerpromc.custombiomes.core.NetherBiomeInjector;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

/**
 * Mixin to inject custom biomes into the Nether biome source parameter list.
 * This allows for the addition of custom biomes to the Nether generation.
 */
@Mixin(targets = "net/minecraft/world/level/biome/MultiNoiseBiomeSourceParameterList$Preset$1")
public class NetherBiomeBuilderMixin {
    @Inject(method = {"apply"}, at = {@At("RETURN")}, cancellable = true)
    public <T> void apply(Function<ResourceKey<Biome>, T> function, CallbackInfoReturnable<Climate.ParameterList<T>> cir) {
        cir.setReturnValue(NetherBiomeInjector.injectBiomes(cir.getReturnValue(), function));
    }
}
