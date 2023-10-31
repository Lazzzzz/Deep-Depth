package com.deepdepth.registries;

import net.neoforged.bus.api.IEventBus;

public class DDModRegistry {

	public static void initMod(IEventBus modEventBus) {
		ItemsRegistry.initItems();
		modEventBus.register(ItemsRegistry.ITEMS);
		
		BlockRegistry.initBlocks();
		modEventBus.register(BlockRegistry.BLOCKS);
		
		
//		DimensionRegistry.register();
		
	}
	
	
}
