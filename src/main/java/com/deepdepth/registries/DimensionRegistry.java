package com.deepdepth.registries;

import com.deepdepth.DeepDepth;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

public class DimensionRegistry {

    public static final ResourceKey<Level> DD_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DeepDepth.MODID, "deepdepth"));
    public static final ResourceKey<DimensionType> DD_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, DD_KEY.registry());

    public static void register() {
        System.out.println("Registering ModDimensions for " + DeepDepth.MODID);
    }
	
}
