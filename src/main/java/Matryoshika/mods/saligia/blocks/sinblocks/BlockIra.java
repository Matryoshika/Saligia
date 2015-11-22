package Matryoshika.mods.saligia.blocks.sinblocks;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockIra extends Block {
	
	public BlockIra (Block BlockIra){
		super(Material.glass);
		setStepSound(soundTypeGlass);
		setBlockTextureName(saligia.MODID+":BlockIra");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockIra");
	}

}