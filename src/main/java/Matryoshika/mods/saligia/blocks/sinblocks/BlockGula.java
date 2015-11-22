package Matryoshika.mods.saligia.blocks.sinblocks;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGula extends Block {
	
	public BlockGula (Block BlockGula){
		super(Material.glass);
		setStepSound(soundTypeGlass);
		setBlockTextureName(saligia.MODID+":BlockGula");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockGula");
	}

}