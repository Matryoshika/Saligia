package Matryoshika.mods.saligia.blocks.sinblocks;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAvaritia extends Block {
	
	public BlockAvaritia (Block BlockAvaritia){
		super(Material.glass);
		setStepSound(soundTypeGlass);
		setBlockTextureName(saligia.MODID+":BlockAvaritia");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockAvaritia");
	}

}
