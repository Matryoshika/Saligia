package Matryoshika.mods.saligia.worldgen;

import java.util.Random;

import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.common.BiomeManager.BiomeType;
import cpw.mods.fml.common.IWorldGenerator;

public class worldGenAltar implements IWorldGenerator{
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		BiomeGenBase b = world.getBiomeGenForCoords(chunkX, chunkZ);
		Random randomchunk = new Random();
		int chunkrandom = randomchunk.nextInt(2) + 1;
		Random randomY = new Random();
		int yRandom = randomY.nextInt(120)+1; 
		if(b.biomeName.equals("Hell")){
			if (chunkrandom == 1){
				
				if(world.getBlock(chunkX*16, yRandom, chunkZ*16) == Blocks.air)
			world.setBlock(chunkX*16 + random.nextInt(16), yRandom, chunkZ*16 + random.nextInt(16), saligia_Blocks.AltarPagan);
			world.scheduleBlockUpdate(chunkX, 70, chunkZ, saligia_Blocks.AltarPagan, 100);
			}
			else{
				return;
			}
		}
		else{
			return;
		}
	}
	
}