package com.coolerpromc;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource GRAVEL = SurfaceRules.state(Blocks.GRAVEL.defaultBlockState());
    private static final SurfaceRules.RuleSource STONE = SurfaceRules.state(Blocks.STONE.defaultBlockState());
    private static final SurfaceRules.RuleSource manaGrassBlock = SurfaceRules.state(Blocks.NETHERRACK.defaultBlockState());
    private static final SurfaceRules.RuleSource manaSoil = SurfaceRules.state(Blocks.SOUL_SOIL.defaultBlockState());
    private static final SurfaceRules.RuleSource deepManaSoil = SurfaceRules.state(Blocks.SOUL_SAND.defaultBlockState());

    protected static SurfaceRules.RuleSource makeTestBiomeRule(){
        SurfaceRules.ConditionSource surfacerules$conditionsource1 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource2 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1);
        SurfaceRules.ConditionSource surfacerules$conditionsource3 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1);
        SurfaceRules.ConditionSource surfacerules$conditionsource6 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource7 = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource9 = SurfaceRules.waterStartCheck(-6, -1);
        SurfaceRules.ConditionSource surfacerules$conditionsource10 = SurfaceRules.hole();
        SurfaceRules.RuleSource surfacerules$rulesource2 = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE), GRAVEL);

        SurfaceRules.ConditionSource surfacerules$conditionsource15 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909, -0.5454);
        SurfaceRules.ConditionSource surfacerules$conditionsource16 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818, 0.1818);
        SurfaceRules.ConditionSource surfacerules$conditionsource17 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454, 0.909);

        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.TEST_BIOME),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                SurfaceRules.ON_FLOOR,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(surfacerules$conditionsource1, manaGrassBlock),
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource3,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(surfacerules$conditionsource15, manaSoil),
                                                        SurfaceRules.ifTrue(surfacerules$conditionsource16, manaSoil),
                                                        SurfaceRules.ifTrue(surfacerules$conditionsource17, manaSoil)
                                                )
                                        ),
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource7, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, manaSoil), deepManaSoil)
                                        ),
                                        SurfaceRules.ifTrue(SurfaceRules.not(surfacerules$conditionsource10), manaGrassBlock),
                                        SurfaceRules.ifTrue(surfacerules$conditionsource9, deepManaSoil),
                                        surfacerules$rulesource2
                                )
                        ),
                        SurfaceRules.ifTrue(
                                surfacerules$conditionsource2,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource6, SurfaceRules.ifTrue(SurfaceRules.not(surfacerules$conditionsource3), manaGrassBlock)
                                        )
                                )
                        ),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.ifTrue(surfacerules$conditionsource9, deepManaSoil))
                )
        );
    }
}
