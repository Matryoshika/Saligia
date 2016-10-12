package se.Matryoshika.Saligia.Content;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.API.UtilityTiles.UtilityTileRegistry;
import se.Matryoshika.Saligia.Content.Blocks.BlockCharcoal;
import se.Matryoshika.Saligia.Content.Blocks.BlockGhast;
import se.Matryoshika.Saligia.Content.Blocks.BlockSinBlock;
import se.Matryoshika.Saligia.Content.Blocks.BlockSinFlower;
import se.Matryoshika.Saligia.Content.Blocks.Altars.AltarPagan;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.BlockRitualMaster;
import se.Matryoshika.Saligia.Content.Blocks.Utility.UtilityBlock;
import se.Matryoshika.Saligia.Content.Items.ItemDustCompound;
import se.Matryoshika.Saligia.Content.Items.ItemDustIngot;
import se.Matryoshika.Saligia.Content.Items.ItemRitualActivator;
import se.Matryoshika.Saligia.Content.Items.ItemSinIngot;
import se.Matryoshika.Saligia.Content.Items.ItemTapeMeasure;
import se.Matryoshika.Saligia.Content.Tiles.TileRitual;
import se.Matryoshika.Saligia.Content.Tiles.TileRitualCOTH;
import se.Matryoshika.Saligia.Content.Tiles.Utility.TileBlockPlacer;
import se.Matryoshika.Saligia.Content.Tiles.Utility.TileMobKiller;

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
	public static Block SIN_ROSE_BLOCK;
	public static Block ALTAR_PAGAN;
	
	public static Block BLOCK_RITUAL_MASTER;
	public static Block GHAST_BLOCK;
	
	
	public static BlockRitualMaster BLOCK_RITUAL_COTH;
	
	public static UtilityBlock BLOCK_PLACER;
	public static UtilityBlock MOB_KILLER;
	
	public static List<Block> blockList = new ArrayList<Block>();
	
	public static void registerBlocks(){

		
		BlockRegistryInjector.blockList.add(CHARCOAL_BLOCK = new BlockCharcoal());
		BlockRegistryInjector.blockList.add(GHAST_BLOCK = new BlockGhast());
		BlockRegistryInjector.blockList.add(SIN_BLOCK = new BlockSinBlock());
		BlockRegistryInjector.blockList.add(SIN_ROSE_BLOCK = new BlockSinFlower());
		BlockRegistryInjector.blockList.add(ALTAR_PAGAN = new AltarPagan());
		
		BlockRegistryInjector.utilityList.add(BLOCK_PLACER =  (UtilityBlock) new UtilityBlock().setRegistryName(Saligia.MODID, "blockplacer")
				.setUnlocalizedName(Saligia.MODID+":blockplacer"));
		BlockRegistryInjector.utilityList.add(MOB_KILLER = (UtilityBlock) new UtilityBlock().setRegistryName(Saligia.MODID, "mobkiller")
				.setUnlocalizedName(Saligia.MODID+":mobkiller"));
		
		BlockRegistryInjector.ritualList.add(BLOCK_RITUAL_COTH =  (BlockRitualMaster) new BlockRitualMaster().setRitualName("Cognizance Of The Hellmouth"));
		
		
		BlockRegistryInjector.blockList.add(BLOCK_RITUAL_MASTER = new BlockRitualMaster().setRegistryName("BlockRitualMaster").setUnlocalizedName("saligia:BlockRitualMaster"));
		
	}

	//Items=========================================================
	
	public static Item DUST_INGOT;
	public static Item TAPE_MEASURE;
	public static Item RITUAL_ACTIVATOR;
	public static Item SIN_INGOT;
	public static Item DUST_COMPOUND;
	
	public static List<Item> itemList = new ArrayList<Item>();
	
	public static void registerItems(){
		
		itemList.add(DUST_INGOT = new ItemDustIngot());
		itemList.add(SIN_INGOT = new ItemSinIngot());
		itemList.add(TAPE_MEASURE = new ItemTapeMeasure());
		itemList.add(RITUAL_ACTIVATOR = new ItemRitualActivator());
		itemList.add(DUST_COMPOUND = new ItemDustCompound());
	}
	
	
	//Tiles=========================================================
	public static void registerTiles(){
		GameRegistry.registerTileEntity(TileRitual.class, TileRitual.getName());
		GameRegistry.registerTileEntity(TileRitualCOTH.class, TileRitualCOTH.getName());
		
		GameRegistry.registerTileEntity(TileMobKiller.class, Saligia.MODID+"mobkiller");
		GameRegistry.registerTileEntity(TileBlockPlacer.class, Saligia.MODID+":blockplacer");
		registerUtility();
	}
	
	
	//Utility=======================================================
	public static void registerUtility(){
		UtilityTileRegistry.addUtility(Saligia.MODID+":mobkiller", TileMobKiller.class, MOB_KILLER);
		UtilityTileRegistry.addUtility(Saligia.MODID+":blockplacer", TileBlockPlacer.class, BLOCK_PLACER);
	}

}
