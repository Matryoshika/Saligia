/**
 * 
 */
package se.Matryoshika.Saligia.Rendering;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.API.Content.ItemRegistryInjector;
import se.Matryoshika.Saligia.API.Rendering.RenderingRegistryInjector;
import se.Matryoshika.Saligia.Content.Blocks.Utility.UtilityBlock;
import se.Matryoshika.Saligia.Utils.ConfigHandler;

/**
 * This class was created by Matryoshika Aug 10, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class RenderRegister {
	
	public static void registerRenderers(){
		for(Block block : BlockRegistryInjector.blockList){
			if(!((Boolean) ConfigHandler.isBlockEnabledMap.get(block.getRegistryName().toString())) || block instanceof UtilityBlock){
				continue;
			}
			RenderingRegistryInjector.regWithLocation(block, block.getRegistryName(), 0);
		}
		
		for(Item item : ItemRegistryInjector.itemList){
			if(!((Boolean) ConfigHandler.isItemEnabledMap.get(item.getRegistryName().toString()))){
				continue;
			}
			RenderingRegistryInjector.regWithLocation(item, item.getRegistryName(), 0);
		}
		
		for(UtilityBlock block : BlockRegistryInjector.utilityList){
			RenderingRegistryInjector.regWithLocation(block, block.getRegistryName(), 0);
		}
		
		//RenderingRegistry.registerEntityRenderingHandler(EntityUtility.class, RenderUtility::new);
		
	}
	

}
