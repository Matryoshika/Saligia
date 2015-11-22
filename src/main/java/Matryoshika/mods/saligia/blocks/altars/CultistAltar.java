package Matryoshika.mods.saligia.blocks.altars;

import Matryoshika.mods.saligia.saligia;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CultistAltar extends Block {
	
	public CultistAltar (Block CultistAltar){
		super(Material.iron);
		setStepSound(soundTypeStone);
		setBlockTextureName(saligia.MODID+":CultistAltar");
		setHardness(10);
		this.setResistance(18000000);
		this.setBlockName("CultistAltar");
	}

}
