package Matryoshika.mods.saligia.worldgen;

import Matryoshika.mods.saligia.blocks.saligia_Blocks;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class biomeDecorationAltar {
	
	private Block blockForGen;
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void decorateNether(DecorateBiomeEvent.Decorate event){
		
		if(event.world.getBiomeGenForCoords(event.chunkX, event.chunkZ).biomeName.equals("Hell")){
			if(event.rand.nextInt(3000) == 0){
				int x = event.chunkX + event.rand.nextInt(16) + 8;
				int z = event.chunkZ + event.rand.nextInt(16) + 8;
				int y = findGround(event.world, event.chunkX*16, event.chunkZ*16);
				
				event.world.setBlock(x, y, z, saligia_Blocks.AltarPagan);
				//System.out.println("Generated an Altar at :"+x+","+y+","+z);
			}
		}
	}
	
	int findGround (World world, int x, int z){
		int returnY = -1;
		do{
			returnY++;
		}while(world.getBlock(x, returnY, z) != Blocks.air);
		do{
			return returnY;
			}while(world.getBlock(x, returnY+1, z) == Blocks.air && returnY < 128);
    }

}
