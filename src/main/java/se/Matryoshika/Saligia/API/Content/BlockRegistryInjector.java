/**
 * 
 */
package se.Matryoshika.Saligia.API.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.Content.ContentRegistry;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.BlockRitualMaster;
import se.Matryoshika.Saligia.Utils.ConfigHandler;

/**
 * This class was created by Matryoshika Aug 11, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class BlockRegistryInjector {
	
	public static List<Block> blockList = new ArrayList<Block>();
	public static List<BlockRitualMaster> ritualList = new ArrayList<BlockRitualMaster>();
	
	public static HashMap getMasterBlock = new HashMap();
	
	public static void addToList(Block block){
		blockList.add(block);
	}
	
	public static void registerSaligiaBlock(){
		for(Block block : blockList){
			if(!((Boolean) ConfigHandler.isBlockEnabledMap.get(block.getRegistryName().toString()))){
				continue;
			}
			GameRegistry.register(block);
			//System.out.println("Registered : " + block.getUnlocalizedName());
			ItemBlock iblock = new ItemBlock(block);
			iblock.setRegistryName(block.getRegistryName());
			GameRegistry.register(iblock);
		}
	}
	
	public static void registerSaligiaRitualBlock(){
	//Has to bypass RegistryName, as it's 1 block, many tiles, with tiles based on this name
		for(BlockRitualMaster block : ritualList){
			if(!((Boolean) ConfigHandler.isRitualEnabledMap.get(block.getRitualName()))){
				continue;
			}
			String name = block.name.replaceAll("\\s", "");
			block.setRegistryName(name);
			block.setUnlocalizedName(Saligia.MODID+":"+name);
			GameRegistry.register(block);
			ItemBlock iblock = new ItemBlock(block);
			iblock.setRegistryName(name);
			GameRegistry.register(iblock);
			getMasterBlock.put(block.getRitualName(), block);
		}
	}
}
