package com.deepdepth.content.items;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.deepdepth.world.features.helpers.VoxelShapes;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.grower.CherryTreeGrower;
import net.minecraft.world.level.block.state.BlockState;import net.minecraft.world.level.block.state.properties.Property;

public class DebugStick extends Item{

	public DebugStick(Properties p_41383_) {
		super(p_41383_);
		// TODO Auto-generated constructor stub
	}
	

	
	@Override
	public InteractionResult useOn(UseOnContext ctx) {
		Level level = ctx.getLevel();
		RandomSource rand = level.getRandom();
		
		if (!level.isClientSide()) {
			

			
			

		}
		return InteractionResult.SUCCESS;
	}
	

}
