package Matryoshika.mods.saligia.blocks;

import java.util.ArrayList;
import java.util.List;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.altars.*;
import Matryoshika.mods.saligia.blocks.ritualmasters.*;
import Matryoshika.mods.saligia.blocks.sinblocks.*;
import Matryoshika.mods.saligia.blocks.soulsystem.BlockLeyLineCrystal;
import Matryoshika.mods.saligia.blocks.soulsystem.BlockSoulBrazier;
import Matryoshika.mods.saligia.blocks.soulsystem.BlockSoulNexus;
import Matryoshika.mods.saligia.blocks.soulsystem.BlockSoulObelisk;
import Matryoshika.mods.saligia.blocks.soulsystem.BlockSoulPyre;
import Matryoshika.mods.saligia.items.saligia_Items;
import Matryoshika.mods.saligia.blocks.*;
import Matryoshika.mods.saligia.utils.ConfigHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class saligia_Blocks {
	
	public static Block CentreRitual;
	public static Block CentreCOTH;
	public static Block CentreROTTS;
	public static Block CentreFOTDB;
	public static Block CentreFOTI;
	
	
	public static Block MatryoshikaBrain;
	public static Block AcediaBlock;
	public static Block AvaritiaBlock;
	public static Block GulaBlock;
	public static Block InvidiaBlock;
	public static Block IraBlock;
	public static Block LuxuriaBlock;
	public static Block SuperbiaBlock;
	
	public static Block GeneratorDynamic;
	public static Block VoidLight;
	public static Block CharcoalBlock;
	public static Block RunicScribe;
	public static Block SinRose;
	
	public static Block AltarPagan;
	public static Block AltarCultist;
	public static Block AltarEvil;
	public static Block AltarSinful;
	public static Block AltarDemonic;
	
	public static Block SoulBrazier;
	public static Block SoulObelisk;
	public static Block SoulNexus;
	public static Block SoulPyre;
	public static Block LeyLineCrystal;
	
	public static List<Block>BlockList=new ArrayList<Block>();
	public static List<Block>BlockMetaList=new ArrayList<Block>();
	
	
	public static void registerBlocks() {
		BlockList.add(AcediaBlock = new BlockAcedia(AcediaBlock).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(GulaBlock = new BlockGula(GulaBlock).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(InvidiaBlock = new BlockInvidia(InvidiaBlock).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(IraBlock = new BlockIra(IraBlock).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(LuxuriaBlock = new BlockLuxuria(LuxuriaBlock).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(SuperbiaBlock = new BlockSuperbia(SuperbiaBlock).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(AvaritiaBlock = new BlockAvaritia(AvaritiaBlock).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(AltarPagan = new PaganAltar(AltarPagan).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(AltarCultist = new CultistAltar(AltarCultist).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(AltarEvil = new EvilAltar(AltarEvil).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(AltarSinful = new SinfulAltar(AltarSinful).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(AltarDemonic = new DemonicAltar(AltarDemonic).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(CentreRitual = new BlockRitualCentre(CentreRitual).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(CentreCOTH = new BlockRitualCOTH(CentreCOTH).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(CentreROTTS = new BlockRitualROTTS(CentreROTTS).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(CentreFOTDB = new BlockRitualFOTDB(CentreFOTDB).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(CentreFOTI = new BlockRitualFOTI(CentreFOTI).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(VoidLight = new BlockVoidLight(VoidLight).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(CharcoalBlock = new BlockCharcoal(CharcoalBlock).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(SoulBrazier = new BlockSoulBrazier(SoulBrazier).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(SoulObelisk = new BlockSoulObelisk(SoulObelisk).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(SoulNexus = new BlockSoulNexus(SoulNexus).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(SoulPyre = new BlockSoulPyre(SoulPyre).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(LeyLineCrystal = new BlockLeyLineCrystal(LeyLineCrystal).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(RunicScribe = new BlockRunicScribe(RunicScribe).setCreativeTab(saligia.MatryoshikaTab));
		BlockList.add(SinRose = new blockSinRose(SinRose).setCreativeTab(saligia.MatryoshikaTab));
		//BlockList.add(GhastlyBlock = new BlockGhastly(GhastlyBlock).setCreativeTab(saligia.MatryoshikaTab));
		
		for(Block block:BlockList){
			GameRegistry.registerBlock(block,  block.getUnlocalizedName());
		}
		
		/*GameRegistry.addRecipe(new ItemStack(saligia.GhastlyBlock), new Object[]{
				"TTT",
				"TNT",
				"TTT",
				'N', Blocks.netherrack, 'T', Items.ghast_tear
		});*/
		
		
	}
}
