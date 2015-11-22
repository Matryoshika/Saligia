package Matryoshika.mods.saligia.blocks;

import java.util.ArrayList;
import java.util.List;

import Matryoshika.mods.saligia.saligia;
import Matryoshika.mods.saligia.blocks.altars.*;
import Matryoshika.mods.saligia.blocks.sinblocks.BlockAcedia;
import Matryoshika.mods.saligia.blocks.sinblocks.BlockAvaritia;
import Matryoshika.mods.saligia.blocks.sinblocks.BlockGula;
import Matryoshika.mods.saligia.blocks.sinblocks.BlockInvidia;
import Matryoshika.mods.saligia.blocks.sinblocks.BlockIra;
import Matryoshika.mods.saligia.blocks.sinblocks.BlockLuxuria;
import Matryoshika.mods.saligia.blocks.sinblocks.BlockSuperbia;
import Matryoshika.mods.saligia.utils.ConfigHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class saligia_Blocks {
	
	public static Block MatryoshikaBrain;
	public static Block AcediaBlock;
	public static Block AvaritiaBlock;
	public static Block GulaBlock;
	public static Block InvidiaBlock;
	public static Block IraBlock;
	public static Block LuxuriaBlock;
	public static Block SuperbiaBlock;
	
	public static Block GeneratorDynamic;
	
	public static Block AltarPagan;
	public static Block AltarCultist;
	public static Block AltarEvil;
	public static Block AltarSinful;
	public static Block AltarDemonic;
	
	public static List<Block>BlockList=new ArrayList<Block>();
	
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
		
		for(Block block:BlockList){
			GameRegistry.registerBlock(block,  block.getUnlocalizedName());
		}
		
	}

}
