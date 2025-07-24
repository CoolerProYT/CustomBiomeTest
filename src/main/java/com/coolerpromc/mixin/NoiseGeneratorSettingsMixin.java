package com.coolerpromc.mixin;

import com.coolerpromc.core.SurfaceRulesModifier;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to modify the surface rules for the Overworld and Nether noise settings.
 * This allows for custom surface rules to be applied based on the noise settings.
 */
@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin {
    @Shadow @Final private NoiseSettings noiseSettings;

    @Inject(method = "surfaceRule", at = @At("HEAD"), cancellable = true)
    private void surfaceRule(CallbackInfoReturnable<SurfaceRules.RuleSource> cir)
    {
        if (this.noiseSettings.equals(NoiseSettings.OVERWORLD_NOISE_SETTINGS)){
            cir.setReturnValue(SurfaceRulesModifier.overworld(true, false, true));
        }

        if (this.noiseSettings.equals(NoiseSettings.NETHER_NOISE_SETTINGS)){
            cir.setReturnValue(SurfaceRulesModifier.nether());
        }
    }
}
