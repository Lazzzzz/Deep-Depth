package com.deepdepth.world.features.zone1;

import org.joml.Vector2f;

import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BigVerticalTree extends Feature<NoneFeatureConfiguration> {
	
	
	public BigVerticalTree(Codec<NoneFeatureConfiguration> p_65786_) {
		super(p_65786_);
	}
	
	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
		BlockPos pos = ctx.origin();
		int size = ctx.random().nextInt(15) + 20;

		Vector2f offset = generateOffset(ctx.random());
		makeBranch(ctx.level(), pos, size, 4, offset.x, offset.y);
		return false;
	}
	
	public Vector2f generateOffset(RandomSource rand) {
		float offsetx;
		float offsetz;
		
		while (true) {
			offsetx = (rand.nextFloat() - 0.5f) * ((rand.nextFloat() * 1.25f) + 1);
			offsetz = (rand.nextFloat() - 0.5f) * ((rand.nextFloat() * 1.25f)+ 1);
			if (offsetx != 0 && offsetz != 0)
				break;
		}
		
		return new Vector2f(offsetx, offsetz);
		
	}
	
	public void makeBranch(WorldGenLevel worldGenLevel, BlockPos start, int size, int width, float offsetx, float offsetz) {
		if (width < 1 || size < 3)
			return;
		
		RandomSource rand = worldGenLevel.getRandom();
		
		float addx = 0;
		float addz = 0;
		BlockPos p = start;

		boolean shouldMakeNewBranch = rand.nextInt(5 - width) == 0;
		
        int numCount = 1;

        int[] randomNumbers = new int[numCount];
        
        for (int i = 0; i < numCount; i++) {
            randomNumbers[i] = rand.nextInt((int) (size / 2f), size);
        }
		
		for (int i = 0; i < size; i++) {
			float currentWight = 0.5f * width * ( 1f -  (i / (float) size));
			
			for (int j = (int) -currentWight; j < currentWight; j++) {
				for (int k = (int) -currentWight; k < currentWight; k++) {
					if (j * j + k * k <= currentWight * currentWight) {
						worldGenLevel.setBlock(p.offset(j, i, k), Blocks.DRIED_KELP_BLOCK.defaultBlockState(), 3);
					}
				}
			}
			
			addx += rand.nextInt(4) != 0 ? offsetx : 0;
			addz +=  rand.nextInt(4) != 0 ? offsetz : 0;
			p = new BlockPos(start.getX() + (int) addx, start.getY(), start.getZ() + (int) addz);
						
			if (shouldMakeNewBranch && i > size * 0.4f) {
				Vector2f offset = generateOffset(rand);

				
				makeBranch(worldGenLevel, p.offset((int) (offset.x * 2), i , (int) (offset.y * 2)), (int) (size * Math.max(rand.nextFloat(), 0.5f)), (int) (currentWight * 2), offset.x, offset.y);
			
			}
			
			for (int leafPos : randomNumbers) {
				if (leafPos == i) {
					makeLeaf(worldGenLevel, rand, p.above(i), 4 - width, 1, 0, Blocks.CHERRY_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
					makeLeaf(worldGenLevel, rand, p.offset((int) (-offsetx * 3), i + 1, (int) -offsetz * 3), 4 - width, 1, 0, Blocks.CHERRY_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
				}
			}
		}
		
		makeLeaf(worldGenLevel, rand, p.above(size), 5 - width, 1, 0, Blocks.CHERRY_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
		makeLeaf(worldGenLevel, rand, p.offset((int) (-offsetx * 3), size + 1, (int) -offsetz * 3), 5 - width, 1, 0, Blocks.CHERRY_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true));
		
	}
	
	public static void makeLeaf(WorldGenLevel worldGenLevel,RandomSource random, BlockPos centerPos, float xzRadius, float yRadius, float verticalBias,  BlockState blockState) {
        float xzRadiusSquared = xzRadius * xzRadius;
        float yRadiusSquared = yRadius * yRadius;
        float superRadiusSquared = xzRadiusSquared * yRadiusSquared;
        placeWithoutOverlap(worldGenLevel, centerPos, blockState);



        for (int y = 0; y <= yRadius; y++) {
            if (y > yRadius) continue;

            placeWithoutOverlap(worldGenLevel,centerPos.offset( 0,  y, 0), blockState);
            placeWithoutOverlap(worldGenLevel,centerPos.offset( 0, -y, 0), blockState);
        }

        for (int x = 0; x <= xzRadius; x++) {
            for (int z = 1; z <= xzRadius; z++) {
                if (x * x + z * z > xzRadiusSquared) continue;

                placeWithoutOverlap(worldGenLevel,centerPos.offset(  x, 0,  z), blockState);
                placeWithoutOverlap(worldGenLevel,centerPos.offset( -x, 0, -z), blockState);
                placeWithoutOverlap(worldGenLevel,centerPos.offset( -z, 0,  x), blockState);
                placeWithoutOverlap(worldGenLevel,centerPos.offset(  z, 0, -x), blockState);

                for (int y = 1; y <= yRadius; y++) {
                    float xzSquare = ((x * x + z * z) * yRadiusSquared);

                    if (xzSquare + (((y - verticalBias) * (y - verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        placeWithoutOverlap(worldGenLevel,centerPos.offset(  x,  y,  z), blockState);
                        placeWithoutOverlap(worldGenLevel,centerPos.offset( -x,  y, -z), blockState);
                        placeWithoutOverlap(worldGenLevel,centerPos.offset( -z,  y,  x), blockState);
                        placeWithoutOverlap(worldGenLevel,centerPos.offset(  z,  y, -x), blockState);
                    }

                    if (xzSquare + (((y + verticalBias) * (y + verticalBias)) * xzRadiusSquared) <= superRadiusSquared) {
                        placeWithoutOverlap(worldGenLevel,centerPos.offset(  x, -y,  z), blockState);
                        placeWithoutOverlap(worldGenLevel,centerPos.offset( -x, -y, -z), blockState);
                        placeWithoutOverlap(worldGenLevel,centerPos.offset( -z, -y,  x), blockState);
                        placeWithoutOverlap(worldGenLevel,centerPos.offset(  z, -y, -x), blockState);
                    }
                }
            }
        }
	}
	
	private static void placeWithoutOverlap(WorldGenLevel worldGenLevel, BlockPos pos, BlockState state) {
		if (!worldGenLevel.getBlockState(pos).isSolid())
			worldGenLevel.setBlock(pos, state, 3);        
	}
		
}
