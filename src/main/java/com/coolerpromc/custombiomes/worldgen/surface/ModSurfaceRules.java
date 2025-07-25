package com.coolerpromc.custombiomes.worldgen.surface;

import com.coolerpromc.custombiomes.worldgen.biome.ModBiomes;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

/**
 * This class defines custom surface rules for biomes in the mod.
 */
public class ModSurfaceRules {
    private static final MaterialRules.MaterialRule GRAVEL = MaterialRules.block(Blocks.GRAVEL.getDefaultState());
    private static final MaterialRules.MaterialRule STONE = MaterialRules.block(Blocks.STONE.getDefaultState());
    private static final MaterialRules.MaterialRule NETHERRACK = MaterialRules.block(Blocks.NETHERRACK.getDefaultState());
    private static final MaterialRules.MaterialRule SOUL_SOIL = MaterialRules.block(Blocks.SOUL_SOIL.getDefaultState());
    private static final MaterialRules.MaterialRule SOUL_SAND = MaterialRules.block(Blocks.SOUL_SAND.getDefaultState());
    private static final MaterialRules.MaterialRule DIRT = MaterialRules.block(Blocks.DIRT.getDefaultState());
    private static final MaterialRules.MaterialRule PODZOL = MaterialRules.block(Blocks.PODZOL.getDefaultState());

    /**
     * Creates a surface rule for a test biome.
     * This rule defines how the surface of the test biome should be generated based on various conditions.
     *
     * @return A MaterialRules.MaterialRule that defines the surface generation rules for the test biome.
     */
    public static MaterialRules.MaterialRule makeTestBiomeRule(){
        MaterialRules.MaterialCondition surfacerules$conditionsource1 = MaterialRules.aboveY(YOffset.fixed(256), 0);
        MaterialRules.MaterialCondition surfacerules$conditionsource2 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(63), -1);
        MaterialRules.MaterialCondition surfacerules$conditionsource3 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(74), 1);
        MaterialRules.MaterialCondition surfacerules$conditionsource6 = MaterialRules.aboveY(YOffset.fixed(63), 0);
        MaterialRules.MaterialCondition surfacerules$conditionsource7 = MaterialRules.water(-1, 0);
        MaterialRules.MaterialCondition surfacerules$conditionsource9 = MaterialRules.waterWithStoneDepth(-6, -1);
        MaterialRules.MaterialCondition surfacerules$conditionsource10 = MaterialRules.hole();
        MaterialRules.MaterialRule surfacerules$rulesource2 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, STONE), GRAVEL);

        MaterialRules.MaterialCondition surfacerules$conditionsource15 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.909, -0.5454);
        MaterialRules.MaterialCondition surfacerules$conditionsource16 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.1818, 0.1818);
        MaterialRules.MaterialCondition surfacerules$conditionsource17 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, 0.5454, 0.909);

        return MaterialRules.condition(
                MaterialRules.biome(ModBiomes.TEST_BIOME),
                MaterialRules.sequence(
                        MaterialRules.condition(
                                MaterialRules.STONE_DEPTH_FLOOR,
                                MaterialRules.sequence(
                                        MaterialRules.condition(surfacerules$conditionsource1, NETHERRACK),
                                        MaterialRules.condition(
                                                surfacerules$conditionsource3,
                                                MaterialRules.sequence(
                                                        MaterialRules.condition(surfacerules$conditionsource15, SOUL_SOIL),
                                                        MaterialRules.condition(surfacerules$conditionsource16, SOUL_SOIL),
                                                        MaterialRules.condition(surfacerules$conditionsource17, SOUL_SOIL)
                                                )
                                        ),
                                        MaterialRules.condition(
                                                surfacerules$conditionsource7, MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SOUL_SOIL), SOUL_SAND)
                                        ),
                                        MaterialRules.condition(MaterialRules.not(surfacerules$conditionsource10), NETHERRACK),
                                        MaterialRules.condition(surfacerules$conditionsource9, SOUL_SAND),
                                        surfacerules$rulesource2
                                )
                        ),
                        MaterialRules.condition(
                                surfacerules$conditionsource2,
                                MaterialRules.sequence(
                                        MaterialRules.condition(
                                                surfacerules$conditionsource6, MaterialRules.condition(MaterialRules.not(surfacerules$conditionsource3), NETHERRACK)
                                        )
                                )
                        ),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(surfacerules$conditionsource9, SOUL_SAND))
                )
        );
    }

    /**
     * Creates a surface rule for a test nether biome.
     * This rule defines how the surface of the test nether biome should be generated based on various conditions.
     *
     * @return A MaterialRules.MaterialRule that defines the surface generation rules for the test nether biome.
     */
    public static MaterialRules.MaterialRule makeTestNetherBiomeRule() {
        MaterialRules.MaterialCondition surfacerules$conditionsource2 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(30), 0);
        MaterialRules.MaterialCondition surfacerules$conditionsource3 = MaterialRules.not(MaterialRules.aboveYWithStoneDepth(YOffset.fixed(35), 0));
        MaterialRules.MaterialCondition surfacerules$conditionsource8 = MaterialRules.noiseThreshold(NoiseParametersKeys.PATCH, -0.012);
        MaterialRules.MaterialCondition surfacerules$conditionsource11 = MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_STATE_SELECTOR, 0.0);
        MaterialRules.MaterialRule surfacerules$rulesource = MaterialRules.condition(
                surfacerules$conditionsource8, MaterialRules.condition(surfacerules$conditionsource2, MaterialRules.condition(surfacerules$conditionsource3, GRAVEL))
        );

        return MaterialRules.condition(
                MaterialRules.biome(ModBiomes.TEST_NETHER_BIOME),
                MaterialRules.sequence(
                        MaterialRules.condition(
                                MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(surfacerules$conditionsource11, DIRT), PODZOL)
                        ),
                        MaterialRules.condition(
                                MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                                MaterialRules.sequence(surfacerules$rulesource, MaterialRules.condition(surfacerules$conditionsource11, DIRT), PODZOL)
                        )
                )
        );
    }
}
