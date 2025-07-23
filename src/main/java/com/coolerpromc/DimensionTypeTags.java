package com.coolerpromc;

import com.coolerpromc.CustomBiomes;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.dimension.DimensionType;

public class DimensionTypeTags {

    public static final TagKey<DimensionType> OVERWORLD_REGIONS = create("overworld_regions");
    public static final TagKey<DimensionType> NETHER_REGIONS = create("nether_regions");

    private static TagKey<DimensionType> create(String id) {
        return TagKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(CustomBiomes.MOD_ID, id));
    }

    public static void init() {}
}
