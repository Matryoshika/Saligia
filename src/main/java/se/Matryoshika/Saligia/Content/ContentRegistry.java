package se.Matryoshika.Saligia.Content;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import se.Matryoshika.Saligia.Saligia;
import se.Matryoshika.Saligia.API.Content.BlockRegistryInjector;
import se.Matryoshika.Saligia.API.UtilityTiles.UtilityTileRegistry;
import se.Matryoshika.Saligia.Content.Blocks.*;
import se.Matryoshika.Saligia.Content.Blocks.Altars.*;
import se.Matryoshika.Saligia.Content.Blocks.RitualMasters.*;
import se.Matryoshika.Saligia.Content.Blocks.Utility.*;
import se.Matryoshika.Saligia.Content.Items.*;
import se.Matryoshika.Saligia.Content.Items.Animun.*;
import se.Matryoshika.Saligia.Content.Items.Tools.*;
import se.Matryoshika.Saligia.Content.Tiles.*;
import se.Matryoshika.Saligia.Content.Tiles.Utility.*;

/**
 * This class was created by Matryoshika Aug 8, 2016
 * Property of Matryoshika. 
 * Part of the Saligia mod.
 * May be viewed for educational purposes.
 */
public class ContentRegistry {
	
	//Tool Materials================================================
	public static ToolMaterial VILE = EnumHelper.addToolMaterial("SIN", 3, 1536, 10.0F, 8.0F, 0);
	
	//Blocks========================================================
	
	public static Block CHARCOAL_BLOCK;
	public static Block SIN_BLOCK;
	public static Block SIN_ROSE_BLOCK;
	public static Block ALTAR_PAGAN;
	
	public static Block BLOCK_RITUAL_MASTER;
	public static Block GHAST_BLOCK;
	public static Block GHAST_SPAWNER;
	
	
	public static BlockRitualMaster BLOCK_RITUAL_COTH;
	
	public static UtilityBlock BLOCK_PLACER;
	public static UtilityBlock MOB_KILLER;
	public static UtilityBlock ITEM_PICKUPPER;
	public static UtilityBlock CROP_GROWER;
	public static UtilityBlock SMELTER;
	
	public static List<Block> blockList = new ArrayList<Block>();
	
	public static void registerBlocks(){

		
		BlockRegistryInjector.blockList.add(CHARCOAL_BLOCK = new BlockCharcoal());
		BlockRegistryInjector.blockList.add(GHAST_BLOCK = new BlockGhast());
		BlockRegistryInjector.blockList.add(SIN_BLOCK = new BlockSinBlock());
		BlockRegistryInjector.blockList.add(SIN_ROSE_BLOCK = new BlockSinFlower());
		BlockRegistryInjector.blockList.add(ALTAR_PAGAN = new AltarPagan());
		BlockRegistryInjector.blockList.add(GHAST_SPAWNER = new BlockGhastSpawner());
		
		BlockRegistryInjector.utilityList.add(BLOCK_PLACER =  (UtilityBlock) new UtilityBlock().setRegistryName(Saligia.MODID, "blockplacer")
				.setUnlocalizedName(Saligia.MODID+":blockplacer"));
		BlockRegistryInjector.utilityList.add(MOB_KILLER = (UtilityBlock) new UtilityBlock().setRegistryName(Saligia.MODID, "mobkiller")
				.setUnlocalizedName(Saligia.MODID+":mobkiller"));
		BlockRegistryInjector.utilityList.add(ITEM_PICKUPPER = (UtilityBlock) new UtilityBlock().setRegistryName(Saligia.MODID, "itempickupper")
				.setUnlocalizedName(Saligia.MODID+":itempickupper"));
		BlockRegistryInjector.utilityList.add(CROP_GROWER = (UtilityBlock) new UtilityBlock().setRegistryName(Saligia.MODID, "cropgrower")
				.setUnlocalizedName(Saligia.MODID+":cropgrower"));
		BlockRegistryInjector.utilityList.add(SMELTER = (UtilityBlock) new UtilityBlock().setRegistryName(Saligia.MODID, "smelter")
				.setUnlocalizedName(Saligia.MODID+":smelter"));
		
		BlockRegistryInjector.ritualList.add(BLOCK_RITUAL_COTH =  (BlockRitualMaster) new BlockRitualMaster().setRitualName("Cognizance Of The Hellmouth"));
		
		
		BlockRegistryInjector.blockList.add(BLOCK_RITUAL_MASTER = new BlockRitualMaster().setRegistryName("BlockRitualMaster").setUnlocalizedName("saligia:BlockRitualMaster"));
		
	}

	//Items=========================================================
	
