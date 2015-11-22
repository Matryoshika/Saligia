package Matryoshika.mods.saligia.blocks.sinblocks;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockLuxuria extends Block {
	
	public BlockLuxuria (Block BlockLuxuria){
		super(Material.glass);
		setStepSound(soundTypeGlass);
		setBlockTextureName(saligia.MODID+":BlockLuxuria");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockLuxuria");
	}

}