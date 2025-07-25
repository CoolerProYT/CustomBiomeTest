package com.coolerpromc.custombiomes.core;

import com.coolerpromc.custombiomes.worldgen.surface.ModSurfaceRules;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;

/**
 * This class copies the surface rules from Vanilla SurfaceRuleData class,
 * call your own RuleSource methods to add your own rules.
 * Add your surface rules between the comments in the overworld() and nether() methods.
 */
public class SurfaceRulesModifier {
    private static final MaterialRules.MaterialRule AIR = makeStateRule(Blocks.AIR);
    private static final MaterialRules.MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);
    private static final MaterialRules.MaterialRule WHITE_TERRACOTTA = makeStateRule(Blocks.WHITE_TERRACOTTA);
    private static final MaterialRules.MaterialRule ORANGE_TERRACOTTA = makeStateRule(Blocks.ORANGE_TERRACOTTA);
    private static final MaterialRules.MaterialRule TERRACOTTA = makeStateRule(Blocks.TERRACOTTA);
    private static final MaterialRules.MaterialRule RED_SAND = makeStateRule(Blocks.RED_SAND);
    private static final MaterialRules.MaterialRule RED_SANDSTONE = makeStateRule(Blocks.RED_SANDSTONE);
    private static final MaterialRules.MaterialRule STONE = makeStateRule(Blocks.STONE);
    private static final MaterialRules.MaterialRule DEEPSLATE = makeStateRule(Blocks.DEEPSLATE);
    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule PODZOL = makeStateRule(Blocks.PODZOL);
    private static final MaterialRules.MaterialRule COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final MaterialRules.MaterialRule MYCELIUM = makeStateRule(Blocks.MYCELIUM);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule CALCITE = makeStateRule(Blocks.CALCITE);
    private static final MaterialRules.MaterialRule GRAVEL = makeStateRule(Blocks.GRAVEL);
    private static final MaterialRules.MaterialRule SAND = makeStateRule(Blocks.SAND);
    private static final MaterialRules.MaterialRule SANDSTONE = makeStateRule(Blocks.SANDSTONE);
    private static final MaterialRules.MaterialRule PACKED_ICE = makeStateRule(Blocks.PACKED_ICE);
    private static final MaterialRules.MaterialRule SNOW_BLOCK = makeStateRule(Blocks.SNOW_BLOCK);
    private static final MaterialRules.MaterialRule MUD = makeStateRule(Blocks.MUD);
    private static final MaterialRules.MaterialRule POWDER_SNOW = makeStateRule(Blocks.POWDER_SNOW);
    private static final MaterialRules.MaterialRule ICE = makeStateRule(Blocks.ICE);
    private static final MaterialRules.MaterialRule WATER = makeStateRule(Blocks.WATER);
    private static final MaterialRules.MaterialRule LAVA = makeStateRule(Blocks.LAVA);
    private static final MaterialRules.MaterialRule NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final MaterialRules.MaterialRule SOUL_SAND = makeStateRule(Blocks.SOUL_SAND);
    private static final MaterialRules.MaterialRule SOUL_SOIL = makeStateRule(Blocks.SOUL_SOIL);
    private static final MaterialRules.MaterialRule BASALT = makeStateRule(Blocks.BASALT);
    private static final MaterialRules.MaterialRule BLACKSTONE = makeStateRule(Blocks.BLACKSTONE);
    private static final MaterialRules.MaterialRule WARPED_WART_BLOCK = makeStateRule(Blocks.WARPED_WART_BLOCK);
    private static final MaterialRules.MaterialRule WARPED_NYLIUM = makeStateRule(Blocks.WARPED_NYLIUM);
    private static final MaterialRules.MaterialRule NETHER_WART_BLOCK = makeStateRule(Blocks.NETHER_WART_BLOCK);
    private static final MaterialRules.MaterialRule CRIMSON_NYLIUM = makeStateRule(Blocks.CRIMSON_NYLIUM);

    public static MaterialRules.MaterialRule overworld(boolean aboveGround, boolean bedrockRoof, boolean bedrockFloor) {
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource = MaterialRules.aboveY(YOffset.fixed(97), 2);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource1 = MaterialRules.aboveY(YOffset.fixed(256), 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource2 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(63), -1);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource3 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(74), 1);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource4 = MaterialRules.aboveY(YOffset.fixed(60), 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource5 = MaterialRules.aboveY(YOffset.fixed(62), 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource6 = MaterialRules.aboveY(YOffset.fixed(63), 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource7 = MaterialRules.water(-1, 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource8 = MaterialRules.water(0, 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource9 = MaterialRules.waterWithStoneDepth(-6, -1);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource10 = MaterialRules.hole();
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource11 = MaterialRules.biome(BiomeKeys.FROZEN_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource12 = MaterialRules.steepSlope();
        MaterialRules.MaterialRule surfaceRules$rulesource = MaterialRules.sequence(MaterialRules.condition(surfacetulesRules$conditionSource8, GRASS_BLOCK), DIRT);
        MaterialRules.MaterialRule surfaceRules$rulesource1 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, SANDSTONE), SAND);
        MaterialRules.MaterialRule surfaceRules$rulesource2 = MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, STONE), GRAVEL);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource13 = MaterialRules.biome(BiomeKeys.WARM_OCEAN, BiomeKeys.BEACH, BiomeKeys.SNOWY_BEACH);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource14 = MaterialRules.biome(BiomeKeys.DESERT);
        MaterialRules.MaterialRule surfaceRules$rulesource3 = MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.STONY_PEAKS),
                        MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.CALCITE, -0.0125, 0.0125), CALCITE), STONE)
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.STONY_SHORE),
                        MaterialRules.sequence(MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.GRAVEL, -0.05, 0.05), surfaceRules$rulesource2), STONE)
                ),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.WINDSWEPT_HILLS), MaterialRules.condition(surfaceNoiseAbove(1.0), STONE)),
                MaterialRules.condition(surfacetulesRules$conditionSource13, surfaceRules$rulesource1),
                MaterialRules.condition(surfacetulesRules$conditionSource14, surfaceRules$rulesource1),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.DRIPSTONE_CAVES), STONE)
        );
        MaterialRules.MaterialRule surfaceRules$rulesource4 = MaterialRules.condition(
                MaterialRules.noiseThreshold(NoiseParametersKeys.POWDER_SNOW, 0.45, 0.58), MaterialRules.condition(surfacetulesRules$conditionSource8, POWDER_SNOW)
        );
        MaterialRules.MaterialRule surfaceRules$rulesource5 = MaterialRules.condition(
                MaterialRules.noiseThreshold(NoiseParametersKeys.POWDER_SNOW, 0.35, 0.6), MaterialRules.condition(surfacetulesRules$conditionSource8, POWDER_SNOW)
        );
        MaterialRules.MaterialRule surfaceRules$rulesource6 = MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.FROZEN_PEAKS),
                        MaterialRules.sequence(
                                MaterialRules.condition(surfacetulesRules$conditionSource12, PACKED_ICE),
                                MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.PACKED_ICE, -0.5, 0.2), PACKED_ICE),
                                MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.ICE, -0.0625, 0.025), ICE),
                                MaterialRules.condition(surfacetulesRules$conditionSource8, SNOW_BLOCK)
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.SNOWY_SLOPES),
                        MaterialRules.sequence(
                                MaterialRules.condition(surfacetulesRules$conditionSource12, STONE),
                                surfaceRules$rulesource4,
                                MaterialRules.condition(surfacetulesRules$conditionSource8, SNOW_BLOCK)
                        )
                ),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.JAGGED_PEAKS), STONE),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.GROVE), MaterialRules.sequence(surfaceRules$rulesource4, DIRT)),
                surfaceRules$rulesource3,
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.WINDSWEPT_SAVANNA), MaterialRules.condition(surfaceNoiseAbove(1.75), STONE)),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS),
                        MaterialRules.sequence(
                                MaterialRules.condition(surfaceNoiseAbove(2.0), surfaceRules$rulesource2),
                                MaterialRules.condition(surfaceNoiseAbove(1.0), STONE),
                                MaterialRules.condition(surfaceNoiseAbove(-1.0), DIRT),
                                surfaceRules$rulesource2
                        )
                ),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.MANGROVE_SWAMP), MUD),
                DIRT
        );
        MaterialRules.MaterialRule surfaceRules$rulesource7 = MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.FROZEN_PEAKS),
                        MaterialRules.sequence(
                                MaterialRules.condition(surfacetulesRules$conditionSource12, PACKED_ICE),
                                MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.PACKED_ICE, 0.0, 0.2), PACKED_ICE),
                                MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.ICE, 0.0, 0.025), ICE),
                                MaterialRules.condition(surfacetulesRules$conditionSource8, SNOW_BLOCK)
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.SNOWY_SLOPES),
                        MaterialRules.sequence(
                                MaterialRules.condition(surfacetulesRules$conditionSource12, STONE),
                                surfaceRules$rulesource5,
                                MaterialRules.condition(surfacetulesRules$conditionSource8, SNOW_BLOCK)
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.JAGGED_PEAKS),
                        MaterialRules.sequence(
                                MaterialRules.condition(surfacetulesRules$conditionSource12, STONE), MaterialRules.condition(surfacetulesRules$conditionSource8, SNOW_BLOCK)
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.GROVE),
                        MaterialRules.sequence(surfaceRules$rulesource5, MaterialRules.condition(surfacetulesRules$conditionSource8, SNOW_BLOCK))
                ),
                surfaceRules$rulesource3,
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.WINDSWEPT_SAVANNA),
                        MaterialRules.sequence(MaterialRules.condition(surfaceNoiseAbove(1.75), STONE), MaterialRules.condition(surfaceNoiseAbove(-0.5), COARSE_DIRT))
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.WINDSWEPT_GRAVELLY_HILLS),
                        MaterialRules.sequence(
                                MaterialRules.condition(surfaceNoiseAbove(2.0), surfaceRules$rulesource2),
                                MaterialRules.condition(surfaceNoiseAbove(1.0), STONE),
                                MaterialRules.condition(surfaceNoiseAbove(-1.0), surfaceRules$rulesource),
                                surfaceRules$rulesource2
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA),
                        MaterialRules.sequence(MaterialRules.condition(surfaceNoiseAbove(1.75), COARSE_DIRT), MaterialRules.condition(surfaceNoiseAbove(-0.95), PODZOL))
                ),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.ICE_SPIKES), MaterialRules.condition(surfacetulesRules$conditionSource8, SNOW_BLOCK)),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.MANGROVE_SWAMP), MUD),
                MaterialRules.condition(MaterialRules.biome(BiomeKeys.MUSHROOM_FIELDS), MYCELIUM),
                surfaceRules$rulesource
        );
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource15 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.909, -0.5454);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource16 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, -0.1818, 0.1818);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource17 = MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, 0.5454, 0.909);
        MaterialRules.MaterialRule surfaceRules$rulesource8 = MaterialRules.sequence(
                MaterialRules.condition(
                        MaterialRules.STONE_DEPTH_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.biome(BiomeKeys.WOODED_BADLANDS),
                                        MaterialRules.condition(
                                                surfacetulesRules$conditionSource,
                                                MaterialRules.sequence(
                                                        MaterialRules.condition(surfacetulesRules$conditionSource15, COARSE_DIRT),
                                                        MaterialRules.condition(surfacetulesRules$conditionSource16, COARSE_DIRT),
                                                        MaterialRules.condition(surfacetulesRules$conditionSource17, COARSE_DIRT),
                                                        surfaceRules$rulesource
                                                )
                                        )
                                ),
                                MaterialRules.condition(
                                        MaterialRules.biome(BiomeKeys.SWAMP),
                                        MaterialRules.condition(
                                                surfacetulesRules$conditionSource5,
                                                MaterialRules.condition(
                                                        MaterialRules.not(surfacetulesRules$conditionSource6), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0), WATER)
                                                )
                                        )
                                ),
                                MaterialRules.condition(
                                        MaterialRules.biome(BiomeKeys.MANGROVE_SWAMP),
                                        MaterialRules.condition(
                                                surfacetulesRules$conditionSource4,
                                                MaterialRules.condition(
                                                        MaterialRules.not(surfacetulesRules$conditionSource6), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0), WATER)
                                                )
                                        )
                                )
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS, BiomeKeys.WOODED_BADLANDS),
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.STONE_DEPTH_FLOOR,
                                        MaterialRules.sequence(
                                                MaterialRules.condition(surfacetulesRules$conditionSource1, ORANGE_TERRACOTTA),
                                                MaterialRules.condition(
                                                        surfacetulesRules$conditionSource3,
                                                        MaterialRules.sequence(
                                                                MaterialRules.condition(surfacetulesRules$conditionSource15, TERRACOTTA),
                                                                MaterialRules.condition(surfacetulesRules$conditionSource16, TERRACOTTA),
                                                                MaterialRules.condition(surfacetulesRules$conditionSource17, TERRACOTTA),
                                                                MaterialRules.terracottaBands()
                                                        )
                                                ),
                                                MaterialRules.condition(
                                                        surfacetulesRules$conditionSource7, MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, RED_SANDSTONE), RED_SAND)
                                                ),
                                                MaterialRules.condition(MaterialRules.not(surfacetulesRules$conditionSource10), ORANGE_TERRACOTTA),
                                                MaterialRules.condition(surfacetulesRules$conditionSource9, WHITE_TERRACOTTA),
                                                surfaceRules$rulesource2
                                        )
                                ),
                                MaterialRules.condition(
                                        surfacetulesRules$conditionSource2,
                                        MaterialRules.sequence(
                                                MaterialRules.condition(
                                                        surfacetulesRules$conditionSource6, MaterialRules.condition(MaterialRules.not(surfacetulesRules$conditionSource3), ORANGE_TERRACOTTA)
                                                ),
                                                MaterialRules.terracottaBands()
                                        )
                                ),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, MaterialRules.condition(surfacetulesRules$conditionSource9, WHITE_TERRACOTTA))
                        )
                ),
                // Custom surface rules
                ModSurfaceRules.makeTestBiomeRule(), // Call your own custom surface rules here
                // End of custom surface rules
                MaterialRules.condition(
                        MaterialRules.STONE_DEPTH_FLOOR,
                        MaterialRules.condition(
                                surfacetulesRules$conditionSource7,
                                MaterialRules.sequence(
                                        MaterialRules.condition(
                                                surfacetulesRules$conditionSource11,
                                                MaterialRules.condition(
                                                        surfacetulesRules$conditionSource10,
                                                        MaterialRules.sequence(
                                                                MaterialRules.condition(surfacetulesRules$conditionSource8, AIR), MaterialRules.condition(MaterialRules.temperature(), ICE), WATER
                                                        )
                                                )
                                        ),
                                        surfaceRules$rulesource7
                                )
                        )
                ),
                MaterialRules.condition(
                        surfacetulesRules$conditionSource9,
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.condition(surfacetulesRules$conditionSource11, MaterialRules.condition(surfacetulesRules$conditionSource10, WATER))
                                ),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, surfaceRules$rulesource6),
                                MaterialRules.condition(surfacetulesRules$conditionSource13, MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, SANDSTONE)),
                                MaterialRules.condition(surfacetulesRules$conditionSource14, MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30, SANDSTONE))
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.STONE_DEPTH_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.biome(BiomeKeys.FROZEN_PEAKS, BiomeKeys.JAGGED_PEAKS), STONE),
                                MaterialRules.condition(MaterialRules.biome(BiomeKeys.WARM_OCEAN, BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.DEEP_LUKEWARM_OCEAN), surfaceRules$rulesource1),
                                surfaceRules$rulesource2
                        )
                )
        );

        ImmutableList.Builder<MaterialRules.MaterialRule> builder = ImmutableList.builder();
        if (bedrockRoof) {
            builder.add(
                    MaterialRules.condition(MaterialRules.not(MaterialRules.verticalGradient("bedrock_roof", YOffset.belowTop(5), YOffset.getTop())), BEDROCK)
            );
        }

        if (bedrockFloor) {
            builder.add(MaterialRules.condition(MaterialRules.verticalGradient("bedrock_floor", YOffset.getBottom(), YOffset.aboveBottom(5)), BEDROCK));
        }

        MaterialRules.MaterialRule surfaceRules$rulesource9 = MaterialRules.condition(MaterialRules.surface(), surfaceRules$rulesource8);
        builder.add(aboveGround ? surfaceRules$rulesource9 : surfaceRules$rulesource8);
        builder.add(MaterialRules.condition(MaterialRules.verticalGradient("deepslate", YOffset.fixed(0), YOffset.fixed(8)), DEEPSLATE));
        return MaterialRules.sequence(builder.build().toArray(MaterialRules.MaterialRule[]::new));
    }

    public static MaterialRules.MaterialRule nether() {
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource = MaterialRules.aboveY(YOffset.fixed(31), 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource1 = MaterialRules.aboveY(YOffset.fixed(32), 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource2 = MaterialRules.aboveYWithStoneDepth(YOffset.fixed(30), 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource3 = MaterialRules.not(MaterialRules.aboveYWithStoneDepth(YOffset.fixed(35), 0));
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource4 = MaterialRules.aboveY(YOffset.belowTop(5), 0);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource5 = MaterialRules.hole();
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource6 = MaterialRules.noiseThreshold(NoiseParametersKeys.SOUL_SAND_LAYER, -0.012);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource7 = MaterialRules.noiseThreshold(NoiseParametersKeys.GRAVEL_LAYER, -0.012);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource8 = MaterialRules.noiseThreshold(NoiseParametersKeys.PATCH, -0.012);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource9 = MaterialRules.noiseThreshold(NoiseParametersKeys.NETHERRACK, 0.54);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource10 = MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_WART, 1.17);
        MaterialRules.MaterialCondition surfacetulesRules$conditionSource11 = MaterialRules.noiseThreshold(NoiseParametersKeys.NETHER_STATE_SELECTOR, 0.0);
        MaterialRules.MaterialRule surfaceRules$rulesource = MaterialRules.condition(
                surfacetulesRules$conditionSource8, MaterialRules.condition(surfacetulesRules$conditionSource2, MaterialRules.condition(surfacetulesRules$conditionSource3, GRAVEL))
        );
        return MaterialRules.sequence(
                MaterialRules.condition(MaterialRules.verticalGradient("bedrock_floor", YOffset.getBottom(), YOffset.aboveBottom(5)), BEDROCK),
                MaterialRules.condition(MaterialRules.not(MaterialRules.verticalGradient("bedrock_roof", YOffset.belowTop(5), YOffset.getTop())), BEDROCK),
                MaterialRules.condition(surfacetulesRules$conditionSource4, NETHERRACK),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.BASALT_DELTAS),
                        MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, BASALT),
                                MaterialRules.condition(
                                        MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                                        MaterialRules.sequence(surfaceRules$rulesource, MaterialRules.condition(surfacetulesRules$conditionSource11, BASALT), BLACKSTONE)
                                )
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.SOUL_SAND_VALLEY),
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, MaterialRules.sequence(MaterialRules.condition(surfacetulesRules$conditionSource11, SOUL_SAND), SOUL_SOIL)
                                ),
                                MaterialRules.condition(
                                        MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                                        MaterialRules.sequence(surfaceRules$rulesource, MaterialRules.condition(surfacetulesRules$conditionSource11, SOUL_SAND), SOUL_SOIL)
                                )
                        )
                ),
                // Custom surface rules
                ModSurfaceRules.makeTestNetherBiomeRule(), // Call your own custom surface rules here
                // End of custom surface rules
                MaterialRules.condition(
                        MaterialRules.STONE_DEPTH_FLOOR,
                        MaterialRules.sequence(
                                MaterialRules.condition(MaterialRules.not(surfacetulesRules$conditionSource1), MaterialRules.condition(surfacetulesRules$conditionSource5, LAVA)),
                                MaterialRules.condition(
                                        MaterialRules.biome(BiomeKeys.WARPED_FOREST),
                                        MaterialRules.condition(
                                                MaterialRules.not(surfacetulesRules$conditionSource9),
                                                MaterialRules.condition(
                                                        surfacetulesRules$conditionSource,
                                                        MaterialRules.sequence(MaterialRules.condition(surfacetulesRules$conditionSource10, WARPED_WART_BLOCK), WARPED_NYLIUM)
                                                )
                                        )
                                ),
                                MaterialRules.condition(
                                        MaterialRules.biome(BiomeKeys.CRIMSON_FOREST),
                                        MaterialRules.condition(
                                                MaterialRules.not(surfacetulesRules$conditionSource9),
                                                MaterialRules.condition(
                                                        surfacetulesRules$conditionSource,
                                                        MaterialRules.sequence(MaterialRules.condition(surfacetulesRules$conditionSource10, NETHER_WART_BLOCK), CRIMSON_NYLIUM)
                                                )
                                        )
                                )
                        )
                ),
                MaterialRules.condition(
                        MaterialRules.biome(BiomeKeys.NETHER_WASTES),
                        MaterialRules.sequence(
                                MaterialRules.condition(
                                        MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH,
                                        MaterialRules.condition(
                                                surfacetulesRules$conditionSource6,
                                                MaterialRules.sequence(
                                                        MaterialRules.condition(
                                                                MaterialRules.not(surfacetulesRules$conditionSource5),
                                                                MaterialRules.condition(surfacetulesRules$conditionSource2, MaterialRules.condition(surfacetulesRules$conditionSource3, SOUL_SAND))
                                                        ),
                                                        NETHERRACK
                                                )
                                        )
                                ),
                                MaterialRules.condition(
                                        MaterialRules.STONE_DEPTH_FLOOR,
                                        MaterialRules.condition(
                                                surfacetulesRules$conditionSource,
                                                MaterialRules.condition(
                                                        surfacetulesRules$conditionSource3,
                                                        MaterialRules.condition(
                                                                surfacetulesRules$conditionSource7,
                                                                MaterialRules.sequence(
                                                                        MaterialRules.condition(surfacetulesRules$conditionSource1, GRAVEL),
                                                                        MaterialRules.condition(MaterialRules.not(surfacetulesRules$conditionSource5), GRAVEL)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                NETHERRACK
        );
    }

    private static MaterialRules.MaterialCondition surfaceNoiseAbove(double d) {
        return MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, d / (double)8.25F, Double.MAX_VALUE);
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}