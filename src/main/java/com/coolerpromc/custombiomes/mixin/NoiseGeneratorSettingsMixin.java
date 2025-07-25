package com.coolerpromc.custombiomes.mixin;

import com.coolerpromc.custombiomes.core.SurfaceRulesModifier;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.GenerationShapeConfig;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
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
@Mixin(ChunkGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin {
    @Shadow @Final private GenerationShapeConfig generationShapeConfig;

    @Inject(method = "surfaceRule", at = @At("HEAD"), cancellable = true)
    private void surfaceRule(CallbackInfoReturnable<MaterialRules.MaterialRule> cir)
    {
        if (this.generationShapeConfig.equals(GenerationShapeConfig.SURFACE)){
            cir.setReturnValue(SurfaceRulesModifier.overworld(true, false, true));
        }

        if (this.generationShapeConfig.equals(GenerationShapeConfig.NETHER)){
            cir.setReturnValue(SurfaceRulesModifier.nether());
        }

        if (this.generationShapeConfig.equals(GenerationShapeConfig.END)) {
            cir.setReturnValue(SurfaceRulesModifier.end());
        }
    }
}
