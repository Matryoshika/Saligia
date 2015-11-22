package Matryoshika.mods.saligia.blocks.sinblocks;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockAcedia extends Block {
	
	public BlockAcedia (Block BlockAcedia){
		super(Material.glass);
		setStepSound(soundTypeGlass);
		setBlockTextureName(saligia.MODID+":BlockAcedia");
		setHardness(10);
		this.setResistance(150);
		this.setBlockName("BlockAcedia");
	}

}