	public static Item DUST_INGOT;
	public static Item TAPE_MEASURE;
	public static Item RITUAL_ACTIVATOR;
	public static Item SIN_INGOT;
	public static Item DUST_COMPOUND;
	public static Item POISONCLAY;
	public static Item SOFTJAR;
	public static Item JAR;
	public static Item SPIDER_EYE_JAR;
	public static Item ZOMBIETOOTH;
	public static Item ZOMBIE_TEETH_JAR;
	public static Item SKELETONVERTEBRAE;
	public static Item SKELETON_VERTEBRAE_JAR;
	public static Item CREEPERASH;
	public static Item CREEPER_ASH_JAR;
	public static Item OCELOTCLAW;
	public static Item OCELOT_CLAW_JAR;
	public static Item MAGMA_CREAM_JAR;
	public static Item GHAST_TEAR_JAR;
	public static Item WOLFBRAIN;
	public static Item WOLF_BRAIN_JAR;
	
	public static Item CREATIVE_ANIMUN_CRUCIBLE;
	
	public static Item DAGGER;
	
	public static List<Item> itemList = new ArrayList<Item>();
	
	public static void registerItems(){
		
		itemList.add(DUST_INGOT = new ItemDustIngot());
		itemList.add(SIN_INGOT = new ItemSinIngot());
		itemList.add(TAPE_MEASURE = new ItemTapeMeasure());
		itemList.add(RITUAL_ACTIVATOR = new ItemRitualActivator());
		itemList.add(DUST_COMPOUND = new ItemDustCompound());
		itemList.add(POISONCLAY = new ItemPoisonClay());
		itemList.add(SOFTJAR = new ItemSoftJar());
		
		itemList.add(JAR = new ItemJar());
		itemList.add(SPIDER_EYE_JAR = new ItemSpiderEyeJar());
		itemList.add(ZOMBIETOOTH = new ItemZombieTooth());
		itemList.add(ZOMBIE_TEETH_JAR = new ItemZombieTeethJar());
		itemList.add(SKELETONVERTEBRAE = new ItemSkeletonVertebrae());
		itemList.add(SKELETON_VERTEBRAE_JAR = new ItemSkeletonVertebraeJar());
		itemList.add(CREEPERASH = new ItemCreeperAsh());
		itemList.add(CREEPER_ASH_JAR = new ItemCreeperAshJar());
		itemList.add(OCELOTCLAW = new ItemOcelotClaw());
		itemList.add(OCELOT_CLAW_JAR = new ItemOcelotClawJar());
		itemList.add(MAGMA_CREAM_JAR = new ItemMagmaCreamJar());
		itemList.add(GHAST_TEAR_JAR = new ItemGhastTearJar());
		itemList.add(WOLFBRAIN = new ItemWolfBrain());
		itemList.add(WOLF_BRAIN_JAR = new ItemWolfBrainJar());
		
		itemList.add(CREATIVE_ANIMUN_CRUCIBLE = new ItemCreativeSoulCrucible());
		
		itemList.add(DAGGER = new ItemDagger()); 
	}
	
	
	//Tiles=========================================================
	public static void registerTiles(){
		GameRegistry.registerTileEntity(TileRitual.class, TileRitual.getName());
		GameRegistry.registerTileEntity(TileRitualCOTH.class, TileRitualCOTH.getName());
		
		GameRegistry.registerTileEntity(TileGhastSpawner.class, Saligia.MODID+":ghastspawner");
		
		GameRegistry.registerTileEntity(TileMobKiller.class, Saligia.MODID+":mobkiller");
		GameRegistry.registerTileEntity(TileBlockPlacer.class, Saligia.MODID+":blockplacer");
		GameRegistry.registerTileEntity(TileItemPickUpper.class, Saligia.MODID+":itempickupper");
		GameRegistry.registerTileEntity(TileCropGrower.class, Saligia.MODID+":cropgrower");
		GameRegistry.registerTileEntity(TileSmelter.class, Saligia.MODID+":smelter");
		registerUtility();
	}
	
	
	//Utility=======================================================
	public static void registerUtility(){
		UtilityTileRegistry.addUtility(Saligia.MODID+":mobkiller", TileMobKiller.class, MOB_KILLER);
		UtilityTileRegistry.addUtility(Saligia.MODID+":blockplacer", TileBlockPlacer.class, BLOCK_PLACER);
		UtilityTileRegistry.addUtility(Saligia.MODID+":itempickupper", TileItemPickUpper.class, ITEM_PICKUPPER);
		UtilityTileRegistry.addUtility(Saligia.MODID+":cropgrower", TileCropGrower.class, CROP_GROWER);
		UtilityTileRegistry.addUtility(Saligia.MODID+":smelter", TileSmelter.class, SMELTER);
	}

}
