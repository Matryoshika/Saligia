/**
 * 
 */
package se.Matryoshika.Saligia.API.Rendering;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.BlockRitualMaster;

/**
 * This class was created by Matryoshika Aug 11, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class RenderingRegistryInjector {
	
	//Simply a fallback method in the API, if one cannot manage register a block/item with models from another modid
	
	
	/**
	 * @param block : The instance of the block you added to Saligia that you want rendered.
	 * @param location : Create a new ResourceLocation using the registryname with your own modid.
	 * @param meta : The meta of the block you want rendered.
	 */
	public static void regWithLocation(Block block, ResourceLocation location, int meta){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(location, "inventory"));
	}
	
	/**
	 * @param item : The instance of the item you added to Saligia that you want rendered.
	 * @param location : Create a new ResourceLocation using the registryname with your own modid.
	 * @param meta : The meta of the item you want rendered.
	 */
	public static void regWithLocation(Item item, ResourceLocation location, int meta){
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(location, "inventory"));
	}
	
	/**
	 * @param block : The instance of the BlockRitualMaster you added to Saligia that you want rendered.
	 * @param location : Create a new ResourceLocation using the registryname with your own modid.
	 * @param meta : The meta of the block you want rendered.
	 */
	public static void reg(BlockRitualMaster block, ModelResourceLocation location, int meta){
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, location);
	}

}
