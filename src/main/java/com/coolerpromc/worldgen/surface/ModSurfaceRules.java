package com.coolerpromc.worldgen.surface;

import com.coolerpromc.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource GRAVEL = SurfaceRules.state(Blocks.GRAVEL.defaultBlockState());
    private static final SurfaceRules.RuleSource STONE = SurfaceRules.state(Blocks.STONE.defaultBlockState());
    private static final SurfaceRules.RuleSource NETHERRACK = SurfaceRules.state(Blocks.NETHERRACK.defaultBlockState());
    private static final SurfaceRules.RuleSource SOUL_SOIL = SurfaceRules.state(Blocks.SOUL_SOIL.defaultBlockState());
    private static final SurfaceRules.RuleSource SOUL_SAND = SurfaceRules.state(Blocks.SOUL_SAND.defaultBlockState());
    private static final SurfaceRules.RuleSource DIRT = SurfaceRules.state(Blocks.DIRT.defaultBlockState());
    private static final SurfaceRules.RuleSource PODZOL = SurfaceRules.state(Blocks.PODZOL.defaultBlockState());

    public static SurfaceRules.RuleSource makeTestBiomeRule(){
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
                                        SurfaceRules.ifTrue(surfacerules$conditionsource1, NETHERRACK),
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource3,
                                                SurfaceRules.sequence(
                                                        SurfaceRules.ifTrue(surfacerules$conditionsource15, SOUL_SOIL),
                                                        SurfaceRules.ifTrue(surfacerules$conditionsource16, SOUL_SOIL),
                                                        SurfaceRules.ifTrue(surfacerules$conditionsource17, SOUL_SOIL)
                                                )
                                        ),
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource7, SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SOUL_SOIL), SOUL_SAND)
                                        ),
                                        SurfaceRules.ifTrue(SurfaceRules.not(surfacerules$conditionsource10), NETHERRACK),
                                        SurfaceRules.ifTrue(surfacerules$conditionsource9, SOUL_SAND),
                                        surfacerules$rulesource2
                                )
                        ),
                        SurfaceRules.ifTrue(
                                surfacerules$conditionsource2,
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                surfacerules$conditionsource6, SurfaceRules.ifTrue(SurfaceRules.not(surfacerules$conditionsource3), NETHERRACK)
                                        )
                                )
                        ),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, SurfaceRules.ifTrue(surfacerules$conditionsource9, SOUL_SAND))
                )
        );
    }

    public static SurfaceRules.RuleSource makeTestNetherBiomeRule() {
        SurfaceRules.ConditionSource surfacerules$conditionsource2 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(30), 0);
        SurfaceRules.ConditionSource surfacerules$conditionsource3 = SurfaceRules.not(SurfaceRules.yStartCheck(VerticalAnchor.absolute(35), 0));
        SurfaceRules.ConditionSource surfacerules$conditionsource8 = SurfaceRules.noiseCondition(Noises.PATCH, -0.012);
        SurfaceRules.ConditionSource surfacerules$conditionsource11 = SurfaceRules.noiseCondition(Noises.NETHER_STATE_SELECTOR, 0.0);
        SurfaceRules.RuleSource surfacerules$rulesource = SurfaceRules.ifTrue(
                surfacerules$conditionsource8, SurfaceRules.ifTrue(surfacerules$conditionsource2, SurfaceRules.ifTrue(surfacerules$conditionsource3, GRAVEL))
        );

        return SurfaceRules.ifTrue(
                SurfaceRules.isBiome(ModBiomes.TEST_NETHER_BIOME),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                                SurfaceRules.UNDER_CEILING, SurfaceRules.sequence(SurfaceRules.ifTrue(surfacerules$conditionsource11, DIRT), PODZOL)
                        ),
                        SurfaceRules.ifTrue(
                                SurfaceRules.UNDER_FLOOR,
                                SurfaceRules.sequence(surfacerules$rulesource, SurfaceRules.ifTrue(surfacerules$conditionsource11, DIRT), PODZOL)
                        )
                )
        );
    }
}
