package com.deepdepth.registries;

import com.deepdepth.DeepDepth;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;

public class DimensionRegistry {

    public static final ResourceKey<Level> DD_KEY = ResourceKey.create(Registries.DIMENSION,
            new ResourceLocation(DeepDepth.MODID, "deepdepth"));
    public static final ResourceKey<DimensionType> DD_TYPE =
            ResourceKey.create(Registries.DIMENSION_TYPE, DD_KEY.registry());
    
    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, DeepDepth.MODID);

    
    public static void register() {
        System.out.println("Registering ModDimensions for " + DeepDepth.MODID);
    }
	
}
