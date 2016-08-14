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
	
	public static Block charcoalBlock;
	
	public static Block ritualMasterBlock;
	public static Block ghastBlock;
	
	
	public static BlockRitualMaster ritualCOTHBlock;
	
	public static List<Block> blockList = new ArrayList<Block>();
	
	public static void registerBlocks(){
		
		BlockRegistryInjector.blockList.add(charcoalBlock = new BlockCharcoal());
		BlockRegistryInjector.blockList.add(ghastBlock = new BlockGhast());
		
		BlockRegistryInjector.ritualList.add(ritualCOTHBlock =  (BlockRitualMaster) new BlockRitualMaster().setRitualName("Cognizance Of The Hellmouth"));
		
		
		BlockRegistryInjector.blockList.add(ritualMasterBlock = new BlockRitualMaster().setRegistryName("BlockRitualMaster").setUnlocalizedName("saligia:BlockRitualMaster"));
		
		
	}

	//Items=========================================================
	
	public static Item dustIngotItem;
	public static Item tapeMeasureItem;
	public static Item ritualActivatorItem;
	
	public static List<Item> itemList = new ArrayList<Item>();
	
	public static void registerItems(){
		
		itemList.add(dustIngotItem = new ItemDustIngot());
		itemList.add(tapeMeasureItem = new ItemTapeMeasure());
		itemList.add(ritualActivatorItem = new ItemRitualActivator());
	}
	
	
	//Tiles=========================================================
	public static void registerTiles(){
		GameRegistry.registerTileEntity(TileRitualCOTH.class, TileRitualCOTH.getName());
	}

}
