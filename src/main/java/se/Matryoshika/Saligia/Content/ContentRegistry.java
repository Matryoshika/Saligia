package se.Matryoshika.Saligia.Content;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.common.util.EnumHelper;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.Content.Blocks.*;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.*;
import se.Matryoshika.Saligia.Content.Items.*;
import se.Matryoshika.Saligia.Content.Tiles.TileRitualCOTH;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ContentRegistry {
	
	//Tool Materials================================================
	public static ToolMaterial SIN = EnumHelper.addToolMaterial("SIN", 3, -1, 15.0F, 21.0F, 0);
	
	//Blocks========================================================
	
	public static Block CHARCOAL_BLOCK;
	public static Block SIN_BLOCK;
	
	public static Block BLOCK_RITUAL_MASTER;
	public static Block GHAST_BLOCK;
	
	
	public static BlockRitualMaster BLOCK_RITUAL_COTH;
	
	public static List<Block> blockList = new ArrayList<Block>();
	
	public static void registerBlocks(){
		
		BlockRegistryInjector.blockList.add(CHARCOAL_BLOCK = new BlockCharcoal());
		BlockRegistryInjector.blockList.add(GHAST_BLOCK = new BlockGhast());
		BlockRegistryInjector.blockList.add(SIN_BLOCK = new BlockSinBlock());
		
		BlockRegistryInjector.ritualList.add(BLOCK_RITUAL_COTH =  (BlockRitualMaster) new BlockRitualMaster().setRitualName("Cognizance Of The Hellmouth"));
		
		
		BlockRegistryInjector.blockList.add(BLOCK_RITUAL_MASTER = new BlockRitualMaster().setRegistryName("BlockRitualMaster").setUnlocalizedName("saligia:BlockRitualMaster"));
		
		
	}

	//Items=========================================================
	
	public static Item DUST_INGOT;
	public static Item TAPE_MEASURE;
	public static Item RITUAL_ACTIVATOR;
	public static Item SIN_INGOT;
	
	public static List<Item> itemList = new ArrayList<Item>();
	
	public static void registerItems(){
		
		itemList.add(DUST_INGOT = new ItemDustIngot());
		itemList.add(SIN_INGOT = new ItemSinIngot());
		itemList.add(TAPE_MEASURE = new ItemTapeMeasure());
		itemList.add(RITUAL_ACTIVATOR = new ItemRitualActivator());
	}
	
	
	//Tiles=========================================================
	public static void registerTiles(){
		GameRegistry.registerTileEntity(TileRitualCOTH.class, TileRitualCOTH.getName());
	}

}
