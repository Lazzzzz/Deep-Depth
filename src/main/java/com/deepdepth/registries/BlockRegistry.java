package com.deepdepth.registries;

import com.deepdepth.DeepDepth;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DeepDepth.MODID);
	
    public static void initBlocks() {
    	
    }
    
}
