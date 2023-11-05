package com.deepdepth.world.features.helpers;

import java.util.Random;

import org.joml.Vector3f;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class VoxelShapes {
	
	
	
    public static void drawEllipseWithZExtension(Level world, BlockPos centerPos, double radiusX, double radiusY, double radiusZ,  BlockState blockState, boolean shouldReplaceBlock) {       
        int centerX = centerPos.getX();
        int centerY = centerPos.getY();
        int centerZ = centerPos.getZ();
        
        for (double x = -radiusX; x <= radiusX; x++) {
            for (double y = -radiusY; y <= radiusY; y++) {
                for (double z = -radiusZ; z <= radiusZ; z++) {
                    if ((x * x) / (radiusX * radiusX) + (y * y) / (radiusY * radiusY) + (z * z) / (radiusZ * radiusZ) <= 1) {
                        BlockPos blockPos = new BlockPos((int) (centerX + x), (int) (centerY + y), (int) (centerZ + z));
                        if (shouldReplaceBlock || !world.getBlockState(blockPos).isSolid())
                        	world.setBlockAndUpdate(blockPos, blockState);                        
                    }
                }
            }
        }
    }
}
    
    
