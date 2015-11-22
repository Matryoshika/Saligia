package Matryoshika.mods.saligia.blocks.sinblocks;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockInvidia extends Block {
	
	public BlockInvidia (Block BlockInvidia){
		super(Material.glass);
		setStepSound(soundTypeGlass);
		setBlockTextureName(saligia.MODID+":BlockInvidia");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockInvidia");
	}

}