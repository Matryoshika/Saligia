package Matryoshika.mods.saligia.worldgen;

import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class biomeDecorationRose {

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void whenDecoratingWorld(DecorateBiomeEvent.Decorate event){
		BiomeGenBase biome = event.world.getBiomeGenForCoords(event.chunkX, event.chunkZ);
		if(biome.biomeName.equals("Plains") || biome.biomeName.equals("Forest") || biome.biomeName.equals("ForestHills")){
			int x = event.chunkX + event.rand.nextInt(16) + 8;
			int z = event.chunkZ + event.rand.nextInt(16) + 8;
			int y = event.world.getTopSolidOrLiquidBlock(x, z);
			
			if(event.rand.nextInt(256) == 0){
				event.world.setBlock(x, y, z, saligia_Blocks.SinRose);
			}
			//System.out.println("Placed a Rose at :"+x+","+y+","+z);
		}
	}
	
	
}
